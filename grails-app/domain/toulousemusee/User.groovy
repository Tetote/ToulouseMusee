package toulousemusee

class User {

    String toString() {
        favorites.toString()
    }

    static hasMany = [
        favorites : Museum
    ]

    static constraints = {

    }
}
