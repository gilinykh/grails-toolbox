/*
 * TestController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import java.util.regex.Pattern
import java.util.regex.Matcher
import org.joda.time.DateTime

class TestController {

    def pluginInfoLoaderService

    def feedService

    def index = {
        pluginInfoLoaderService.updateInfoFromPluginListFile()

        render "ok"
    }

    def mailFeed = {
        feedService.parseEmailDump()

        render "ok"
    }

    def feed = {
//        Feed.findOrCreateWhere(type: ResourceType.FEED, title: 'grails.ru', url: 'http://grails.ru/feed/').save()
//        Feed.findOrCreateWhere(type: ResourceType.STACKOVERFLOW, title: 'stackoverflow grails', url: 'http://stackoverflow.com/feeds/tag?tagnames=grails&sort=newest').save()
//        Feed.findOrCreateWhere(type: ResourceType.MAIL, title: 'grails - user - with replies', url: 'http://grails.1312388.n4.nabble.com/Grails-user-f1312389.xml').save()

        feedService.parseAllFeedsAndMatch()

        render "ok"
    }

    def main = {
        def categories = Category.list()
        def plugins = Plugin.list()
        def categoryList = []
        categories.collect { c ->
            def cp = plugins.grep { it.categories.contains(c) }
            def cd = [categoryName: c.title]
            def pluginList = []
            cp.each { p ->
                def pd = [:]
                pd.code = p.name
                pd.release = p.latestRelease?.releaseVersion ?: 'unknown'
                pd.releases = p.releases.size()
                pd.authors = p.author?.username ?: 'unknown'
                int rate5 = (int) (50 * p.rating)
                pd.rate5 = rate5
                pd.ratings = p.ratingCount
                def rml = ResourceMatcher.findAllByPlugin(p)
                def news = []
                rml.each { rm ->
                    def r = rm.resource
                    if (r.instanceOf(FeedEntry)) {
                        def m = [:]
                        def f = (FeedEntry) r
                        m.date = f.publishedDate.toString('dd-MM-yy')
                        m.origin = r.type.toString().toLowerCase().capitalize()
                        m.title = f.title
                        news << m
                    }
                }
                pd.news = news

                if (pd.releases) {
                    pluginList << pd
                }
            }
            cd.pluginList = pluginList.sort { it.rate5 + 5 * Math.max(0, Math.log(it.ratings) - 0.8) }.reverse()/*.take(10)*/
            categoryList << cd
        }

