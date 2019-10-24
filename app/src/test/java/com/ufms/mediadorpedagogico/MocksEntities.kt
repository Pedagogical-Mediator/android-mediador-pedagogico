package com.ufms.mediadorpedagogico

import com.ufms.mediadorpedagogico.domain.entity.About
import com.ufms.mediadorpedagogico.domain.entity.User

object MocksEntities {

    val mockedUser = User(
        id = "1",
        name = "Arthur Thomas Mário Martins",
        token = "Abacaxi",
        classKey = "Abacaxi"
    )

    val mockedAbout = About(
        id = 1,
        description = "SobreSobreSobreSobreSobreSobreSobreSobre",
        image = "BASE64",
        link = "google.com"
    )
}