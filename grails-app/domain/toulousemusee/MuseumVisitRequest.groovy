package toulousemusee

class MuseumVisitRequest {

    Date requestDate

    static belongsTo = [
        museum : Museum,
        visitRequest : VisitRequest
    ]

    static constraints = {
        requestDate nullable: false
        museum nullable: false
        visitRequest nullable: false
    }
}
