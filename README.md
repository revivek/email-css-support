# Email CSS Support

Breakdown of the CSS support for the top 10 popular mobile, web and desktop email clients.

Scraped from [The Ultimate Guide to CSS](https://www.campaignmonitor.com/css/), and located in `main.json`.

**Personal note:** This is a project written Clojure, a language I&rsquo;ve eyed for a while.

### Example usage when converted to a JavaScript Object

```
client1.supported_features['*']
client1.supported_features['@font-face']

// If someone were to create a JS library,
// then something like this could be handy:
emailClients['Google Gmail'].getSupportFor('E[foo*="bar"]');
emailClients['Outlook 2007/10/13'].getSupportFor('E F');
emailClients['iPhone iOS 7/iPad'].getSupportFor('font-family');
emailClients['Apple Mail 6.5'].getSupportFor('RGBA Colors');
```

## TODO

[ ] Clean up '+' at the end of some client headers
[ ] Clean up 'CSS3' at the end of some feature names
