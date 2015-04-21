package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseumVisitRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MuseumVisitRequest.list(params), model: [museumVisitRequestInstanceCount: MuseumVisitRequest.count()]
    }

    def show(MuseumVisitRequest museumVisitRequestInstance) {
        respond museumVisitRequestInstance
    }

    def create() {
        respond new MuseumVisitRequest(params)
    }

    @Transactional
    def save(MuseumVisitRequest museumVisitRequestInstance) {
        if (museumVisitRequestInstance == null) {
            notFound()
            return
        }

        if (museumVisitRequestInstance.hasErrors()) {
            respond museumVisitRequestInstance.errors, view: 'create'
            return
        }

        museumVisitRequestInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'museumVisitRequest.label', default: 'MuseumVisitRequest'), museumVisitRequestInstance.id])
                redirect museumVisitRequestInstance
            }
            '*' { respond museumVisitRequestInstance, [status: CREATED] }
        }
    }

    def edit(MuseumVisitRequest museumVisitRequestInstance) {
        respond museumVisitRequestInstance
    }

    @Transactional
    def update(MuseumVisitRequest museumVisitRequestInstance) {
        if (museumVisitRequestInstance == null) {
            notFound()
            return
        }

        if (museumVisitRequestInstance.hasErrors()) {
            respond museumVisitRequestInstance.errors, view: 'edit'
            return
        }

        museumVisitRequestInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MuseumVisitRequest.label', default: 'MuseumVisitRequest'), museumVisitRequestInstance.id])
                redirect museumVisitRequestInstance
            }
            '*' { respond museumVisitRequestInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MuseumVisitRequest museumVisitRequestInstance) {

        if (museumVisitRequestInstance == null) {
            notFound()
            return
        }

        museumVisitRequestInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MuseumVisitRequest.label', default: 'MuseumVisitRequest'), museumVisitRequestInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'museumVisitRequest.label', default: 'MuseumVisitRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
