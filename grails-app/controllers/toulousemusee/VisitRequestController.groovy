package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VisitRequestController {

    VisitRequestService visitRequestService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        int codeVisitRequest = -2;
        try {
            codeVisitRequest = Integer.parseInt(params.codeVisitRequest)
        } catch(Exception e) {
        }

        User currentUser = User.list().size() > 0 ? User.list().get(0) : null

        render(view: '/visitrequest', model: [favoriteMuseumInstanceList: currentUser?.favorites,
                                              codeVisitRequest: codeVisitRequest])
    }

    def addVisitRequest() {
        User currentUser = User.list().size() > 0 ? User.list().get(0) : null
        int nbPeople = -1
        try {
            nbPeople = Integer.parseInt(params.nbPeople)
        } catch (Exception e) {
        }

        VisitRequest visitRequest = visitRequestService.insertVisitRequest(
                params.startPeriodDate,
                params.endPeriodDate,
                nbPeople)

        if (visitRequest && !visitRequest.hasErrors())
            visitRequestService.insertVisitRequestForMuseums(visitRequest, currentUser?.favorites)

        int codeVisitRequest = (!visitRequest || visitRequest.hasErrors()) ? -1 : visitRequest.code
        redirect(action: "index", params: [codeVisitRequest: codeVisitRequest])
    }

    def show(VisitRequest visitRequestInstance) {
        respond visitRequestInstance
    }

    def create() {
        respond new VisitRequest(params)
    }

    @Transactional
    def save(VisitRequest visitRequestInstance) {
        if (visitRequestInstance == null) {
            notFound()
            return
        }

        if (visitRequestInstance.hasErrors()) {
            respond visitRequestInstance.errors, view: 'create'
            return
        }

        visitRequestInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'visitRequest.label', default: 'VisitRequest'), visitRequestInstance.id])
                redirect visitRequestInstance
            }
            '*' { respond visitRequestInstance, [status: CREATED] }
        }
    }

    def edit(VisitRequest visitRequestInstance) {
        respond visitRequestInstance
    }

    @Transactional
    def update(VisitRequest visitRequestInstance) {
        if (visitRequestInstance == null) {
            notFound()
            return
        }

        if (visitRequestInstance.hasErrors()) {
            respond visitRequestInstance.errors, view: 'edit'
            return
        }

        visitRequestInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'VisitRequest.label', default: 'VisitRequest'), visitRequestInstance.id])
                redirect visitRequestInstance
            }
            '*' { respond visitRequestInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(VisitRequest visitRequestInstance) {

        if (visitRequestInstance == null) {
            notFound()
            return
        }

        visitRequestInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'VisitRequest.label', default: 'VisitRequest'), visitRequestInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'visitRequest.label', default: 'VisitRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
