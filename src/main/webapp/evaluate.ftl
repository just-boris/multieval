<#-- @ftlvariable name="browsers" type="java.util.List<it.multieval.beans.Browser>"  -->
<#-- @ftlvariable name="base_path" type="java.lang.String"  -->
<#-- @ftlvariable name="analytics_key" type="java.lang.String"  -->
<html>
<head>
    <title>Multieval</title>
    <link rel="stylesheet" href="https://yastatic.net/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${base_path}/static/css/styles.css"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Multieval</a>
        </div>

        <div class="collapse in navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/evaluate">Evaluate</a></li>
                <li class="disabled" title="Not implemented yet"><a>Screenshots</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="https://github.com/just-boris/multieval">Source on Github</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h1>Evaluate</h1>

    <p>Evaluate your javascript snippet into different browsers and compare results</p>

    <form class="evaluate-app">
        <h3>Browsers</h3>
        <div class="browser-select" hidden>
            <button type="button" class="btn btn-link pull-right evaluate-app__collapse">
                <span class="glyphicon glyphicon-chevron-up"></span>
                Collapse
            </button>
            <#list browsers as browser>
                <div class="browser">
                    <h5 class="text-capitalize browser__name">
                        <span class="browser__icon ${browser.name}-icon"></span>
                    ${browser.name}
                    </h5>
                    <div class="browser__versions">
                        <#list browser.versions as version>
                            <div class="version-checkbox">
                                <label>
                                    <input type="checkbox" value="${version}" <#if version == browser.defaultVersion>checked</#if> />
                                    <span class="version-checkbox__display">${version}</span>
                                </label>
                            </div>
                        </#list>
                    </div>
                </div>
            </#list>
        </div>
        <div class="browser-display">
            <button type="button" class="btn btn-link pull-right evaluate-app__expand">
                <span class="glyphicon glyphicon-chevron-down"></span>
                Edit
            </button>
            <div class="browser-display__display"></div>
        </div>
        <h3>Code</h3>
        <div class="form-group">
            <textarea class="form-control evaluate-app__script" name="code" cols="30" rows="10">return 'ok';</textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Evaluate</button>
        </div>
        <div class="requests" hidden>
            <h3>Requests</h3>
        </div>
    </form>
</div>

<script src="https://yastatic.net/jquery/2.1.3/jquery.min.js"></script>
<script src="${base_path}/static/js/evaluate.js"></script>
<script src="${base_path}/static/js/evaluate-request.js"></script>
<script src="${base_path}/static/js/browser-thumb.js"></script>
<script src="${base_path}/static/js/browser-display.js"></script>
<script src="${base_path}/static/js/browser-select.js"></script>
<#if analytics_key?length &gt; 0>
<script>
    (function(i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function() {
                    (i[r].q = i[r].q || []).push(arguments)
                };
        i[r].l = 1 * new Date();
        a = s.createElement(o);
        m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
    ga('create', '${analytics_key}', 'auto');
    ga('send', 'pageview');
</script>
</#if>
</body>
</html>
