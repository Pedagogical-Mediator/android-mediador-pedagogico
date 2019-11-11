package com.ufms.mediadorpedagogico.data.remote.repository

import com.ufms.mediadorpedagogico.data.remote.client.ApiClient
import com.ufms.mediadorpedagogico.data.remote.entity.ApiTeacher
import com.ufms.mediadorpedagogico.domain.boundary.TeacherRepository
import com.ufms.mediadorpedagogico.domain.entity.Teacher
import io.reactivex.Single

class DefaultTeacherRepository(
    private val apiClient: ApiClient
) : TeacherRepository {

    override fun getTeachers(): Single<List<Teacher>> {
        return apiClient.getTeachers().map(ApiTeacher.ApiTeacherToTeacher::transform)
    }
}