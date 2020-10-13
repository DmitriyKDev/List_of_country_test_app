package dev.kalendula.listofcountry.utils

data class Resource<T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data : T?): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
    }
}