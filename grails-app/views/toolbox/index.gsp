<html>

<head>
    <title>Hello world</title>
    <meta name="layout" content="bootstrap"/>
    <r:require module="index-page"/>
</head>

<body>
<div class="container">
    <div>
        <h1>
            Grails plugins toolbox
        </h1>

        <p>
            All the blogs, screencasts, discussions and questions in one place
            All the blogs, screencasts, discussions and questions in one place
            All the blogs, screencasts, discussions and questions in one place
        </p>
    </div>


    <h2 class="page-header">Plugins
        <input type="text" class="pull-right" placeholder="filter this content" style="
        margin-top: 10px;
        ">
    </h2>

    <h3>
        Security
    </h3>

    <table class="table">
        <tbody>
        <g:each var="p" status="pi" in="${pluginData}">
            <tr ${pi == 0 ? 'class="row-additional"' : ''}>
                <td class="span3"><strong>${p.code}</strong> <small class="muted">by ${p.authors}</small></td>
                <td class="span3">v. ${p.release} (2 releases last year)</td>
                <td class="span3">${p.rating} (${p.ratings} ratings, 50% adoption)</td>
            </tr>
            <g:each in="${p.news}" var="n">
                <tr class="row-additional row-comment">
                    <td><small class="muted">${n.date} ${n.origin}</small></td> <td colspan="2"><small class="muted">${n.title}</small> <span class="pull-right"><a href="#">&hellip; and ${new Random().nextInt(4) + 1} more links</a></span></td>
                </tr>
            </g:each>
        </g:each>

        </tbody>
    </table>

</div>

</body>
</html>
