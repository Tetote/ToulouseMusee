package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {

    MuseumService museumService

    static String filename = "data/museum.csv"
    static String separator = ";"

    enum FIELDS {
        NAME(0),
        MANAGER(1),
        HOURS(2),
        WEBSITE(3),
        PHONENUMBER(4),
        METROACCESS(5),
        BUSACCESS(6),
        NUM(7),
        STREET(8),
        ZIPCODE(9),
        CITY(10)

        int id
        FIELDS(id) {
            this.id = id
        }
    }

    def createJeuTestForMuseum() {
        if (Museum.count() == 0) {
            File file = new File(filename);

            file.splitEachLine(separator) { fields ->
                // Skip header
                if (fields[FIELDS.NAME.id].equals("EQ_NOM_EQUIPEMENT"))
                    return

                Museum museum = new Museum(
                        name: fields[FIELDS.NAME.id],
                        hours: fields[FIELDS.HOURS.id],
                        phoneNumber: fields[FIELDS.PHONENUMBER.id],
                        metroAccess: fields[FIELDS.METROACCESS.id],
                        busAccess: fields[FIELDS.BUSACCESS.id]
                )

                // Check if the manager exist in database
                Manager manager = Manager.findByName(fields[FIELDS.MANAGER.id]) ?:
                        new Manager(name: fields[FIELDS.MANAGER.id])

                Address address = new Address(
                        num: fields[FIELDS.NUM.id],
                        street: fields[FIELDS.STREET.id],
                        zipCode: fields[FIELDS.ZIPCODE.id],
                        city: fields[FIELDS.CITY.id]
                )

                museumService.insertOrUpdateMuseumForAddressAndManager(museum, address, manager)
            }
        }
    }

    def createUser() {
        if (User.count == 0) {
            new User().save()
        }
    }
}
