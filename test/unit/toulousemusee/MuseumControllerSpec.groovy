package toulousemusee


import grails.test.mixin.*
import spock.lang.*

@TestFor(MuseumController)
@Mock([Museum, Address, Manager, User])
class MuseumControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["name"] = "ENSEMBLE CONVENTUEL DES JACOBINS"
        params["hours"] = "Ouvert tous les jours de 9h à 19h."
        params["phoneNumber"] = "561222192"
        params["metroAccess"] = "Esquirol, Capitole (A)"
        params["busAccess"] = "NCV, 2, 10, 12, 14, 38, 78, 80"
        params["manager"] = Mock(Manager)
        params["address"] = Mock(Address)
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.zipCodeInstanceList
        !model.favoriteMuseumInstanceList
    }

    void "Test the doSearchMuseums action returns the correct model"() {
        when: "The doSearchMuseums action is executed"
        controller.doSearchMuseums()

        then: "The model is correct"
        !model.museumInstanceList
        model.museumInstanceCount == 0
        !model.favorisInstanceList
        !model.zipCodeInstanceList
        !model.favoriteMuseumInstanceList
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.museumInstance != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def museum = new Museum()
        museum.validate()
        controller.save(museum)

        then: "The create view is rendered again with the correct model"
        model.museumInstance != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        museum = new Museum(params)

        controller.save(museum)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/museum/show/1'
        controller.flash.message != null
        Museum.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def museum = new Museum(params)
        controller.show(museum)

        then: "A model is populated containing the domain instance"
        model.museumInstance == museum
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def museum = new Museum(params)
        controller.edit(museum)

        then: "A model is populated containing the domain instance"
        model.museumInstance == museum
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/'
        flash.message != null


        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def museum = new Museum()
        museum.validate()
        controller.update(museum)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.museumInstance == museum

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        museum = new Museum(params).save(flush: true)
        controller.update(museum)

        then: "A redirect is issues to the show action"
        response.redirectedUrl == "/museum/show/$museum.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def museum = new Museum(params).save(flush: true)

        then: "It exists"
        Museum.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(museum)

        then: "The instance is deleted"
        Museum.count() == 0
        response.redirectedUrl == '/index'
        flash.message != null
    }
}
