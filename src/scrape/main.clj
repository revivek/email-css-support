(ns scrape.main
  (:require [net.cgrand.enlive-html :as html]
            [clojure.data.json :as json]
            [clojure.core.matrix :as matrix]))

(def base-url "https://www.campaignmonitor.com/css/")
(def feature-names-selector [:#csstable :tbody :td.element-style])
(def email-clients-selector [:#csstable :thead :td.client])

(defn support-selector
  "Create support selector. Need client count because there's a bug(?) causing an extra <td>.
  So we pick the first nth <td> elements."
  [client-count]
  [:#csstable :tbody :> [:tr (html/but :.short)]
   [:td (html/but :.element-style) (html/nth-child -1 (inc client-count))]])

(defn fetch-url
  "Fetch and parse HTML response returned by request to provided URL."
  [url]
  (html/html-resource (java.net.URL. url)))

(defn extract-text
  "Extract text vector, given a resource and selector."
  [html-resource selector]
  (mapv html/text (html/select html-resource selector)))

(defn extract-matrix
  "Extract 2D vector matrix, given a resource, partition length, and selector."
  [html-resource partition-size selector]
  {:pre [(pos? partition-size)]
   :post [(= 1 (count (distinct (map count %))))]}
  (partition-all partition-size (extract-text html-resource selector)))

(defn build
  "Build up final hashmap data-structure."
  [clients clients-support feature-names]
  {:pre [(pos? (count clients-support))
         (= (count clients) (count clients-support))
         (= (count (first clients-support)) (count feature-names))]}
  {:clients (mapv #(hash-map :client %1 :features (zipmap feature-names %2))
                  clients
                  clients-support)})

(defn -main
  []
  (spit
    "main.json"
    (let [root            (fetch-url base-url)
          feature-names   (extract-text root feature-names-selector)
          clients         (extract-text root email-clients-selector)
          clients-support (matrix/transpose ;; feature-indexed (row) -> client-indexed (col)
                            (matrix/emap #(case % "No" 0 "Info" 1 "Yes" 2)
                              (extract-matrix root
                                              (count clients)
                                              (support-selector (count clients)))))]
        (json/write-str (build clients clients-support feature-names)))))
