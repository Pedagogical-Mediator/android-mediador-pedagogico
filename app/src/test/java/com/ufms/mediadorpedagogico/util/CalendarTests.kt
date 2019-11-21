package com.ufms.mediadorpedagogico.util

import com.ufms.mediadorpedagogico.domain.util.getToday
import com.ufms.mediadorpedagogico.domain.util.isExpirationDay
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class CalendarTests {

    @Test
    fun executeToday() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONDAY, 11)
        calendar.set(Calendar.DAY_OF_MONTH, 20)
        assert(calendar.isExpirationDay())
    }

    @Test
    fun executeFailure() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONDAY, 11)
        calendar.set(Calendar.DAY_OF_MONTH, 25)
        assert(!calendar.isExpirationDay())
    }

    @Test
    fun executeGetToday() {
        val calendar = Calendar.getInstance()
        assert(calendar.getToday() == SimpleDateFormat("dd/MM/yy").format(Calendar.getInstance().time))
    }
}
