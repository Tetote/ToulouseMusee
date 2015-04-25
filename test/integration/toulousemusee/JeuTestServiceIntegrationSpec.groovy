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

        then:"aucune nouvelle activité n'est crée"
        Museum.count() == 12
    }

}
