function BrowserDisplay(element) {
    this.element = element;
    this.display = this.element.find('.browser-display__display')
}
BrowserDisplay.prototype.show = function() {
    this.element.show();
};
BrowserDisplay.prototype.hide = function() {
    this.element.hide();
};
BrowserDisplay.prototype.setBrowsers = function(browsers) {
    var html = browsers.map(function(browser) {
        var iconClass = browser.browserName + '-icon';
        return '<span class="browser-thumb text-capitalize">' +
            '<span class="browser-thumb__icon ' + iconClass + '"></span> '
            + browser.browserName + ' ' + browser.version +
            '</span>'
    }).join(' ');
    this.display.html(html);
};
