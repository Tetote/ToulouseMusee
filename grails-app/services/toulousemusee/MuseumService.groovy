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
}
