package com.ufms.mediadorpedagogico.domain.boundary

import com.ufms.mediadorpedagogico.domain.entity.About
import io.reactivex.Single

interface AboutRepository {
    fun getAboutInformation(): Single<About>
}