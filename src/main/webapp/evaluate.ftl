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

        <div class="collapse navbar-collapse">
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
        <button class="btn btn-link pull-right">
            <span class="glyphicon glyphicon-chevron-up"></span>
            Close
        </button>
        <h3>Browsers</h3>
        <#list browsers as browser>
            <div class="browser">
                <h5 class="text-capitalize browser__name">${browser.name}</h5>
                <#list browser.versions as version>
                    <div class="checkbox browser__version">
                        <label>
                            <input type="checkbox" value="${version}" <#if version == browser.defaultVersion>checked</#if> />
                            <span>${version}</span>
                        </label>
                    </div>
                </#list>
            </div>
        </#list>
        <h3>Code</h3>
        <div class="form-group">
            <textarea class="form-control evaluate-app__script" name="code" cols="30" rows="10"></textarea>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Evaluate</button>
        </div>
        <div class="requests" hidden>
            <h3>Requests</h3>
        </div>
    </form>
</div>

<script src="https://yastatic.net/jquery/2.1.3/jquery.min.js"></script>
<script src="/static/js/evaluate.js"></script>
</body>
</html>
