<%@ page import="ru.gramant.grails.toolbox.ResourceType" %>
<html>

<head>
    <title>Hello world</title>
    <meta name="layout" content="bootstrap"/>
    <r:require module="plugin-page"/>
</head>

<body>

<div class="container">
    <h2><g:message code="resources.recent"/></h2>

    <table class="table">
        <thead>
        <tr>
            <th class="thin"><g:message code="plugin.resource.submitted"/></th>
            <th class="thin"><g:message code="plugin.resource.source"/></th>
            <th><g:message code="plugin.resource.title"/></th>
        </tr>
        </thead>

        <g:each in="${resources}" var="r">
            <tr>
                <g:render template="/toolbox/resource_submitted_from_title" model="[resource: r]"/>
            </tr>
        </g:each>
    </table>
</div>



</body>

</html>
