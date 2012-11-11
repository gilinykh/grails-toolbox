<%@ page import="ru.gramant.grails.toolbox.Category" %>



			<div class="control-group fieldcontain ${hasErrors(bean: categoryInstance, field: 'title', 'error')} required">
				<label for="title" class="control-label"><g:message code="category.title.label" default="Title" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:textField name="title" required="" value="${categoryInstance?.title}"/>
					<span class="help-inline">${hasErrors(bean: categoryInstance, field: 'title', 'error')}</span>
				</div>
			</div>

