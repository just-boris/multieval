function EvaluateApp(elem) {
    this.element = elem;
    this.requestsBox = this.element.find('.requests');
    this.scriptBox = this.element.find('.evaluate-app__script');
    this.browserDisplay = new BrowserDisplay(this.element.find('.browser-display'));
    this.browserSelect = new BrowserSelect(this.element.find('.browser-select'));
    this.element.on('submit', this.onSubmit.bind(this));
    this.element.find('.evaluate-app__collapse').on('click', this.collapseBrowsers.bind(this));
    this.element.find('.evaluate-app__expand').on('click', this.expandBrowsers.bind(this));
    this.browserDisplay.setBrowsers(this.browserSelect.getSelectedBrowsers());
}
EvaluateApp.prototype.onSubmit = function(e) {
    e.preventDefault();
    this.requestsBox.html('');
    var scriptText = this.scriptBox.val();
    this.requestsBox.attr('hidden', null);
    this.browserSelect.getSelectedBrowsers().forEach(function(browser) {
        var container = $('<div />').appendTo(this.requestsBox);
        new EvaluateRequest(container, browser, scriptText)
    }, this);
};
EvaluateApp.prototype.collapseBrowsers = function() {
    this.browserDisplay.setBrowsers(this.browserSelect.getSelectedBrowsers());
    this.browserDisplay.show();
    this.browserSelect.hide();

};
EvaluateApp.prototype.expandBrowsers = function() {
    this.browserDisplay.hide();
    this.browserSelect.show();
};

$(document).ready(function() {
    new EvaluateApp($('.evaluate-app'))
});
