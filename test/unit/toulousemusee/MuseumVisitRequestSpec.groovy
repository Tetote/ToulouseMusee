package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(MuseumVisitRequest)
class MuseumVisitRequestSpec extends Specification {

    @Unroll
    void "test de validite d'une museumVisitRequest valide"(Date requestDate, def _) {
        given: "une museumVisitRequest initialise avec une requestDate, un museum et une visitRequest"
        MuseumVisitRequest museumVisitRequest = new MuseumVisitRequest(
                requestDate: requestDate,
                museum: Mock(Museum),
                visitRequest: Mock(VisitRequest))

        expect: "la museumVisitRequest est valide"
        museumVisitRequest.validate()

        where:
        requestDate          | _
        new Date(2015,02,02) | _
    }

    @Unroll
    void "test d'invalidite d'une museumVisitRequest non valide"(Date requestDate, Museum museum, VisitRequest visitRequest) {
        given: "une museumVisitRequest initialise avec une requestDate, un museum et une visitRequest"
        MuseumVisitRequest museumVisitRequest = new MuseumVisitRequest(
                requestDate: requestDate,
                museum: museum,
                visitRequest: visitRequest)

        expect: "la museumVisitRequest est non valide"
        !museumVisitRequest.validate()

        where:
        requestDate          | museum       | visitRequest
        null                 | Mock(Museum) | Mock(VisitRequest)
        new Date(2015,02,02) | null         | Mock(VisitRequest)
        new Date(2015,02,02) | Mock(Museum) | null
    }

    @Unroll
    void "test toString"() {
        given: "une museumVisitRequest"
        Date requestDate = new Date(2015,02,02)
        MuseumVisitRequest museumVisitRequest = new MuseumVisitRequest(requestDate: requestDate)

        when: "on veut l'afficher"
        String toString = museumVisitRequest.toString()

        then: "le toString est bien affiche"
        toString == ""+requestDate
    }
}
