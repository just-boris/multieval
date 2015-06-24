<#-- @ftlvariable name="browsers" type="java.util.List<com.catatron.probe.beans.Browser>"  -->
<html>
<head>
    <title>Probe</title>
    <link rel="stylesheet" href="https://yastatic.net/bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/styles.css"/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Probe</a>
        </div>

        <div class="collapse in navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/evaluate">Evaluate</a></li>
                <li class="disabled" title="Not implemented yet"><a>Screenshots</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h1>Evaluate</h1>

    <p>Evaluate your javascript snippet into differen browsers and compare results</p>

    <form class="evaluate-app">
        <h3>Browsers</h3>
        <div class="browser-select">
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
        <div class="browser-display" hidden>
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
<script src="/static/js/evaluate.js"></script>
<script src="/static/js/browser-display.js"></script>
<script src="/static/js/browser-select.js"></script>
</body>
</html>
