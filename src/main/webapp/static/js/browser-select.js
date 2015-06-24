function BrowserSelect(element) {
    this.element = element;
}
BrowserSelect.prototype.resetDisplay = function(visible) {
    this.element.attr('hidden', visible ? null : true);
    this.element.css('display', '');
};
BrowserSelect.prototype.show = function() {
    this.element.slideDown({
        complete: this.resetDisplay.bind(this, true)
    });
};
BrowserSelect.prototype.hide = function() {
    this.element.slideUp({
        complete: this.resetDisplay.bind(this, false)
    });
};
BrowserSelect.prototype.getSelectedBrowsers = function() {
    return this.element.find('.browser').toArray().reduce(function(browsers, browser) {
        var $browser = $(browser);
        var browserName = $browser.find('.browser__name').text().trim();
        return browsers.concat($browser.find('.version-checkbox input[type=checkbox]:checked').toArray().map(function(version) {
            return {
                browserName: browserName,
                version: version.value
            }
        }));
    }, [])
};
