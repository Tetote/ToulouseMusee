package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseumService {

    Museum insertOrUpdateMuseumForAddressAndManager(Museum museum, Address address, Manager manager) {
        museum.setAddress(address)
        museum.setManager(manager)

        address.save()
        manager.save()
        museum.save()
    }

    Museum insertOrUpdateMuseumForAddress(Museum museum, Address address) {
        museum.setAddress(address)

        address.save()
        museum.save()
    }

    Museum insertOrUpdateMuseumForManager(Museum museum, Manager manager) {
        museum.setManager(manager)

        manager.save()
        museum.save()
    }

    void deleteMuseum(Museum museum) {
        museum.address.delete()
        museum.delete()
    }

    void addFavorite(Museum museum, User user) {
        user.addToFavorites(museum)
        user.save(flush: true)
    }

    void removeFavorite(Museum museum, User user) {
        user.removeFromFavorites(museum)
        user.save(flush: true)
    }

    List<Museum> searchMuseums(String name, String street, String zipCode, int offset, int max) {
        def criteria = Museum.createCriteria()

        List<Museum> museums = criteria.list(offset: offset, max: max) {
            if (name) {
                like 'name', "%${name.toUpperCase()}%"
            }

            if (street) {
                address {
                    like 'street', "%${street.toUpperCase()}%"
                }
            }

            if (zipCode) {
                address {
                    eq 'zipCode', zipCode
                }
            }

            order('name')
        }
        museums
    }
}
