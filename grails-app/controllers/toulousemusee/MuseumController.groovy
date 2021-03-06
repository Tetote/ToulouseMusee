package toulousemusee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseumController {

    MuseumService museumService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        User currentUser = User.list().size() > 0 ? User.list().get(0) : null

        render(view: '/index',
                params: params,
                model: [zipCodeInstanceList: Address.listZipCode(),
                        favoriteMuseumInstanceList: currentUser?.favorites])
    }

    def doSearchMuseums() {
        int offset = params.offset ? Integer.parseInt(params.offset) : 0

        List<Museum> museumsList = museumService.searchMuseums(params.name,
                params.street,
                params.zipCode,
                offset,
                5)
        int museumsListNumber = museumService.searchMuseums(params.name,
                params.street,
                params.zipCode,
                0,
                100).size()

        List<Boolean> favoritesList = new ArrayList<>()

        User currentUser = User.list().size() > 0 ? User.list().get(0) : null
        for (Museum museum : museumsList) {
            favoritesList.add(currentUser?.favorites.contains(museum))
        }

        render(view: '/index',
                params: params,
                model: [museumInstanceList: museumsList,
                        museumInstanceCount: museumsListNumber,
                        favoriteInstanceList: favoritesList,
                        zipCodeInstanceList: Address.listZipCode(),
                        favoriteMuseumInstanceList: currentUser?.favorites])
    }

    def addToFavorite(Museum museumInstance) {
        museumService.addFavorite(museumInstance, User.list().get(0))

        redirect(action: "index")
    }

    def removeFromFavorite(Museum museumInstance) {
        museumService.removeFavorite(museumInstance, User.list().get(0))

        redirect(action: "index")
    }

    def show(Museum museumInstance) {
        respond museumInstance
    }

    def create() {
        respond new Museum(params)
    }

    @Transactional
    def save(Museum museumInstance) {
        if (museumInstance == null) {
            notFound()
            return
        }

        if (museumInstance.hasErrors()) {
            respond museumInstance.errors, view:'create'
            return
        }

        museumService.insertOrUpdateMuseumForAddressAndManager(
                museumInstance,
                museumInstance.address,
                museumInstance.manager)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'museum.label', default: 'Museum'), museumInstance.id])
                redirect museumInstance
            }
            '*' { respond museumInstance, [status: CREATED] }
        }
    }

    def edit(Museum museumInstance) {
        respond museumInstance
    }

    @Transactional
    def update(Museum museumInstance) {
        if (museumInstance == null) {
            notFound()
            return
        }

        if (museumInstance.hasErrors()) {
            respond museumInstance.errors, view:'edit'
            return
        }

        museumService.insertOrUpdateMuseumForAddressAndManager(
                museumInstance,
                museumInstance.address,
                museumInstance.manager)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Museum.label', default: 'Museum'), museumInstance.id])
                redirect museumInstance
            }
            '*'{ respond museumInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Museum museumInstance) {

        if (museumInstance == null) {
            notFound()
            return
        }

        museumService.deleteMuseum(museumInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Museum.label', default: 'Museum'), museumInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'museum.label', default: 'Museum'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
