package toulousemusee

class MuseumVisitRequest {

    Date requestDate

    static belongsTo = [
        museum : Museum,
        visitRequest : VisitRequest
    ]

    static constraints = {

    }
}
