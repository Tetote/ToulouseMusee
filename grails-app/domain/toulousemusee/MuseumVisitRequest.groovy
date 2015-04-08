package toulousemusee

class MuseumVisitRequest {

    Date requestDate

    static hasMany = [
        museums:Museum,
        visitRequests:VisitRequest
    ]

    static constraints = {

    }
}
