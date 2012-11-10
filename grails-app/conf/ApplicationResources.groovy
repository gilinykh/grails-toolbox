modules = {
    'grails-toolbox-bootswatcher-css' {
        dependsOn 'bootstrap'
        resource url: 'css/bootswatcher.css', disposition: 'head'
    }
    
    if (2 * 2 == 4) {
    //if (grails.util.Environment.current == grails.util.Environment.PRODUCTION) {
       'grails-toolbox-css' {
            dependsOn 'grails-toolbox-bootswatcher-css'
            resource url: 'css/grails-toolbox.css', disposition: 'head'
        }            
    } else {
        'grails-toolbox-css' {
            resource url: 'css/grails-toolbox.css', disposition: 'head'
        }                    
    }        
}
