package toulousemusee

import spock.lang.Specification


/**
 * Created by MetalDiamond on 22/04/2015.
 */
class MuseumServiceIntegrationSpec extends Specification{

    MuseumService museumService;

    void "test insertion ou mise à jour d'un musee avec une adresse et un manager"() {

        given:"address"
        Address address = new Address(num: 2, street: "Rue des Archives", zipCode: "31500", city: "Toulouse")

        and:"manager"
        Manager manager = new Manager(name: "Mairie de Toulouse - DGA Culture")

        and:"museum"
        Museum museum = new Museum(name: "ARCHIVES MUNICIPALES TOULOUSE", hours: "Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.", phoneNumber: "561616333", metroAccess: "Roseraie (A)", busAccess: "36, 38")

        when: "on tente de répercuter en base la création ou la modification du musee"
        Museum resultMuseum = museumService.insertOrUpdateMuseumForAddressAndManager(museum, address, manager)

        then: "le musee resultant pointe sur le musee initale"
        museum == resultMuseum

        and:"le musee résultante n'a pas d'erreur"
        !resultMuseum.hasErrors()

        and:"le musee résultante a un id"
        resultMuseum.id

        and:"le musee est bien presente en base"
        Museum.findById(resultMuseum.id) != null

        and: "le musee a pour adresse l'adresse passé en paramètre"
        resultMuseum.address == address

        and: "le musee a pour manager le manager passé en paramètre"
        resultMuseum.manager == manager
    }

    void "test insertion ou mise à jour d'un musee avec une adresse"() {

        given:"address"
        Address address = new Address(num: 2, street: "Rue des Archives", zipCode: "31500", city: "Toulouse")

        and:"manager"
        Manager manager = new Manager(name: "Mairie de Toulouse - DGA Culture").save()

        and:"museum"
        Museum museum = new Museum(name: "ARCHIVES MUNICIPALES TOULOUSE", hours: "Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.", phoneNumber: "561616333", metroAccess: "Roseraie (A)", busAccess: "36, 38")
        museum.setManager(manager)

        when: "on tente de répercuter en base la création ou la modification du musee"
        Museum resultMuseum = museumService.insertOrUpdateMuseumForAddress(museum, address)

        then: "le musee resultant pointe sur le musee initale"
        museum == resultMuseum

        and:"le musee résultante n'a pas d'erreur"
        !resultMuseum.hasErrors()

        and:"le musee résultante a un id"
        resultMuseum.id

        and:"le musee est bien presente en base"
        Museum.findById(resultMuseum.id) != null

        and: "le musee a pour adresse l'adresse passé en paramètre"
        resultMuseum.address == address
    }

    void "test insertion ou mise à jour d'un musee avec un manager"() {

        given:"manager"
        Manager manager = new Manager(name: "Mairie de Toulouse - DGA Culture")

        and:"address"
        Address address = new Address(num: 2, street: "Rue des Archives", zipCode: "31500", city: "Toulouse").save()

        and:"museum"
        Museum museum = new Museum(name: "ARCHIVES MUNICIPALES TOULOUSE", hours: "Ouvert du lundi au vendredi de 9h à 17h. Fermeture de 12h à 13h30 pendant toutes les vacances scolaires. Fermeture annuelle la dernière quinzaine de juillet.", phoneNumber: "561616333", metroAccess: "Roseraie (A)", busAccess: "36, 38")
        museum.setAddress(address)

        when: "on tente de répercuter en base la création ou la modification du musee"
        Museum resultMuseum = museumService.insertOrUpdateMuseumForManager(museum, manager)

        then: "le musee resultant pointe sur le musee initale"
        museum == resultMuseum

        and:"le musee résultante n'a pas d'erreur"
        !resultMuseum.hasErrors()

        and:"le musee résultante a un id"
        resultMuseum.id

        and:"le musee est bien presente en base"
        Museum.findById(resultMuseum.id) != null

        and: "le musee a pour manager le manager passé en paramètre"
        resultMuseum.manager == manager
    }
}
