package toulousemusee

import spock.lang.Specification
/**
 * Created by MetalDiamond on 25/04/2015.
 */
class VisitRequestServiceIntegrationSpec extends Specification{

    VisitRequestService visitRequestService

    void "test insertion d'une visit request"() {

        when: "on tente de répercuter en base la création ou la modification de la visit request"
        VisitRequest resultVisitRequest = visitRequestService.insertVisitRequest(
                new Date(2015, 11, 13),
                new Date(2015, 11, 20),
                3)

        then:"la visit request résultante n'a pas d'erreur"
        !resultVisitRequest.hasErrors()

        and:"la visit request résultante a un id"
        resultVisitRequest.id

        and:"la visit request est bien presente en base"
        VisitRequest.findById(resultVisitRequest.id) != null
    }

    void "test insertion d'une visit request avec des musees"() {

        given:"visitRequest"
        VisitRequest visitRequest = visitRequestService.insertVisitRequest(
                new Date(2015, 11, 13),
                new Date(2015, 11, 20),
                3)

        and: "des musees"
        List<Museum> museums = new ArrayList<>()
        museums.add(Museum.list().get(0))
        museums.add(Museum.list().get(1))

        when: "on tente de répercuter en base la création ou la modification de la visit request avec des musees"
        VisitRequest resultVisitRequest = visitRequestService.insertVisitRequestForMuseums(visitRequest, museums)

        then: "la visit request resultante pointe sur la visit request initale"
        visitRequest == resultVisitRequest

        and:"la visit request résultante n'a pas d'erreur"
        !resultVisitRequest.hasErrors()

        and:"la visit request résultante a un id"
        resultVisitRequest.id

        and:"la visit request est bien presente en base"
        VisitRequest.findById(resultVisitRequest.id) != null

        and: "les 2 museums sont bien dans la visit request"
        resultVisitRequest.museumVisitRequest.size() == 2
    }
}
