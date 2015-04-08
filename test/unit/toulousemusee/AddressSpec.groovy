package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Address)
class AddressSpec extends Specification {

    void "test la validite d'un musee valide"(String num, String street, String zipCode, String city) {

        given: "une adresse initialise avec un numero, une rue, un code postal et une ville non vides"
        Address address = new Address(num: num, street: street, zipCode: zipCode, city: city)

        expect: "le musee est valide"
        address.validate()

        where:
        num | street                    | zipCode   | city
        "1" | "Toluka Lake Street"      | "13666"   | "Silent Hill"
        "1" | "rue lawl"                | "13042"   | "Poney Land"
        "1" | "rue lawl"                | "13042"   | "Relawl ville"
        "1" | "rue lawl"                | "13042"   | "Une ville"
    }

    void "test l'invalidite d'un musee valide"(String num, String street, String zipCode, String city) {

        given: "une adresse initialise avec un numero, une rue, un code postal et une ville non vides"
        Address address = new Address(num: num, street: street, zipCode: zipCode, city: city)

        expect: "le musee est non valide"
        !address.validate()

        where:
        num     | street                | zipCode   | city
        null    | "Toluka Lake Street"  | "13666"   | "Silent Hill"
        ""      | "Toluka Lake Street"  | "13666"   | "Silent Hill"
        "1"     | ""                    | "13042"   | "Poney Land"
        "1"     | "rue lawl"            | ""        | "Relawl ville"
        "1"     | "rue lawl"            | "13042"   | ""
        "1"     | null                  | "13042"   | "Poney Land"
        "1"     | "rue lawl"            | null      | "Relawl ville"
        "1"     | "rue lawl"            | "13042"   | null
    }
}
