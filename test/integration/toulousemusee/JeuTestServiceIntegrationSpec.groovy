package toulousemusee

import spock.lang.Specification

/**
 * Created by tetote on 22/04/15.
 */
class JeuTestServiceIntegrationSpec extends Specification {

    JeuTestService jeuTestService

    void "test creation jeu de tests pour museum"() {
        given: "une base ne contenant pas de museum"
        Museum.count() == 0

        when: "on crée le jeu de test pour les museums"
        jeuTestService.createJeuTestForMuseum()

        then: "12 nouveaux museums ont été crées en base"
        Museum.count() == 12

        and: "4 nouveaux manager ont été crées en base"
        Manager.count() == 4

        and: "12 nouveaux address ont été crées en base"
        Address.count() == 12


        when:"des museums exitent deja dans la base"
        Museum.count() == 12

        and:"on déclenche à nouveau la création du jeu de test pour museum"
        jeuTestService.createJeuTestForMuseum()

        then:"aucun museum n'est crée"
        Museum.count() == 12
    }

    void "test creation jeu de tests pour user"() {
        given: "une base ne contenant pas de user"
        User.count() == 0

        when: "on crée le jeu de test pour le user"
        jeuTestService.createUser()

        then: "1 user a été crée en base"
        User.count() == 1


        when:"Un user exite deja dans la base"
        User.count() == 1

        and:"on déclenche à nouveau la création du jeu de test pour user"
        jeuTestService.createUser()

        then:"aucun user n'est crée"
        User.count() == 1
    }
}
