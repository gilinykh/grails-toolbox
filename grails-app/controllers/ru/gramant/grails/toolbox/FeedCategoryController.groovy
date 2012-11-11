package ru.gramant.grails.toolbox

import org.springframework.dao.DataIntegrityViolationException

class FeedCategoryController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [feedCategoryInstanceList: FeedCategory.list(params), feedCategoryInstanceTotal: FeedCategory.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [feedCategoryInstance: new FeedCategory(params)]
                break
            case 'POST':
                def feedCategoryInstance = new FeedCategory(params)
                if (!feedCategoryInstance.save(flush: true)) {
                    render view: 'create', model: [feedCategoryInstance: feedCategoryInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), feedCategoryInstance.id])
                redirect action: 'show', id: feedCategoryInstance.id
                break
        }
    }

    def show() {
        def feedCategoryInstance = FeedCategory.get(params.id)
        if (!feedCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), params.id])
            redirect action: 'list'
            return
        }

        [feedCategoryInstance: feedCategoryInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def feedCategoryInstance = FeedCategory.get(params.id)
                if (!feedCategoryInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), params.id])
                    redirect action: 'list'
                    return
                }

                [feedCategoryInstance: feedCategoryInstance]
                break
            case 'POST':
                def feedCategoryInstance = FeedCategory.get(params.id)
                if (!feedCategoryInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (feedCategoryInstance.version > version) {
                        feedCategoryInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                            [message(code: 'feedCategory.label', default: 'FeedCategory')] as Object[],
                            "Another user has updated this FeedCategory while you were editing")
                        render view: 'edit', model: [feedCategoryInstance: feedCategoryInstance]
                        return
                    }
                }

                feedCategoryInstance.properties = params

                if (!feedCategoryInstance.save(flush: true)) {
                    render view: 'edit', model: [feedCategoryInstance: feedCategoryInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), feedCategoryInstance.id])
                redirect action: 'show', id: feedCategoryInstance.id
                break
        }
    }

    def delete() {
        def feedCategoryInstance = FeedCategory.get(params.id)
        if (!feedCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), params.id])
            redirect action: 'list'
            return
        }

        try {
            feedCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'feedCategory.label', default: 'FeedCategory'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
