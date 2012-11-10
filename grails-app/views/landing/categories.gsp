<html>

<head>
    <title><g:message code="default.welcome.title" args="[meta(name: 'app.name')]"/></title>
    <meta name="layout" content="kickstart"/>
</head>

<body>

<g:render template="navbarFromJetstrap"/>

<div class="hero-unit">
    <div>
        <h1>
            Grails plugins toolbox
        </h1>

        <p>
            All the blogs, screencasts, discussions and questions in one place
        </p>
    </div>
</div>
<ul class="nav nav-tabs">
    <li>
        <a href="#">
            By category
        </a>
    </li>
    <li>
        <a href="#">
            By name
        </a>
    </li>
    <li>
    </li>
</ul>

<div class="container-fluid">
    <div class="row-fluid">
    </div>
</div>

<h3>
    Plugins by category
</h3>

<div class="well">
    <h4>
        Security
    </h4>
    <table class="table">
        <thead>
        <tr>
            <th class="span3">Plugin name</th>
            <th class="span2">Version</th>
            <th class="span2">Popularity</th>
            <th class="span1">Comments</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
        </tr>
        <tr class="row-additional">
            <td colspan="4"><small class="muted"> this is a great plugin blah blah</small></td>
        </tr>
        <tr>
            <td>2</td>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
        </tr>
        <tr>
            <td>3</td>
            <td>Larry</td>
            <td>the Bird</td>
            <td>@twitter</td>
        </tr>
        </tbody>
    </table>

    <div class="row">
        <div class="span3">
            Plugin name
        </div>

        <div class="span2">
            Last version
        </div>

        <div class="span3">
            Popularity
        </div>

        <div class="span3">
            Something else
        </div>

        <div class="span1">
            Like
        </div>
    </div>

    <div class="row">
        <div class="span12">
        </div>
    </div>

    <div class="row">
    </div>
</div>

</body>

</html>
