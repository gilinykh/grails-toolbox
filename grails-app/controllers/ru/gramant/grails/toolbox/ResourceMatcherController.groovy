package ru.gramant.grails.toolbox

import org.springframework.dao.DataIntegrityViolationException

class ResourceMatcherController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [resourceMatcherInstanceList: ResourceMatcher.list(params), resourceMatcherInstanceTotal: ResourceMatcher.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [resourceMatcherInstance: new ResourceMatcher(params)]
                break
            case 'POST':
                def resourceMatcherInstance = new ResourceMatcher(params)
                if (!resourceMatcherInstance.save(flush: true)) {
                    render view: 'create', model: [resourceMatcherInstance: resourceMatcherInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), resourceMatcherInstance.id])
                redirect action: 'show', id: resourceMatcherInstance.id
                break
        }
    }

    def show() {
        def resourceMatcherInstance = ResourceMatcher.get(params.id)
        if (!resourceMatcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), params.id])
            redirect action: 'list'
            return
        }

        [resourceMatcherInstance: resourceMatcherInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def resourceMatcherInstance = ResourceMatcher.get(params.id)
                if (!resourceMatcherInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), params.id])
                    redirect action: 'list'
                    return
                }

                [resourceMatcherInstance: resourceMatcherInstance]
                break
            case 'POST':
                def resourceMatcherInstance = ResourceMatcher.get(params.id)
                if (!resourceMatcherInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (resourceMatcherInstance.version > version) {
                        resourceMatcherInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                            [message(code: 'resourceMatcher.label', default: 'ResourceMatcher')] as Object[],
                            "Another user has updated this ResourceMatcher while you were editing")
                        render view: 'edit', model: [resourceMatcherInstance: resourceMatcherInstance]
                        return
                    }
                }

                resourceMatcherInstance.properties = params

                if (!resourceMatcherInstance.save(flush: true)) {
                    render view: 'edit', model: [resourceMatcherInstance: resourceMatcherInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), resourceMatcherInstance.id])
                redirect action: 'show', id: resourceMatcherInstance.id
                break
        }
    }

    def delete() {
        def resourceMatcherInstance = ResourceMatcher.get(params.id)
        if (!resourceMatcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), params.id])
            redirect action: 'list'
            return
        }

        try {
            resourceMatcherInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'resourceMatcher.label', default: 'ResourceMatcher'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
