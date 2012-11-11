<%@ page import="ru.gramant.grails.toolbox.FeedEntry" %>
<!doctype html>
<html>
<head>
  <meta name="layout" content="bootstrap">
  <g:set var="entityName" value="${message(code: 'feedEntry.label', default: 'FeedEntry')}"/>
  <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

  <div class="span3">
    <div class="well">
      <ul class="nav nav-list">
        <li class="nav-header">${entityName}</li>
        <li>
          <g:link class="list" action="list">
            <i class="icon-list"></i>
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
      <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:if test="${flash.message}">
      <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
    </g:if>

    <dl>

    <g:if test="${feedEntryInstance?.title}">
      <dt><g:message code="feedEntry.title.label" default="Title"/></dt>

      <dd><g:fieldValue bean="${feedEntryInstance}" field="title"/></dd>

    </g:if>

    <g:if test="${feedEntryInstance?.feed}">
      <dt><g:message code="feedEntry.feed.label" default="Feed"/></dt>

      <dd><g:link controller="feed" action="show"
                  id="${feedEntryInstance?.feed?.id}">${feedEntryInstance?.feed?.encodeAsHTML()}</g:link></dd>

    </g:if>

      <g:if test="${feedEntryInstance?.description}">
        <dt><g:message code="feedEntry.description.label" default="Description"/></dt>

        <dd><g:fieldValue bean="${feedEntryInstance}" field="description"/></dd>

      </g:if>

      <g:if test="${feedEntryInstance?.author}">
        <dt><g:message code="feedEntry.author.label" default="Author"/></dt>

        <dd><g:fieldValue bean="${feedEntryInstance}" field="author"/></dd>

      </g:if>

      <g:if test="${feedEntryInstance?.categories}">
        <dt><g:message code="feedEntry.categories.label" default="Categories"/></dt>

        <g:each in="${feedEntryInstance.categories}" var="c">
          <dd><g:link controller="feedCategory" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></dd>
        </g:each>

      </g:if>

      <g:if test="${feedEntryInstance?.link}">
        <dt><g:message code="feedEntry.link.label" default="Link"/></dt>

        <dd><g:fieldValue bean="${feedEntryInstance}" field="link"/></dd>

      </g:if>

      <g:if test="${feedEntryInstance?.plugins}">
        <dt><g:message code="feedEntry.plugins.label" default="Plugins"/></dt>

        <g:each in="${feedEntryInstance.plugins}" var="p">
          <dd><g:link controller="resourceMatcher" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
        </g:each>

      </g:if>

      <g:if test="${feedEntryInstance?.publishedDate}">
        <dt><g:message code="feedEntry.publishedDate.label" default="Published Date"/></dt>

        <dd><g:fieldValue bean="${feedEntryInstance}" field="publishedDate"/></dd>

      </g:if>

      <g:if test="${feedEntryInstance?.type}">
        <dt><g:message code="feedEntry.type.label" default="Type"/></dt>

        <dd><g:fieldValue bean="${feedEntryInstance}" field="type"/></dd>

      </g:if>

    </dl>

    <g:form>
      <g:hiddenField name="id" value="${feedEntryInstance?.id}"/>
      <div class="form-actions">
        <g:link class="btn" action="edit" id="${feedEntryInstance?.id}">
          <i class="icon-pencil"></i>
          <g:message code="default.button.edit.label" default="Edit"/>
        </g:link>
        <button class="btn btn-danger" type="submit" name="_action_delete">
          <i class="icon-trash icon-white"></i>
          <g:message code="default.button.delete.label" default="Delete"/>
        </button>
      </div>
    </g:form>

  </div>

</div>
</body>
</html>
