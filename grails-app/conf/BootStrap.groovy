import grails.util.Environment
import ru.gramant.grails.toolbox.Feed

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {
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
