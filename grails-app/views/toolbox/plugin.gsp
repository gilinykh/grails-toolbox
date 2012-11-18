<%@ page import="ru.gramant.grails.toolbox.ResourceType" %>
<html>

<head>
    <title>Hello world</title>
    <meta name="layout" content="bootstrap"/>
    <r:require module="plugin-page"/>
</head>

<body>

<div id="data" data-plugin="${plugin.id}"></div>

<div class="container">
    <h2>${plugin.name}</h2>
    <p>${plugin.description}</p>
</div>

<g:if test="${resources.size()}">
    <div class="container">
        <h3><g:message code="plugin.resources"/></h3>

        <table class="table">
            <thead>
            <tr>
                <th class="thin"><g:message code="plugin.resource.submitted"/></th>
                <th class="thin"><g:message code="plugin.resource.source"/></th>
                <th><g:message code="plugin.resource.title"/></th>
                <th class="super-thin"></th>
                <th class="super-thin"></th>
            </tr>
            </thead>

            <g:each in="${resources}" var="r">
                <tr>
                    %{--<td>
                        <span class="submitted" original-title="">
                            <tb:formatDateTime value="${r.resource.publishedDate}"/>
                        </span>
                        <g:if test="${r.resource.author}">
                            <br>
                            <small class="nowrap">by ${r.resource.author}</small>
                        </g:if>
                    </td>
                    <td>
                        <i class="icon-${[(ResourceType.FEED): 'rss', (ResourceType.MAIL): 'envelope', (ResourceType.STACKOVERFLOW): 'signal'][r.resource.type]}"></i>
                        <g:message code="resource.type.${r.resource.type}"/>
                    </td>
                    <td><a href="${r.resource.link}" target="_blank">${r.resource.title}</a></td>--}%
                    <g:render template="/toolbox/resource_submitted_from_title" model="[resource: r.resource]"/>
                    <td></td>
                    <td>
                        <g:if test="${!r.matchedByUser}">
                            <a data-resource-id="${r.resource.id}" class="btn btn-warning btn-small should-be-checked" href="#report">!</a>
                        </g:if>
                    </td>
                </tr>
            </g:each>
        </table>

        <a class="add-resource btn pull-right" href="#add-resource" data-toggle="modal"><g:message code="plugin.resource.add"/></a>
    </div>
</g:if>

%{--modal - report--}%
<div id="report" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3><g:message code="plugin.resource.report.label"/></h3>
    </div>
    <div class="modal-body">
        <p><g:message code="plugin.resource.report.message"/></p>
        <textarea rows="3" class="support-comment"></textarea>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal"><g:message code="button.close"/></a>
        <a href="#" class="send btn btn-primary" data-url="${g.createLink(controller: 'user', action: 'pluginResourceReport')}"><g:message code="button.send"/></a>
    </div>
</div>

%{--modal - add new resource--}%
<div id="add-resource" class="modal hide fade">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3><g:message code="plugin.resource.add.label"/></h3>
    </div>
    <div class="modal-body">
        <p><g:message code="plugin.resource.add.message"/></p>
        <input type="text" class="resource-url" placeholder="${g.message(code: 'plugin.resource.add.url.placeholder')}" />
        <textarea rows="3" class="support-comment" placeholder="${g.message(code: 'plugin.resource.add.message.placeholder')}"></textarea>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal"><g:message code="button.close"/></a>
        <a href="#" class="send btn btn-primary" data-url="${g.createLink(controller: 'user', action: 'addResource')}"><g:message code="button.send"/></a>
    </div>
</div>

</body>

</html>
