package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(VisitRequest)
class VisitRequestSpec extends Specification {

    @Unroll
    void "test de validite d'une visitRequest valide"(Integer code, Date startPeriodDate, Date endPeriodDate, int nbPeople) {
        given: "une visitRequest initialise avec un code, une date de debut, une date de fin non null, et un nb de personne >= 1"
        VisitRequest visitRequest = new VisitRequest(
                code: code,
                startPeriodDate: startPeriodDate,
                endPeriodDate: endPeriodDate,
                nbPeople: nbPeople,
                status: VisitRequest.Status.CONFIRMED)

        expect: "la visitRequest est valide"
        visitRequest.validate()

        where:
        code    | startPeriodDate           | endPeriodDate             | nbPeople
        1       | new Date(2015, 11, 13)    | new Date(2015, 11, 20)    | 3
        2       | new Date(2015, 11, 13)    | new Date(2015, 11, 15)    | 5
        3       | new Date(2015, 11, 13)    | new Date(2015, 11, 25)    | 4
        42      | new Date(2015, 11, 13)    | new Date(2015, 11, 19)    | 5
    }

    @Unroll
    void "test d'invalidite d'une visitRequest non valide"(Integer code, Date startPeriodDate, Date endPeriodDate, int nbPeople) {
        given: "une visitRequest initialise avec un code, une date de debut, une date de fin et un status non null, et un nb de personne >= 1"
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
        null    | new Date(2015, 11, 13)    | new Date(2015, 11, 20)    | 3
        2       | null                      | new Date(2015, 11, 15)    | 5
        2       | new Date(2015, 11, 13)    | null                      | 5
        42      | new Date(2015, 11, 13)    | new Date(2015, 11, 19)    | 0
        42      | new Date(2015, 11, 13)    | new Date(2015, 11, 19)    | 7
    }

    @Unroll
    void "test toString"() {
        given: "une visitRequest"
        Integer code = 2
        Date startPeriodDate = new Date(2015, 11, 13)
        Date endPeriodDate = new Date(2015, 11, 20)
        int nbPeople = 5
        VisitRequest.Status status = VisitRequest.Status.REFUSED
        VisitRequest visitRequest = new VisitRequest(
                code: code,
                startPeriodDate: startPeriodDate,
                endPeriodDate: endPeriodDate,
                nbPeople: nbPeople,
                status: status)

        when: "on veut l'afficher"
        String toString = visitRequest.toString()

        then: "le toString est bien affiche"
        toString == code+" ["+startPeriodDate+"-"+endPeriodDate+"] "+nbPeople+" -> "+status
    }
}
