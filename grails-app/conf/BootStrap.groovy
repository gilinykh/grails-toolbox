import grails.util.Environment
import ru.gramant.grails.toolbox.Feed
import org.joda.time.DateTime
import ru.gramant.grails.toolbox.ResourceType
import ru.gramant.grails.toolbox.FeedEntry
import ru.gramant.grails.toolbox.Plugin
import ru.gramant.grails.toolbox.ResourceMatcher
import ru.gramant.grails.toolbox.MatchLevel

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {
                def plugins = []
                (1..20).each{ num ->
                    plugins << new Plugin(name: "Plugin$num").save(failOnError: true)
                }

                Feed feed = new Feed(title: 'grails.ru', url: 'http://grails.ru/feed/').save(failOnError: true)
                (1..100).each{ num ->
                    long millis = System.currentTimeMillis() - (1000 * 60 * 60 * 24 * (100-num))
                    def post = new FeedEntry(feed: feed, publishedDate: new DateTime(millis), link: "Link$num", title: "Title$num").save(failOnError: true)
                    Collections.shuffle(plugins)
                    new ResourceMatcher(plugin: plugins[0], resource: post, matchLevel: MatchLevel.MINOR).save(failOnError: true)
                }
            }
            
            staging {
                Feed.findOrCreateWhere(title: 'grails.ru', url: 'http://grails.ru/feed/').save()
            }
            
            production {
                
            }
        }
    }
    def destroy = {
    }
}
