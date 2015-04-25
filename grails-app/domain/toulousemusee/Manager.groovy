package toulousemusee

class Manager {

    String name

    String toString() {
        "$name"
    }

    static constraints = {
        name blank: false, nullable: false
    }
}
