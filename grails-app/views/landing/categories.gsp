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
            By popularity
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

<h2 class="page-header">
    Popular categories
</h2>


<g:each var="c" in="'aa'">
<h3>
    Security
</h3>
<div class="well group-well">
    <table class="table">
        %{--<thead>--}%
        %{--<tr>--}%
            %{--<th class="span4">Plugin</th>--}%
            %{--<th class="span3">Version</th>--}%
            %{--<th class="span5">Popularity</th>--}%
        %{--</tr>--}%
        %{--</thead>--}%
        %{--<% println pluginData.dump() %>
        <g:each var="p" status="pi" in="${pluginData}">
            <div class="row" ${pi == 0 ? '' : 'style="border-top:1px;"'}>
                <div class="span4">${p.code} <small class="muted">by ${p.authors}</small></div>
                <div class="span3">${p.release} (2 releases last year)</div>
                <div class="span4">${p.rating} (${p.ratings} ratings, 50% adoption)</div>
            </div>
            <g:each in="${p.news}" var="n">
                <div class="row" >
                    <div class="span4"> <small class="muted">${n.date} ${n.origin}</small></div> <div calss="span7"><small class="muted">${n.title}</small></div>
                </div>
            </g:each>
        </g:each>--}%

        <tbody>
        <g:each var="p" status="pi" in="${pluginData}">
            <tr ${pi == 0 ? 'class="row-additional"' : ''}>
                <td class="span3"><strong>${p.code}</strong> <small class="muted">by ${p.authors}</small></td>
                <td class="span3">v. ${p.release} (2 releases last year)</td>
                <td class="span3">${p.rating} (${p.ratings} ratings, 50% adoption)</td>
            </tr>
            <g:each in="${p.news}" var="n">
                <tr class="row-additional">
                    <td><small class="muted">${n.date} ${n.origin}</small></td> <td colspan="2"><small class="muted">${n.title}</small> <span class="pull-right"><a href="#">&hellip; and ${new Random().nextInt(4) + 1} more links</a></span></td>
                </tr>
            </g:each>
        </g:each>

        </tbody>
    </table>
</div>
</g:each>

</body>

</html>
