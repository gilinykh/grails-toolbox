<%@ page import="ru.gramant.grails.toolbox.FeedEntry" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="bootstrap">
  <g:set var="entityName" value="${message(code: 'feedEntry.label', default: 'FeedEntry')}"/>
  <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

  <div class="span3">
    <div class="well">
      <ul class="nav nav-list">
        <li class="nav-header">${entityName}</li>
        <li class="active">
          <g:link class="list" action="list">
            <i class="icon-list icon-white"></i>
            <g:message code="default.list.label" args="[entityName]"/>
          </g:link>
        </li>
        <li>
          <g:link class="create" action="create">
            <i class="icon-plus"></i>
            <g:message code="default.create.label" args="[entityName]"/>
          </g:link>
        </li>
      </ul>
    </div>
  </div>

  <div class="span9">

    <div class="page-header">
      <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:if test="${flash.message}">
      <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
    </g:if>

    <table class="table table-striped">
      <thead>
      <tr>

        <g:sortableColumn property="description"
                          title="${message(code: 'feedEntry.description.label', default: 'Description')}"/>

        <g:sortableColumn property="author" title="${message(code: 'feedEntry.author.label', default: 'Author')}"/>

        <th class="header"><g:message code="feedEntry.feed.label" default="Feed"/></th>

        <g:sortableColumn property="link" title="${message(code: 'feedEntry.link.label', default: 'Link')}"/>

        <g:sortableColumn property="publishedDate"
                          title="${message(code: 'feedEntry.publishedDate.label', default: 'Published Date')}"/>

        <g:sortableColumn property="title" title="${message(code: 'feedEntry.title.label', default: 'Title')}"/>

        <th></th>
      </tr>
      </thead>
      <tbody>
      <g:each in="${feedEntryInstanceList}" var="feedEntryInstance">
        <tr>

          <td>${fieldValue(bean: feedEntryInstance, field: "description")}</td>

          <td>${fieldValue(bean: feedEntryInstance, field: "author")}</td>

          <td>${fieldValue(bean: feedEntryInstance, field: "feed")}</td>

          <td>${fieldValue(bean: feedEntryInstance, field: "link")}</td>

          <td>${fieldValue(bean: feedEntryInstance, field: "publishedDate")}</td>

          <td>${fieldValue(bean: feedEntryInstance, field: "title")}</td>

          <td class="link">
            <g:link action="show" id="${feedEntryInstance.id}" class="btn btn-small">Show &raquo;</g:link>
          </td>
        </tr>
      </g:each>
      </tbody>
    </table>

    <div class="pagination">
      <bootstrap:paginate total="${feedEntryInstanceTotal}"/>
    </div>
  </div>

</div>
</body>
</html>
