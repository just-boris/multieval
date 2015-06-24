function BrowserThumb(browser) {
    this.render = function() {
        var iconClass = browser.browserName + '-icon';
        return '<span class="browser-thumb text-capitalize">' +
            '<span class="browser-thumb__icon ' + iconClass + '"></span> '
            + browser.browserName + ' ' + browser.version +
        '</span>'
    }
}
