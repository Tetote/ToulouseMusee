package toulousemusee

class VisitRequest {

    enum Status{
        PENDING, CONFIRMED, REFUSED
    }

    Integer code
    Date startPeriodDate
    Date endPeriodDate
    int nbPeople
    Status status

    static hasMany = [
            museumVisitRequest : MuseumVisitRequest
    ]

    static constraints = {

        code nullable: false
        startPeriodDate nullable: false
        endPeriodDate nullable: false
        nbPeople min: 1
    }
}
