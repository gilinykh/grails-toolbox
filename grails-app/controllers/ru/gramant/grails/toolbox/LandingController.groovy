package ru.gramant.grails.toolbox
class LandingController {
    def index = {
        redirect(action: 'categories')
    }

    def categories = {
        []
    }

    def recent = {
        []
    }
}