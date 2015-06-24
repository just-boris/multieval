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
        return new BrowserThumb(browser).render()
    }).join(' ');
    this.display.html(html);
};
