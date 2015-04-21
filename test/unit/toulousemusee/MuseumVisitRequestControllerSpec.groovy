package toulousemusee


import grails.test.mixin.*
import spock.lang.*

@TestFor(MuseumVisitRequestController)
@Mock(MuseumVisitRequest)
class MuseumVisitRequestControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["requestDate"] = new Date(2015,02,02)
        params["museum"] = Mock(Museum)
        params["visitRequest"] = Mock(VisitRequest)
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.museumVisitRequestInstanceList
        model.museumVisitRequestInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.museumVisitRequestInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def museumVisitRequest = new MuseumVisitRequest()
        museumVisitRequest.validate()
        controller.save(museumVisitRequest)

        then: "The create view is rendered again with the correct model"
        model.museumVisitRequestInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        museumVisitRequest = new MuseumVisitRequest(params)

        controller.save(museumVisitRequest)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/museumVisitRequest/show/1'
        controller.flash.message != null
        MuseumVisitRequest.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def museumVisitRequest = new MuseumVisitRequest(params)
        controller.show(museumVisitRequest)

        then: "A model is populated containing the domain instance"
        model.museumVisitRequestInstance == museumVisitRequest
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def museumVisitRequest = new MuseumVisitRequest(params)
        controller.edit(museumVisitRequest)

        then: "A model is populated containing the domain instance"
        model.museumVisitRequestInstance == museumVisitRequest
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/museumVisitRequest/index'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def museumVisitRequest = new MuseumVisitRequest()
        museumVisitRequest.validate()
        controller.update(museumVisitRequest)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.museumVisitRequestInstance == museumVisitRequest

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        museumVisitRequest = new MuseumVisitRequest(params).save(flush: true)
        controller.update(museumVisitRequest)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/museumVisitRequest/show/$museumVisitRequest.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/museumVisitRequest/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def museumVisitRequest = new MuseumVisitRequest(params).save(flush: true)

        then: "It exists"
        MuseumVisitRequest.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(museumVisitRequest)

        then: "The instance is deleted"
        MuseumVisitRequest.count() == 0
        response.redirectedUrl == '/museumVisitRequest/index'
        flash.message != null
    }
}
