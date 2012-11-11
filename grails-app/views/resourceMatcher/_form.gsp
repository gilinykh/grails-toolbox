<%@ page import="ru.gramant.grails.toolbox.ResourceMatcher" %>



			<div class="control-group fieldcontain ${hasErrors(bean: resourceMatcherInstance, field: 'matchLevel', 'error')} required">
				<label for="matchLevel" class="control-label"><g:message code="resourceMatcher.matchLevel.label" default="Match Level" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:select name="matchLevel" from="${ru.gramant.grails.toolbox.MatchLevel?.values()}" keys="${ru.gramant.grails.toolbox.MatchLevel.values()*.name()}" required="" value="${resourceMatcherInstance?.matchLevel?.name()}"/>
					<span class="help-inline">${hasErrors(bean: resourceMatcherInstance, field: 'matchLevel', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: resourceMatcherInstance, field: 'plugin', 'error')} required">
				<label for="plugin" class="control-label"><g:message code="resourceMatcher.plugin.label" default="Plugin" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:select id="plugin" name="plugin.id" from="${ru.gramant.grails.toolbox.Plugin.list()}" optionKey="id" required="" value="${resourceMatcherInstance?.plugin?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: resourceMatcherInstance, field: 'plugin', 'error')}</span>
				</div>
			</div>

			<div class="control-group fieldcontain ${hasErrors(bean: resourceMatcherInstance, field: 'resource', 'error')} required">
				<label for="resource" class="control-label"><g:message code="resourceMatcher.resource.label" default="Resource" /><span class="required-indicator">*</span></label>
				<div class="controls">
					<g:select id="resource" name="resource.id" from="${ru.gramant.grails.toolbox.Resource.list()}" optionKey="id" required="" value="${resourceMatcherInstance?.resource?.id}" class="many-to-one"/>
					<span class="help-inline">${hasErrors(bean: resourceMatcherInstance, field: 'resource', 'error')}</span>
				</div>
			</div>

