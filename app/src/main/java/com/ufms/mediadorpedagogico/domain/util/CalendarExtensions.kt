package com.ufms.mediadorpedagogico.domain.util

import java.util.*

fun Calendar.isExpirationDay(): Boolean {
    val day = get(Calendar.DAY_OF_MONTH)
    val month = get(Calendar.MONTH) + 1
    //Dia de expiração automática do aluno
    return day == 20 && month == 12
}
