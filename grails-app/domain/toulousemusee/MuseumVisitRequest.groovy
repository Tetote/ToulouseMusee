package toulousemusee

class MuseumVisitRequest {

    Date requestDate

    static hasOne = [
        museum : Museum,
        visitRequest : VisitRequest
    ]

    static constraints = {

    }
}
