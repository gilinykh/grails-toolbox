<%@ page import="ru.gramant.grails.toolbox.FeedCategory" %>



			<div class="control-group fieldcontain ${hasErrors(bean: feedCategoryInstance, field: 'title', 'error')} ">
				<label for="title" class="control-label"><g:message code="feedCategory.title.label" default="Title" /></label>
				<div class="controls">
					<g:textField name="title" value="${feedCategoryInstance?.title}"/>
					<span class="help-inline">${hasErrors(bean: feedCategoryInstance, field: 'title', 'error')}</span>
				</div>
			</div>

