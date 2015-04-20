package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(VisitRequest)
class VisitRequestSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test de validité d'une visitRequest valide"(code, startPeriodDate, endPeriodDate, nbPeople) {
        given: "une visitRequest initialise avec un code, une date de début, une date de fin non null, et un nb de personne >= 1"
        VisitRequest visitRequest = new VisitRequest(
                code: code,
                startPeriodDate: startPeriodDate,
                endPeriodDate: endPeriodDate,
                nbPeople: nbPeople,
                status: VisitRequest.Status.CONFIRMED)

        expect: "la visitRequest est valide"
        visitRequest.save(failOnError: true)
        visitRequest.validate()

        where:
        code    | startPeriodDate           | endPeriodDate             | nbPeople
        1       | new Date(2015, 11, 13)    | new Date(2015, 11, 20)    | 3
        2       | new Date(2015, 11, 13)    | new Date(2015, 11, 15)    | 5
        3       | new Date(2015, 11, 13)    | new Date(2015, 11, 25)    | 4
        42      | new Date(2015, 11, 13)    | new Date(2015, 11, 19)    | 5
    }

    void "test d'invalidité d'une visitRequest non valide"(code, startPeriodDate, endPeriodDate, nbPeople) {

        given: "une visitRequest initialise avec un code, une date de début, une date de fin et un statu non null, et un nb de personne >= 1"
        VisitRequest visitRequest = new VisitRequest(
                code: code,
                startPeriodDate: startPeriodDate,
                endPeriodDate: endPeriodDate,
                nbPeople: nbPeople,
                status: VisitRequest.Status.PENDING)

        expect: "la visitRequest est non valide"
        !visitRequest.validate()

        where:
        code    | startPeriodDate           | endPeriodDate             | nbPeople
        /*null    | new Date(2015, 11, 13)    | new Date(2015, 11, 20)    | 3
        2       | null                      | new Date(2015, 11, 15)    | 5
        2       | new Date(2015, 11, 13)    | null                      | 5*/
        42      | new Date(2015, 11, 13)    | new Date(2015, 11, 19)    | 0
    }
}
