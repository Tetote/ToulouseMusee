package toulousemusee

import spock.lang.Specification
/**
 * Created by MetalDiamond on 25/04/2015.
 */
class VisitRequestServiceIntegrationSpec extends Specification{

    VisitRequestService visitRequestService

    void "test insertion d'une visit request"() {

        given:"visitRequest"
        VisitRequest visitRequest = new VisitRequest(
                code: 1,
                startPeriodDate: new Date(2015, 11, 13),
                endPeriodDate: new Date(2015, 11, 20) ,
                nbPeople: 3)

        when: "on tente de répercuter en base la création ou la modification de la visit request"
        VisitRequest resultVisitRequest = visitRequestService.insertVisitRequest(visitRequest)

        then: "la visit request resultanet pointe sur la visit request initale"
        visitRequest == resultVisitRequest

        and:"la visit request résultante n'a pas d'erreur"
        !resultVisitRequest.hasErrors()

        and:"la visit request résultante a un id"
        resultVisitRequest.id

        and:"la visit request est bien presente en base"
        VisitRequest.findById(resultVisitRequest.id) != null
    }
}
