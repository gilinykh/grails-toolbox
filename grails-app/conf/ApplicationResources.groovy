modules = {
    'grails-toolbox-bootswatcher-css' {
        dependsOn 'bootstrap'
        resource url: 'css/bootswatcher.css', disposition: 'head'
    }
    
    'grails-toolbox-css' {
        dependsOn 'grails-toolbox-bootswatcher-css'
        resource url: 'css/grails-toolbox.css', disposition: 'head'    
    }    
}