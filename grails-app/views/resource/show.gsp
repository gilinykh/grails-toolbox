
<%@ page import="ru.gramant.grails.toolbox.FeedEntry" %>
<!doctype html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="layout" content="kickstart" />
	<g:set var="entityName" value="${message(code: 'feedEntry.label', default: 'FeedEntry')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-feedEntry" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.description.label" default="Description" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: feedEntryInstance, field: "description")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.author.label" default="Author" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: feedEntryInstance, field: "author")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.categories.label" default="Categories" /></td>
				
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${feedEntryInstance.categories}" var="c">
						<li><g:link controller="feedCategory" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.feed.label" default="Feed" /></td>
				
				<td valign="top" class="value"><g:link controller="feed" action="show" id="${feedEntryInstance?.feed?.id}">${feedEntryInstance?.feed?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.link.label" default="Link" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: feedEntryInstance, field: "link")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.publishedDate.label" default="Published Date" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: feedEntryInstance, field: "publishedDate")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.title.label" default="Title" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: feedEntryInstance, field: "title")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="feedEntry.type.label" default="Type" /></td>
				
				<td valign="top" class="value">${feedEntryInstance?.type?.encodeAsHTML()}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
