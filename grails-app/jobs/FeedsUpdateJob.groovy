class FeedsUpdateJob {

    def feedService

    //don't run this job concurrently
    def concurrent = false

    static triggers = {
        simple name: 'feedsUpdateJobTrigger', startDelay: 40*1000, repeatInterval: 15*60*1000
    }

    def group = "toolbox"

    def execute(){
        feedService.parseAllFeedsAndMatch()
    }
}