//        def acegi = [code: 'acegi', authors: 'Tsuyoshi Yamamoto', release: '0.5.3.2', rating: '4.0/5', ratings: 37,
//                news: [[date: 'Oct 31 09', title: 'Grails Acegi plugin lost password', origin: 'stackoverflow']]]
//
//        def springsec = [code: 'spring-security-core', authors: 'Burt Beckwith', release: '1.2.7.3', rating: '4.5/5', ratings: 66,
//                usage: '66%', news: [[date: 'Sep 19 12', title: 'Grails and redirect with spring security core', origin: 'stackoverflow']]]


        render view: '/toolbox/index', model: [useLargeHeader: true, categoryList: categoryList]
    }

    def plugin = {
        render view: '/toolbox/plugin_static'
    }

    def mailList = {
        def list = []
        try {
            new File('data/grails-mailing-list-dump.xml').eachLine { line ->
                list << new String(line.decodeBase64())
                throw new RuntimeException()
            }
        } catch (Exception e) {

        }

//        def linkMatcher = (list[0] =~ /(?m)(?s).*(http:\/\/grails\.\d+\.n\d+\.nabble\.com\/([^\.]+)tp\d+p\d+\.html).*/)
//        render ((linkMatcher.matches() as String) + ' ' + linkMatcher[0][1] + linkMatcher[0][2]).encodeAsHTML()

        def pluginName = 'spring-security-core'

        def text = "My Spring security Core is so great!"

        def pluginRe = pluginName.toLowerCase().replaceAll('-', '[\\\\- ]')
        def peString = "\\b${pluginRe}\\b"

        System.out.println peString

        Pattern pattern = Pattern.compile(peString)
        Matcher matcher = pattern.matcher(text?.toLowerCase())

        render matcher.find()
//        render list
    }

    def loadGspData() {
        def mailFeed = Feed.findOrCreateWhere(type: ResourceType.MAIL, title: 'grails - user - with replies', url: 'http://grails.1312388.n4.nabble.com/Grails-user-f1312389.xml').save()
        def focr = {link, title ->
            if (!FeedEntry.findByFeedAndLink(mailFeed, link)) {
                def entry = new FeedEntry()
                entry.feed = mailFeed
                entry.author = 'Mail'
                entry.link = link
                entry.title = title
                entry.publishedDate = new DateTime()
                entry.categories = []
                entry.description = title
                entry.save()
            }
        }

        new ResourceMatcher(plugin: Plugin.findByName('bean-fields'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Grails-development-is-really-slow-tp4257651p4263023.html', 'Grails development is really slow ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('oauth'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/OAuth-and-Grails-tp4534616p4563470.html', 'OAuth and Grails ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('oauth'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/OAuth-and-Grails-tp4534616p4563470.html', 'OAuth and Grails ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mail'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Grails-Mail-in-utility-classes-tp4076282p4076282.html', 'Grails Mail in utility classes ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mail'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Grails-Mail-in-utility-classes-tp4076282p4076282.html', 'Grails Mail in utility classes ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('pm'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/grails-maven-archetype-for-grails-2-0-tp4370651p4370651.html', 'grails maven archetype for grails 2 0 ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('testing'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/grails-maven-archetype-for-grails-2-0-tp4370651p4370651.html', 'grails maven archetype for grails 2 0 ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('prototype'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Spring-security-core-plugin-How-to-access-User-id-from-a-hidden-field-tp4372346p4376254.html', 'Spring security core plugin How to access User id from a hidden field ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('spring-security-core'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Spring-security-core-plugin-How-to-access-User-id-from-a-hidden-field-tp4372346p4376254.html', 'Spring security core plugin How to access User id from a hidden field ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('spring-security-core'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Spring-security-core-plugin-How-to-access-User-id-from-a-hidden-field-tp4372346p4376254.html', 'Spring security core plugin How to access User id from a hidden field ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/weceem-plugin-dependency-broken-tp4635651p4635655.html', 'weceem plugin dependency broken ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('jquery'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/weceem-plugin-dependency-broken-tp4635651p4635655.html', 'weceem plugin dependency broken ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/getting-ApplicationContext-from-src-java-tp4633072p4633077.html', 'getting ApplicationContext from src java ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('pm'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/getting-ApplicationContext-from-src-java-tp4633072p4633077.html', 'getting ApplicationContext from src java ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mongodb'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Grails-development-is-really-slow-tp4257651p4257894.html', 'Grails development is really slow ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('debug'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Grails-development-is-really-slow-tp4257651p4257894.html', 'Grails development is really slow ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('neo4j'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Neo4J-plugin-not-registering-transactionManager-tp4633381p4633398.html', 'Neo4J plugin not registering transactionManager ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('neo4j'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Neo4J-plugin-not-registering-transactionManager-tp4633381p4633398.html', 'Neo4J plugin not registering transactionManager ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('jms'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('jms'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('camel'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('routing'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('routing'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('routing-jms'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('routing-jms'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Read-message-header-using-routing-jms-tp4417253p4417253.html', 'Read message header using routing jms ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mongodb'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Can-t-connect-to-mongodb-on-cloud-foundry-tp4633861p4633954.html', 'Can t connect to mongodb on cloud foundry ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mongodb'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Can-t-connect-to-mongodb-on-cloud-foundry-tp4633861p4633954.html', 'Can t connect to mongodb on cloud foundry ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('javascript'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Processing-i18n-messages-in-JavaScript-in-Grails-2-0-tp4291122p4291122.html', 'Processing i18n messages in JavaScript in Grails 2 0 ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('javascript'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Processing-i18n-messages-in-JavaScript-in-Grails-2-0-tp4291122p4291122.html', 'Processing i18n messages in JavaScript in Grails 2 0 ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('javascript'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Charting-plugin-suggestion-tp4358248p4358567.html', 'Charting plugin suggestion ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('jqplot'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Charting-plugin-suggestion-tp4358248p4358567.html', 'Charting plugin suggestion ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('pm'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Problem-with-Cloud-Foundry-Plugin-tp4299226p4299267.html', 'Problem with Cloud Foundry Plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('hibernate-filter'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/hibernate-filter-plugin-Filters-defined-in-class-hierarchies-tp4433856p4433856.html', 'hibernate filter plugin Filters defined in class hierarchies ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('hibernate-filter'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/hibernate-filter-plugin-Filters-defined-in-class-hierarchies-tp4433856p4433856.html', 'hibernate filter plugin Filters defined in class hierarchies ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('testing'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Integration-tests-and-testing-controllers-tp4631352p4631359.html', 'Integration tests and testing controllers ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('testing'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Integration-tests-and-testing-controllers-tp4631352p4631359.html', 'Integration tests and testing controllers ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mail'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Difficulties-in-installing-Nimble-tp4020457p4020457.html', 'Difficulties in installing Nimble ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('nimble'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Difficulties-in-installing-Nimble-tp4020457p4020457.html', 'Difficulties in installing Nimble ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('nimble'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Difficulties-in-installing-Nimble-tp4020457p4020457.html', 'Difficulties in installing Nimble ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('oauth'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Acessing-request-contents-in-oauth-plugin-tp4228813p4243626.html', 'Acessing request contents in oauth plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('oauth'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Acessing-request-contents-in-oauth-plugin-tp4228813p4243626.html', 'Acessing request contents in oauth plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('authentication'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Custom-password-hashing-algorithm-with-Spring-Security-tp4637085p4637093.html', 'Custom password hashing algorithm with Spring Security ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('database-migration'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/cobertura-plugin-and-database-migration-excluding-migration-scripts-from-code-coverage-tp4322376p4323076.html', 'cobertura plugin and database migration excluding migration scripts from code coverage ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('database-migration'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/cobertura-plugin-and-database-migration-excluding-migration-scripts-from-code-coverage-tp4322376p4323076.html', 'cobertura plugin and database migration excluding migration scripts from code coverage ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('code-coverage'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/cobertura-plugin-and-database-migration-excluding-migration-scripts-from-code-coverage-tp4322376p4323076.html', 'cobertura plugin and database migration excluding migration scripts from code coverage ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('code-coverage'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/cobertura-plugin-and-database-migration-excluding-migration-scripts-from-code-coverage-tp4322376p4323076.html', 'cobertura plugin and database migration excluding migration scripts from code coverage ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('javascript'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('debug'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('lesscss-resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('less-resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('less-resources'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Basic-question-on-less-resources-plugin-tp4322940p4322940.html', 'Basic question on less resources plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mongodb'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Does-id-generator-uuid-work-with-the-mongodb-plugin-tp4631456p4631465.html', 'Does id generator uuid work with the mongodb plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('mongodb'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/Does-id-generator-uuid-work-with-the-mongodb-plugin-tp4631456p4631465.html', 'Does id generator uuid work with the mongodb plugin ')).save()
        new ResourceMatcher(plugin: Plugin.findByName('database-migration'), matchLevel: MatchLevel.MINOR, resource: focr('http://grails.1312388.n4.nabble.com/admin-mysql-service-in-cloud-foundry-tp4636007p4636063.html', 'admin mysql service in cloud foundry ')).save()
    }

}
