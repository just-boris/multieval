function EvaluateApp(elem) {
    this.elem = elem;
    this.requestsBox = this.elem.find('.requests');
    this.scriptBox = this.elem.find('.evaluate-app__script');
    this.shortBox = this.elem.find('.evaluate-app__short');
    this.browsersBox = this.elem.find('.evaluate-app__browsers');
    this.elem.on('submit', this.onSubmit.bind(this));
    this.elem.find('.evaluate-app__collapse').on('click', this.collapseBrowsers.bind(this));
    this.elem.find('.evaluate-app__expand').on('click', this.expandBrowsers.bind(this));
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
EvaluateApp.prototype.collapseBrowsers = function() {
    this.shortBox.attr('hidden', null).find('.evaluate-app__display').html(this.getCollapsedViewHtml());
    this.browsersBox.slideUp({
        complete: function() {
            $(this).attr('hidden', true).css('display', '');
        }
    });

};
EvaluateApp.prototype.expandBrowsers = function() {
    this.shortBox.attr('hidden', true);
    this.browsersBox.slideDown({
        complete: function() {
            $(this).css('display', '');
        }
    });
    this.browsersBox.attr('hidden', null);
};
EvaluateApp.prototype.getCollapsedViewHtml = function() {
    return this.getSelectedBrowsers().map(function(browser) {
        var iconClass = browser.browserName + '-icon';
        return '<span class="browser-thumb text-capitalize"><span class="browser-thumb__icon '+iconClass+'"></span> ' + browser.browserName + ' ' + browser.version + '</span>'
    }).join(' ');
};
EvaluateApp.prototype.getSelectedBrowsers = function() {
    return this.elem.find('.browser').toArray().reduce(function(browsers, browser) {
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
