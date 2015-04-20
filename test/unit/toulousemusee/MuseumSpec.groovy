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
        name                               | hours                                                                 | phoneNumber   | metroAccess              | busAccess
        "ARCHIVES MUNICIPALES TOULOUSE"    | "Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30" +
                " pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet."     | "561616333"   | "Roseraie (A)"           | "36, 38"
        "MUSEE DES COMPAGNONS"             | "Le Mercredi et le 1er dimanche de chaque mois de 14h à 17h"          | "562474177"   | "Esquirol, Capitole (A)" | null
        "MUSEE DU VIEUX TOULOUSE"          | "Ouvert tous les jours du 2 mai au 31 octobre de 14h00 à 18h00." +
                "Fermé le dimanche et jours fériés."                                                               | "562271150"   | null                     | "2, 10, 12, 14, 38, 78, 80"
        "ENSEMBLE CONVENTUEL DES JACOBINS" | "Ouvert tous les jours de 9h à 19h."                                  | "561222192"   | null                     | null
    }

    @Unroll
    void "test l'invalidite d'un musee non valide"(String name, String hours, String phoneNumber, Address address, Manager manager) {

        given: "un musee initialise avec un nom des heures d'ouverture et un numero de telephone non vides et les acces metro et bus"
        Museum museum = new Museum(name: name, hours: hours, phoneNumber: phoneNumber)

        expect: "le musee est non valide"
        !museum.validate()

        where:
        name                                 | hours                                | phoneNumber   | address        | manager
        ""                                   | "Ouvert tous les jours de 9h à 19h." | "561222192"   | Mock(Address)  | Mock(Manager)
        "ENSEMBLE CONVENTUEL DES JACOBINS"   | ""                                   | "561222192"   | Mock(Address)  | Mock(Manager)
        "ENSEMBLE CONVENTUEL DES JACOBINS"   | "Ouvert tous les jours de 9h à 19h." | ""            | Mock(Address)  | Mock(Manager)
        null                                 | "Ouvert tous les jours de 9h à 19h." | "561222192"   | Mock(Address)  | Mock(Manager)
        "ENSEMBLE CONVENTUEL DES JACOBINS"   | null                                 | "561222192"   | Mock(Address)  | Mock(Manager)
        "ENSEMBLE CONVENTUEL DES JACOBINS"   | "Ouvert tous les jours de 9h à 19h." | null          | Mock(Address)  | Mock(Manager)
    }
}
