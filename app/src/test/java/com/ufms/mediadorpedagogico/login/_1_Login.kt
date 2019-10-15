//package com.ufms.mediadorpedagogico.login
//
//import com.ufms.mediadorpedagogico.domain.interactor.user.SignIn
//import com.ufms.mediadorpedagogico.presentation.util.dependecyinjector.*
//import org.junit.Assert
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.inject
//
//class _1_Login : KoinTest {
//
//    val signIn by inject<SignIn>()
//
//    @BeforeEach
//    fun before() {
//        startKoin {
//            modules(listOf(interactorModule, repositoryModule, applicationModule, viewModelModule, androidContextModule))
//        }
//    }
//
//    @Test
//    fun getNews() {
//        val user = signIn.default("Abacaxi", "Nome teste").blockingGet()
//        Assert.assertNotNull(user)
//    }
//
//    @AfterEach
//    fun after() {
//        stopKoin()
//    }
//}