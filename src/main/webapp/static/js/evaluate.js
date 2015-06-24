function EvaluateApp(elem) {
    this.elem = elem;
    this.requestsBox = this.elem.find('.requests');
    this.scriptBox = this.elem.find('.evaluate-app__script');
    this.browserDisplay = new BrowserDisplay(this.elem.find('.browser-display'));
    this.browserSelect = new BrowserSelect(this.elem.find('.browser-select'));
    this.elem.on('submit', this.onSubmit.bind(this));
    this.elem.find('.evaluate-app__collapse').on('click', this.collapseBrowsers.bind(this));
    this.elem.find('.evaluate-app__expand').on('click', this.expandBrowsers.bind(this));
}
EvaluateApp.prototype.onSubmit = function(e) {
    e.preventDefault();
    this.requestsBox.html('');
    var scriptText = this.scriptBox.val();
    this.requestsBox.attr('hidden', null);
    this.browserSelect.getSelectedBrowsers().forEach(function(browser) {
        var container = $('<div />').appendTo(this.requestsBox);
        new Request(container, browser, scriptText)
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

function Request(elem, browser, text) {
    this.elem = elem;
    this.elem.addClass('request');
    this.elem.append('<b class="text-capitalize">' + browser.browserName + ' ' + browser.version + '</b> ');
    this.status = $('<span>loading...</span>').appendTo(this.elem);
    $.ajax({
        type: "POST",
        url: '/api/evaluate?' + $.param({browser: browser.browserName, version: browser.version}),
        dataType: 'text',
        data: text
    }).then(this.onSuccess.bind(this), this.onError.bind(this))
}
Request.prototype.onSuccess = function(result) {
    this.status.addClass('text-success');
    this.status.text(result);
};
Request.prototype.onError = function(e) {
    this.status.addClass('text-danger');
    this.status.text(e.responseText);
};

$(document).ready(function() {
    new EvaluateApp($('.evaluate-app'))
});
