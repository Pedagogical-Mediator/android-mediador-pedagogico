package com.ufms.mediadorpedagogico.domain.interactor.teacher

import com.ufms.mediadorpedagogico.domain.boundary.TeacherRepository
import com.ufms.mediadorpedagogico.domain.entity.Teacher
import io.reactivex.Single

class GetTeacher(
    private val teacherRepository: TeacherRepository
) {

    fun executeList(): Single<List<Teacher>> {
        return teacherRepository.getTeachers()
    }
}