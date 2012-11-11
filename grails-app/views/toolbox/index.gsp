<html>

<head>
    <title>Hello world</title>
    <meta name="layout" content="bootstrap"/>
    <r:require module="index-page"/>
</head>

<body>
<div class="container">

    <h2 class="page-header">Plugins
        <input type="text" class="pull-right" placeholder="filter this content" style="
        margin-top: 10px;
        ">
    </h2>

    <g:each var="category" in="${categoryList}">

        <h3>
            ${category.categoryName}
        </h3>

        <table class="table">
            <tbody>
            <g:each var="p" status="pi" in="${category.pluginList}">
                <tr ${pi == 0 ? 'class="row-additional"' : ''}>
                    <td class="span3"><strong>${p.code}</strong> <small class="muted">by ${p.authors}</small></td>
                    <td class="span3">v. ${p.release} (${p.releases} releases total)</td>
                    <td class="span3">${String.format('%d.%d/5', p.rate5.intdiv(10), p.rate5 % 10)} (${p.ratings} ratings)</td>
                </tr>
                <g:if test="${p.news}">
                    <g:set var="n" value="${p.news[0]}"/>
                    <tr class="row-additional row-comment">
                        <td><small class="muted">${n.date} ${n.origin}</small></td> <td colspan="2"><small class="muted">${n.title}</small>
                        <g:if test="${p.news.size() > 1}"><span class="pull-right"><a href="#">&hellip; and ${p.news.size() - 1} more links</a></span></g:if></td>
                    </tr>
                </g:if>
            </g:each>

            </tbody>
        </table>
    </g:each>

</div>

</body>
</html>
