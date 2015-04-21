package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VisitRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond VisitRequest.list(params), model: [visitRequestInstanceCount: VisitRequest.count()]
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
