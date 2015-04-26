package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    void "test de la validatité d'un user"() {

        given: "Un utilisateur"
        User user = new User()

        expect: "Le utilisateur est valide"
        user.validate()
    }
}
