package toulousemusee

class VisitRequest {

    enum Status{
        PENDING, CONFIRMED, REFUSED
    }

    int code
    Date startPeriodDate
    Date endPeriodDate
    int nbPeople
    Status status

    static hasMany = [
            museumVisitRequest : MuseumVisitRequest
    ]

    static constraints = {
    }
}
