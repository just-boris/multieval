function EvaluateApp(elem) {
    this.elem = elem;
    this.requestsBox = this.elem.find('.requests');
    this.scriptBox = this.elem.find('.evaluate-app__script');
    this.elem.on('submit', this.onSubmit.bind(this))
}
EvaluateApp.prototype.onSubmit = function(e) {
    e.preventDefault();
    this.requestsBox.html('');
    var scriptText = this.scriptBox.val();
    this.requestsBox.attr('hidden', null);
    this.getSelectedBrowsers().forEach(function(browser) {
        var container = $('<div />').appendTo(this.requestsBox);
        new Request(container, browser, scriptText)
    }, this);
};
EvaluateApp.prototype.getSelectedBrowsers = function() {
    return this.elem.find('.browser').toArray().reduce(function(browsers, browser) {
        var $browser = $(browser);
        var browserName = $browser.find('.browser__name').text();
        return browsers.concat($browser.find('.browser__version input[type=checkbox]:checked').toArray().map(function(version) {
            return {
                browserName: browserName,
                version: version.value
            }
        }));
    }, [])
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
