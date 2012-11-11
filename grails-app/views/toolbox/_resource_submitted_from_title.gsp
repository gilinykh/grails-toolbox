<%@ page import="ru.gramant.grails.toolbox.ResourceType" %>
<td>
    <span class="submitted" original-title="">
        <tb:formatDateTime value="${resource.publishedDate}"/>
    </span>
    <g:if test="${resource.author}">
        <br>
        <small class="nowrap">by ${resource.author?.encodeAsHTML()}</small>
    </g:if>
</td>
<td>
    <i class="icon-${[(ResourceType.FEED): 'rss', (ResourceType.MAIL): 'envelope', (ResourceType.STACKOVERFLOW): 'signal'][resource.type]}"></i>
    <g:message code="resource.type.${resource.type}"/>
</td>
<td><a href="${resource.link}" target="_blank">${resource.title.encodeAsHTML()}</a></td>