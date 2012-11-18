class UserFilters {

    def cookieService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                def user = cookieService.get('userId')
                if (!user) {
                    user = System.currentTimeMillis()
                    cookieService.set(request, response, 'userId', user)
                }

                request.userGeneratedId = user

                return true
            }
        }
    }
}