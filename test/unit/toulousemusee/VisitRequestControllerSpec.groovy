package toulousemusee


import grails.test.mixin.*
import spock.lang.*

@TestFor(VisitRequestController)
@Mock([VisitRequest, User])
class VisitRequestControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["code"] = 1
        params["startPeriodDate"] = new Date(2015, 11, 13)
        params["endPeriodDate"] = new Date(2015, 11, 20)
        params["nbPeople"] = 3
        params["status"] = VisitRequest.Status.CONFIRMED
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.favoriteMuseumInstanceList
        model.codeVisitRequest == -2
    }

    void "Test the addVisitRequest action returns the correct model"() {
        when: "The index action is executed"
        controller.addVisitRequest()

        then: "The model is correct"
        !model.favoriteMuseumInstanceList
        model.codeVisitRequest == -1
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.visitRequestInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def visitRequest = new VisitRequest()
        visitRequest.validate()
        controller.save(visitRequest)

        then: "The create view is rendered again with the correct model"
        model.visitRequestInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        visitRequest = new VisitRequest(params)

        controller.save(visitRequest)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/visitRequest/show/1'
        controller.flash.message != null
        VisitRequest.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def visitRequest = new VisitRequest(params)
        controller.show(visitRequest)

        then: "A model is populated containing the domain instance"
        model.visitRequestInstance == visitRequest
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def visitRequest = new VisitRequest(params)
        controller.edit(visitRequest)

        then: "A model is populated containing the domain instance"
        model.visitRequestInstance == visitRequest
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/visitrequest'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def visitRequest = new VisitRequest()
        visitRequest.validate()
        controller.update(visitRequest)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.visitRequestInstance == visitRequest

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        visitRequest = new VisitRequest(params).save(flush: true)
        controller.update(visitRequest)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/visitRequest/show/$visitRequest.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/visitrequest'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def visitRequest = new VisitRequest(params).save(flush: true)

        then: "It exists"
        VisitRequest.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(visitRequest)

        then: "The instance is deleted"
        VisitRequest.count() == 0
        response.redirectedUrl == '/visitrequest'
        flash.message != null
    }
}
