package ru.gramant.grails.toolbox
class LandingController {
    def index = {
        forward(controller: 'test', action: 'main')
    }

    def categories = {

        def acegi = [code: 'acegi', authors: 'Tsuyoshi Yamamoto', release: '0.5.3.2', rating: '4.0/5', ratings: 37,
        news: [[date: 'Oct 31 09', title: 'Grails Acegi plugin lost password', origin: 'stackoverflow']]]

        def springsec = [code: 'spring-security-core', authors: 'Burt Beckwith', release: '1.2.7.3', rating: '4.5/5', ratings: 66,
                usage: '66%', news: [[date: 'Sep 19 12', title: 'Grails and redirect with spring security core', origin: 'stackoverflow']]]
        [pluginData: [acegi, springsec]]
    }

    def recent = {
        []
    }
}