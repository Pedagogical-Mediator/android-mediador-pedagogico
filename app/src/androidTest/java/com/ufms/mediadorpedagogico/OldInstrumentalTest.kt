//package com.ufms.mediadorpedagogico
//
//import androidx.recyclerview.widget.RecyclerView
//import androidx.test.filters.LargeTest
//import androidx.test.runner.AndroidJUnit4
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class AllNoticesTest {
//    @Rule
//    @JvmField
//    val rule = IntentsTestRule(BottomNavigationView::class.java)
//
//    @Before
//    fun init(){
//        val activity = rule.activity
//        val allFragment = AllFragment()
//        val manager = activity.supportFragmentManager
//        val transaction = manager.beginTransaction()
//
//        transaction.replace(R.id.fragment_holder, allFragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
//
//    // Teste seleciona a primeira noticia da lista de notícia
//    @Test
//    fun testRecycleViewFirstNotice() {
//        Thread.sleep(7000)
//
//        val listnotice = (rule.activity.findViewById<RecyclerView>(R.id.listNoticesRecyclerView).adapter as NoticeListAdapter).noticeList
//        val firsItem = listnotice[0]
//        onView(withId(R.id.listNoticesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NoticeListAdapter.NoticeListViewHolder>(0, click()))
//
//        Thread.sleep(3000)
//        onView(withId(R.id.titlePost)).check(matches(withText(firsItem.title)))
//        onView(withId(R.id.authorPost)).check((matches(withText(firsItem.author))))
//
//    }
//
//    //Teste em que seleciona a ultima noticia da primeira pagina
//    @Test
//    fun testFirstPageLastNotice() {
//        Thread.sleep(7000)
//
//        val listnotice = (rule.activity.findViewById<RecyclerView>(R.id.listNoticesRecyclerView).adapter as NoticeListAdapter).noticeList
//        val firsItem = listnotice[9]
//        onView(withId(R.id.listNoticesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NoticeListAdapter.NoticeListViewHolder>(9, click()))
//
//        Thread.sleep(3000)
//        onView(withId(R.id.titlePost)).check(matches(withText(firsItem.title)))
//        onView(withId(R.id.authorPost)).check((matches(withText(firsItem.author))))
//    }
//
//    //Testa o carregamento de mais noticias
//    @Test
//    fun testLoadingMoreNotices() {
//        Thread.sleep(7000)
//        var noticeList = (rule.activity.findViewById<RecyclerView>(R.id.listNoticesRecyclerView).adapter as NoticeListAdapter).noticeList
//        val firstItem = noticeList[9]
//
//        onView(withId(R.id.listNoticesRecyclerView)).perform(RecyclerViewActions.scrollToPosition<NoticeListAdapter.NoticeListViewHolder>(9))
//
//        Thread.sleep(5000)
//        noticeList = (rule.activity.findViewById<RecyclerView>(R.id.listNoticesRecyclerView).adapter as NoticeListAdapter).noticeList
//
//        Assert.assertNotEquals(10, noticeList.size)
//    }
//
//    @Test
//    fun testCheckIfNoticeLoadedPassedMatches() {
//        Thread.sleep(7000)
//        onView(withId(R.id.listNoticesRecyclerView)).perform(
//            RecyclerViewActions.scrollToPosition<NoticeListAdapter.NoticeListViewHolder>(9)
//        )
//
//        Thread.sleep(5000)
//        val noticeList = (rule.activity.findViewById<RecyclerView>(R.id.listNoticesRecyclerView).adapter as NoticeListAdapter).noticeList
//        val firstItem = noticeList[10]
//
//        onView(withId(R.id.listNoticesRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NoticeListAdapter.NoticeListViewHolder>(10, click()))
//
//        Thread.sleep(2000)
//
//        try {
//            onView(withId(R.id.authorPost)).check(matches(withText(CoreMatchers.containsString(firstItem.author))))
//            onView(withId(R.id.titlePost)).check(matches(withText(CoreMatchers.containsString(firstItem.title))))
//        } catch (e: NoMatchingViewException) {
//            System.out.println("View não encontrada")
//        }
//    }
//}