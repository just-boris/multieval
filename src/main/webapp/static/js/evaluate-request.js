function EvaluateRequest(elem, browser, text) {
    this.element = elem;
    this.render(browser);
    $.ajax({
        type: "POST",
        url: '/api/evaluate?' + $.param({browser: browser.browserName, version: browser.version}),
        dataType: 'text',
        data: text
    }).then(this.onSuccess.bind(this), this.onError.bind(this))
}
EvaluateRequest.prototype.render = function(browser) {
    this.element.addClass('request');
    this.element.html('<p class="request__title">'+new BrowserThumb(browser).render()+'</p>');
    this.status = $('<span class="text-info">loading...</span>');
    this.element.find('.request__title').append(this.status)
};
EvaluateRequest.prototype.setResult = function(result) {
    this.element.append('<pre class="request__output"><code>'+result+'</code></pre>');
};
EvaluateRequest.prototype.onSuccess = function(result) {
    this.status.attr('class', 'text-success').text('success');
    this.setResult(result);
};
EvaluateRequest.prototype.onError = function(e) {
    this.status.attr('class', 'text-danger').text('failed');
    this.setResult(e.responseText);
};
