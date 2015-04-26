package toulousemusee

class User {

    static hasMany = [
        favorites : Museum
    ]

    static constraints = {

    }
}
