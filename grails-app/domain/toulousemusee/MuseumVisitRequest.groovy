package toulousemusee

class MuseumVisitRequest {

    Date requestDate

    String toString() {
        "$requestDate"
    }

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
