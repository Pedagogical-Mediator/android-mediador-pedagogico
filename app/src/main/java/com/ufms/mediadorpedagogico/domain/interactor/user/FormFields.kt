package com.ufms.mediadorpedagogico.domain.interactor.user

class FormFields {

    private val invalidFieldsException: InvalidFieldsException = InvalidFieldsException()

    var classKey: String? = null
        private set
    var name: String? = null
        private set

    private fun isClassKeyValid(): Boolean =
        classKey != null && !classKey!!.trim { it <= ' ' }.isEmpty()

    private fun isNameValid(): Boolean = name != null && !name!!.isEmpty()

    val isValid: Boolean
        get() = invalidFieldsException.getFields().isEmpty()

    val exception: InvalidFieldsException get() = invalidFieldsException

    fun withClassKey(classKey: String): FormFields {
        this.classKey = classKey
        updateField(InvalidFieldsException.CLASS_KEY, isClassKeyValid())
        return this
    }

    fun withName(name: String): FormFields {
        this.name = name
        updateField(InvalidFieldsException.NAME, isNameValid())
        return this
    }

    private fun updateField(field: Int, valid: Boolean) {
        if (valid) {
            invalidFieldsException.getFields().remove(field)
        } else {
            invalidFieldsException.getFields().add(field)
        }
    }
}
