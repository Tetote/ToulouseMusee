package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Museum)
class MuseumSpec extends Specification {

    @Unroll
    void "test la validite d'un musee valide"(String name, String hours, String phoneNumber, String metroAccess, String busAccess) {

        given: "un musee initialise avec un nom des heures d'ouverture et un numero de telephone non vides et les acces metro et bus"
        Museum museum = new Museum(name: name, hours: hours, phoneNumber: phoneNumber,
                metroAccess: metroAccess, busAccess: busAccess,
                manager: Mock(Manager), address: Mock(Address))

        expect: "le musee est valide"
        museum.validate()

        where:
        name    | hours     | phoneNumber   | metroAccess   | busAccess
        "nom"   | "8h"      | "052344547"   | "Ligne B"     | "Ligne 65"
        "nom"   | "9h"      | "052344547"   | null          | "Ligne 65"
        "nom"   | "10h"     | "052344547"   | "Ligne B"     | null
        "nom"   | "11h"     | "052344547"   | null          | null
    }

    @Unroll
    void "test l'invalidite d'un musee non valide"(String name, String hours, String phoneNumber, Address address, Manager manager) {

        given: "un musee initialise avec un nom des heures d'ouverture et un numero de telephone non vides et les acces metro et bus"
        Museum museum = new Museum(name: name, hours: hours, phoneNumber: phoneNumber)

        expect: "le musee est non valide"
        !museum.validate()

        where:
        name    | hours     | phoneNumber   | address        | manager
        ""      | "8h"      | "052344547"   | Mock(Address)  | Mock(Manager)
        "nom"   | ""        | "052344547"   | Mock(Address)  | Mock(Manager)
        "nom"   | "10h"     | ""            | Mock(Address)  | Mock(Manager)
        null    | "8h"      | "052344547"   | Mock(Address)  | Mock(Manager)
        "nom"   | null      | "052344547"   | Mock(Address)  | Mock(Manager)
        "nom"   | "10h"     | null          | Mock(Address)  | Mock(Manager)
    }
}
