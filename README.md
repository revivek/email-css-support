# Email CSS Support

Breakdown of the CSS support for the top 10 popular mobile, web and desktop email clients.

Scraped from [The Ultimate Guide to CSS](https://www.campaignmonitor.com/css/), and located in `main.json`.

```
0 = No support
1 = Partial support
2 = Full support
```

**Personal note:** This is a project written Clojure, a language I&rsquo;ve eyed for a while.

### Example usage when converted to a JavaScript Object

```
// If someone were to create a JS wrapper,
// then something like this could be handy:
emailClients['Google Gmail'].hasFullSupportFor('E[foo*="bar"]');
emailClients['Outlook 2007/10/13'].hasFullSupportFor('E F');
emailClients['iPhone iOS 7/iPad'].hasFullSupportFor('font-family');
emailClients['Apple Mail 6.5'].hasFullSupportFor('RGBA Colors');
```
