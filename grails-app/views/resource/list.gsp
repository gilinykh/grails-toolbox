
<%@ page import="ru.gramant.grails.toolbox.FeedEntry" %>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'feedEntry.label', default: 'FeedEntry')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>

<body>
	
<section id="list-feedEntry" class="first">

	<table class="table table-bordered">
		<thead>
			<tr>
			
				<g:sortableColumn property="description" title="${message(code: 'feedEntry.description.label', default: 'Description')}" />
			
				<g:sortableColumn property="author" title="${message(code: 'feedEntry.author.label', default: 'Author')}" />
			
				<th><g:message code="feedEntry.feed.label" default="Feed" /></th>
			
				<g:sortableColumn property="link" title="${message(code: 'feedEntry.link.label', default: 'Link')}" />
			
				<g:sortableColumn property="publishedDate" title="${message(code: 'feedEntry.publishedDate.label', default: 'Published Date')}" />
			
				<g:sortableColumn property="title" title="${message(code: 'feedEntry.title.label', default: 'Title')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${feedEntryInstanceList}" status="i" var="feedEntryInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${feedEntryInstance.id}">${fieldValue(bean: feedEntryInstance, field: "description")}</g:link></td>
			
				<td>${fieldValue(bean: feedEntryInstance, field: "author")}</td>
			
				<td>${fieldValue(bean: feedEntryInstance, field: "feed")}</td>
			
				<td>${fieldValue(bean: feedEntryInstance, field: "link")}</td>
			
				<td>${fieldValue(bean: feedEntryInstance, field: "publishedDate")}</td>
			
				<td>${fieldValue(bean: feedEntryInstance, field: "title")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<bs:paginate total="${feedEntryInstanceTotal}" />
	</div>
</section>

</body>

</html>
