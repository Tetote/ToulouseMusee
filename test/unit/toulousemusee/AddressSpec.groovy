package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Address)
class AddressSpec extends Specification {

    @Unroll
    void "test la validite d'un musee valide"(String num, String street, String zipCode, String city) {

        given: "une adresse initialise avec un numero, une rue, un code postal et une ville non vides"
        Address address = new Address(num: num, street: street, zipCode: zipCode, city: city)

        expect: "le musee est valide"
        address.validate()

        where:
        num  | street                 | zipCode   | city
        "2"  | "Rue des Archives"     | "31500"   | "Toulouse"
        "5"  | "Rue Saint Pantaleon"  | "31000"   | "Toulouse"
        "69" | "Rue Pargaminieres"    | "31000"   | "Toulouse"
        "31" | "Rue de la Fonderie"   | "31000"   | "Toulouse"
    }

    @Unroll
    void "test l'invalidite d'un musee valide"(String num, String street, String zipCode, String city) {

        given: "une adresse initialise avec un numero, une rue, un code postal et une ville non vides"
        Address address = new Address(num: num, street: street, zipCode: zipCode, city: city)

        expect: "le musee est non valide"
        !address.validate()

        where:
        num  | street               | zipCode   | city
        null | "Rue des Archives"   | "31500"   | "Toulouse"
        ""   | "Rue des Archives"   | "31500"   | "Toulouse"
        "2"  | ""                   | "31500"   | "Toulouse"
        "2"  | "Rue des Archives"   | ""        | "Toulouse"
        "2"  | "Rue des Archives"   | "31500"   | ""
        "2"  | null                 | "31500"   | "Toulouse"
        "2"  | "Rue des Archives"   | null      | "Toulouse"
        "2"  | "Rue des Archives"   | "31500"   | null
    }

    @Unroll
    void "test liste des zipCode"() {
        given: "la classe address"

        when: "on veut la liste des zipCode"
        List<String> listZipCode = Address.listZipCode()

        then: "on obtient la liste des zipCode"
        listZipCode.size() == 0


        when: "Deux address avec le meme zipCode sont ajoutees"
        new Address(num: "2", street: "Rue des Archives", zipCode: "31500", city: "Toulouse").save()
        new Address(num: "2", street: "Rue des Archives", zipCode: "31500", city: "Toulouse").save()

        and: "on veut la liste des zipCode"
        listZipCode = Address.listZipCode()

        then: "on obtient la liste des zipCode"
        listZipCode.size() == 1
    }

    @Unroll
    void "test toString"() {
        given: "une address"
        String num = "2"
        String street = "Rue des Archives"
        String zipCode = "31500"
        String city = "Toulouse"
        Address address = new Address(num: num, street: street, zipCode: zipCode, city: city)

        when: "on veut l'afficher"
        String toString = address.toString()

        then: "le toString est bien affiche"
        toString == num+" "+street+" "+zipCode+" "+city
    }
}
