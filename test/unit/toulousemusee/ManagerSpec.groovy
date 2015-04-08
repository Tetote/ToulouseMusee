package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Manager)
class ManagerSpec extends Specification {

    @Unroll
    void "test la validite d'un manager valide"(String name, def _) {
        given: "un manager initialise avec un nom non vide"
        Manager manager = new Manager(name: name)

        expect: "le manager est valide"
        manager.validate()

        where:
        name                 | _
        "Mairie de Toulouse" | _
    }

    @Unroll
    void "test l'invalidite d'un manager non valide"(String name, def _) {
        given: "un manager initialise avec un nom vide ou null"
        Manager manager = new Manager(name: name)

        expect: "le manager est invalide"
        !manager.validate()

        where:
        name | _
        null | _
        ""   | _
    }
}
