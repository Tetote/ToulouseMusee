package toulousemusee

import spock.lang.Specification


/**
 * Created by MetalDiamond on 22/04/2015.
 */
class MuseumServiceIntegrationSpec extends Specification{

    JeuTestService jeuTestService
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

    void "test du moteur de recherche sur les museums"() {
        given: "les museums fournis par le jeu de test "
        jeuTestService

        when: "on cherche les museums dont le titre du museum contient 'compagnons'"
        List<Museum> res = museumService.searchMuseums("compagnons", null, null)

        then: "on récupère uniquement un museum"
        res.size() == 1
        res*.name.contains("MUSEE DES COMPAGNONS")


        when: "on cherche les museums dont la rue du museum contient 'archives'"
        res = museumService.searchMuseums(null, "archives", null)

        then: "on récupère uniquement un museum"
        res.size() == 1
        res*.name.contains("ARCHIVES MUNICIPALES TOULOUSE")


        when: "on cherche les museums dont le code postal du museum est '31300"
        res = museumService.searchMuseums(null, null, "31300")

        then: "on récupère uniquement un museum"
        res.size() == 2
        res*.name.contains("MUSEE DE L'HISTOIRE DE LA MEDECINE DE TOULOUSE")
        res*.name.contains("MUSEE DES INSTRUMENTS DE MEDECINE DES HOPITAUX DE TOULOUSE")


        when:"on cherche les museums dont le titre du museum contient 'not_today'"
        res = museumService.searchMuseums("not_today", null, null)

        then: "on ne récupère aucun museum"
        res.size() == 0


        when:"on positionne tous les critères à null"
        res = museumService.searchMuseums(null, null, null)

        then: "on récupère tous les museums"
        res.size() == 12
    }
}
