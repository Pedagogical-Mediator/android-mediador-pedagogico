package com.ufms.mediadorpedagogico.domain.interactor.user


class LoginForm {
    var classKey: String? = null
    var name: String? = null

    fun useForm(action: (classGroup: String, name: String) -> Unit): InvalidFieldsException? {
        classKey?.let { classGroup ->
            name?.let { name ->
                action.invoke(classGroup, name)
                return null
            }
        }
        return InvalidFieldsException().apply {
            if (classKey == null) addField(InvalidFieldsException.CLASS_KEY)
            if (name == null) addField(InvalidFieldsException.NAME)
        }
    }
}