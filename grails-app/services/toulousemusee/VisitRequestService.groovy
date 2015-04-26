package toulousemusee

import grails.transaction.Transactional

@Transactional
class VisitRequestService {

    VisitRequest insertVisitRequest(Date startPeriodDate, Date endPeriodDate, int nbPeople) {
        new VisitRequest(
                code: 0,
                startPeriodDate: startPeriodDate,
                endPeriodDate: endPeriodDate,
                nbPeople: nbPeople,
                status: VisitRequest.Status.PENDING
        ).save()
    }

    VisitRequest insertVisitRequestForMuseums(VisitRequest visitRequest, def museums) {
        visitRequest.save(flush: true)

        for (Museum m : museums) {
            MuseumVisitRequest mvr = new MuseumVisitRequest(
                    requestDate: new Date(),
                    museum: m,
                    visitRequest: visitRequest
            )
            mvr.save()

            visitRequest.addToMuseumVisitRequest(mvr)
        }
        visitRequest.save(flush: true)
    }
}
