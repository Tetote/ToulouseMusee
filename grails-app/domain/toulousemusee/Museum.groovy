package toulousemusee

class Museum {

    String name
    String hours
    String phoneNumber
    String metroAccess
    String busAccess

    Manager manager
    Address address

    static hasMany = [
            museumVisitRequest : MuseumVisitRequest
    ]

    static constraints = {

        name blank: false, nullable: false
        hours blank: false, nullable: false
        phoneNumber blank: false, nullable: false
        metroAccess nullable: true
        busAccess nullable: true
    }
}
