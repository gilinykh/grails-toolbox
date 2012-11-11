<%@ page import="ru.gramant.grails.toolbox.FeedEntry" %>



<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'description', 'error')} ">
  <label for="description" class="control-label"><g:message code="feedEntry.description.label"
                                                            default="Description"/></label>

  <div class="controls">
    <g:textField name="description" value="${feedEntryInstance?.description}"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'description', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'author', 'error')} ">
  <label for="author" class="control-label"><g:message code="feedEntry.author.label" default="Author"/></label>

  <div class="controls">
    <g:textField name="author" value="${feedEntryInstance?.author}"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'author', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'categories', 'error')} ">
  <label for="categories" class="control-label"><g:message code="feedEntry.categories.label"
                                                           default="Categories"/></label>

  <div class="controls">
    <g:select name="categories" from="${ru.gramant.grails.toolbox.FeedCategory.list()}" multiple="multiple"
              optionKey="id" size="5" value="${feedEntryInstance?.categories*.id}" class="many-to-many"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'categories', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'feed', 'error')} required">
  <label for="feed" class="control-label"><g:message code="feedEntry.feed.label" default="Feed"/><span
      class="required-indicator">*</span></label>

  <div class="controls">
    <g:select id="feed" name="feed.id" from="${ru.gramant.grails.toolbox.Feed.list()}" optionKey="id" required=""
              value="${feedEntryInstance?.feed?.id}" class="many-to-one"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'feed', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'link', 'error')} ">
  <label for="link" class="control-label"><g:message code="feedEntry.link.label" default="Link"/></label>

  <div class="controls">
    <g:textField name="link" value="${feedEntryInstance?.link}"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'link', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'plugins', 'error')} ">
  <label for="plugins" class="control-label"><g:message code="feedEntry.plugins.label" default="Plugins"/></label>

  <div class="controls">

    <ul class="one-to-many">
      <g:each in="${feedEntryInstance?.plugins ?}" var="p">
        <li><g:link controller="resourceMatcher" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
      </g:each>
      <li class="add">
        <g:link controller="resourceMatcher" action="create"
                params="['feedEntry.id': feedEntryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher')])}</g:link>
      </li>
    </ul>

    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'plugins', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'publishedDate', 'error')} required">
  <label for="publishedDate" class="control-label"><g:message code="feedEntry.publishedDate.label"
                                                              default="Published Date"/><span
      class="required-indicator">*</span></label>

  <div class="controls">

    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'publishedDate', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'title', 'error')} ">
  <label for="title" class="control-label"><g:message code="feedEntry.title.label" default="Title"/></label>

  <div class="controls">
    <g:textField name="title" value="${feedEntryInstance?.title}"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'title', 'error')}</span>
  </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: feedEntryInstance, field: 'type', 'error')} required">
  <label for="type" class="control-label"><g:message code="feedEntry.type.label" default="Type"/><span
      class="required-indicator">*</span></label>

  <div class="controls">
    <g:select name="type" from="${ru.gramant.grails.toolbox.ResourceType?.values()}"
              keys="${ru.gramant.grails.toolbox.ResourceType.values()*.name()}" required=""
              value="${feedEntryInstance?.type?.name()}"/>
    <span class="help-inline">${hasErrors(bean: feedEntryInstance, field: 'type', 'error')}</span>
  </div>
</div>

