package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.Teacher
import io.reactivex.Single

interface TeacherRepository {
    fun getTeachers(): Single<List<Teacher>>
}