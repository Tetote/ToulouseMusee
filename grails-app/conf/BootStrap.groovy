import toulousemusee.JeuTestService

class BootStrap {

    JeuTestService jeuTestService

    def init = { servletContext ->

        jeuTestService.createJeuTestForMuseum()
        jeuTestService.createUser()
    }
    def destroy = {
    }
}
