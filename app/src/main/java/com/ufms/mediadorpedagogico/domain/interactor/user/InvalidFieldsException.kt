package com.ufms.mediadorpedagogico.domain.interactor.user

class InvalidFieldsException : Exception() {

    private val fields = HashSet<Int>()

    fun getFields(): MutableSet<Int> {
        return fields
    }

    fun addField(field: Int) {
        fields.add(field)
    }

    companion object {
        const val CLASS_KEY = 1
        const val NAME = 2
    }
}
