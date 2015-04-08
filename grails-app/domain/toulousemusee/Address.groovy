package toulousemusee

class Address {

    String num
    String street
    String zipCode
    String city

    static constraints = {

        num blank: false, nullable: false
        street blank: false, nullable: false
        zipCode blank: false, nullable: false
        city blank: false, nullable: false
    }
}