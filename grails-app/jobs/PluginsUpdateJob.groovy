class PluginsUpdateJob {

    def pluginInfoLoaderService

    //don't run this job concurrently
    def concurrent = false

    static triggers = {
        simple name: 'pluginsUpdateJobTrigger', startDelay: 10*1000, repeatInterval: 3*60*60*1000
    }
    def group = "toolbox"

    def execute(){
//        pluginInfoLoaderService.updateInfoFromPluginListFile()
    }
}