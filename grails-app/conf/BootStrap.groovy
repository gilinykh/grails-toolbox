import grails.util.Environment
import ru.gramant.grails.toolbox.Feed

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {
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
