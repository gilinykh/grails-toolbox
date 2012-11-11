import grails.util.Environment
import ru.gramant.grails.toolbox.Feed
import org.joda.time.DateTime
import ru.gramant.grails.toolbox.ResourceType
import ru.gramant.grails.toolbox.FeedEntry

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {
//                Feed feed
//                DateTime publishedDate
//                String author
//                String link
//                String title
//                String description
//                ResourceType type = ResourceType.FEED   
//                Feed feed = new Feed(title: 'grails.ru', url: 'http://grails.ru/feed/').save(failOnError: true)
//                (1..100).each{ num ->
//                    long millis = System.currentTimeMillis() - (1000 * 60 * 60 * 24 * (10-num))
//                    new FeedEntry(feed: feed, publishedDate: new DateTime(millis), link: "Link$num", title: "Title$it").save(failOnError: true)
//                }
            }
            
            staging {
                Feed.findOrCreateWhere(type: ResourceType.FEED, title: 'grails.ru', url: 'http://grails.ru/feed/').save()
                Feed.findOrCreateWhere(type: ResourceType.STACKOVERFLOW, title: 'stackoverflow grails', url: 'http://stackoverflow.com/feeds/tag?tagnames=grails&sort=newest').save()
                Feed.findOrCreateWhere(type: ResourceType.MAIL, title: 'grails - user - with replies', url: 'http://grails.1312388.n4.nabble.com/Grails-user-f1312389.xml').save()
            }
            
            production {
                
            }
        }
    }
    def destroy = {
    }
}
