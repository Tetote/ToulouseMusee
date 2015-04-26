package toulousemusee

import grails.transaction.Transactional

@Transactional
class VisitRequestService {

    VisitRequest insertVisitRequest(VisitRequest visitRequest) {
        visitRequest.save()
    }
}
