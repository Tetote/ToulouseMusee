package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    @Unroll
    void "test de la validatite d'un user"() {

        given: "Un utilisateur"
        User user = new User()

        expect: "L'utilisateur est valide"
        user.validate()
    }
}
