package toulousemusee

class MuseumVisitRequest {

    Date requestDate

    static hasOne = [
        Museum,
        VisitRequest
    ]

    static constraints = {

    }
}
