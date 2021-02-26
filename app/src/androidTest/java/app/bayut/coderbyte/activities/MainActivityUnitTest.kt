package app.bayut.coderbyte.activities

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import app.bayut.coderbyte.adapter.BookAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class MainActivityUnitTest {
    @Rule
    @JvmField
    public val activityTest = ActivityTestRule(MainActivity::class.java)
    var mainActivity: MainActivity? = null


    @Before
    fun setUp() {
        mainActivity = activityTest.activity
    }

    @Test
    public fun getItemClick() {
        val rcv: RecyclerView = Mockito.mock(RecyclerView::class.java)
        val list: ArrayList<app.bayut.coderbyte.domain.datamodels.Book> = ArrayList()
        val listId = ArrayList<String>()
        listId.add("9355183956e3445e89735d877b798689")
        val imageUrls = ArrayList<String>()
        imageUrls.add("https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A4VE4E5A4L&Signature=O6GbBqq1V1MEt44tgKJceUSkMmk%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEAIaCXVzLWVhc3QtMSJGMEQCIFXki2RVr7jPIG8hMLYeVummv4xQS2B9CRqzdpcnab7FAiBh%2Fojh0pw0TYWdwvAr5g0y03QjAFhtF9u97F33WOkwcyrOAQgbEAIaDDE4NDM5ODk2Njg0MSIMY9VH8wRIFk7idWw2KqsBFLVedSJ46UWctrSlDOdLuoibXGr7Vuv4KwUKQreNaSSW4SpFC0DXERbx4y3Fd9sRPbRe%2F7l83hKWXrk90%2BwQpA7agAVNiAAWTx%2FQpoLzcpuZ0e2C0Dixh%2FWvYKp5kyPkC4fM9DnBkCHPsPdholBvm5jYsEa8V8I6osyv%2FA5vaUXlEVQm6NYPA3AeBlP2F9bu3JSlKMUTlmya4Wd%2FsYOjAdRPxYvtsarC%2BwpqMKyY2oEGOuEBm%2BeKC3DixHdu8CcFiKSIWjx17j4lDm5%2BRcry%2BdJHrKe8RmcnAJe2onXlWva7xuBg6WIpv2mmP1F%2Bqgo1CxigbVeD83PJm2tyXzoLSeog0y2zR8FAd7aDqNC6h04DoxcOgKDVi2SXsglJP7kphty98tpHrzLOoZuHYhRTOOXIJZ2dkmtbSGA73PEyhkhL3P9d7MlZswNOw%2B12NZh3zpsJfGO8F78Df8wejz%2BpImvbebDPSZcyPASLvdr2XzFEwxC5LGlCXaPO1RlOfEhKXcuPk0w6eoz66gCsU3ZF9Jhf3pkI&Expires=1614191167")
        val imageUrlsThumbnail = ArrayList<String>()
        imageUrlsThumbnail.add("https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A4VE4E5A4L&Signature=O6GbBqq1V1MEt44tgKJceUSkMmk%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEAIaCXVzLWVhc3QtMSJGMEQCIFXki2RVr7jPIG8hMLYeVummv4xQS2B9CRqzdpcnab7FAiBh%2Fojh0pw0TYWdwvAr5g0y03QjAFhtF9u97F33WOkwcyrOAQgbEAIaDDE4NDM5ODk2Njg0MSIMY9VH8wRIFk7idWw2KqsBFLVedSJ46UWctrSlDOdLuoibXGr7Vuv4KwUKQreNaSSW4SpFC0DXERbx4y3Fd9sRPbRe%2F7l83hKWXrk90%2BwQpA7agAVNiAAWTx%2FQpoLzcpuZ0e2C0Dixh%2FWvYKp5kyPkC4fM9DnBkCHPsPdholBvm5jYsEa8V8I6osyv%2FA5vaUXlEVQm6NYPA3AeBlP2F9bu3JSlKMUTlmya4Wd%2FsYOjAdRPxYvtsarC%2BwpqMKyY2oEGOuEBm%2BeKC3DixHdu8CcFiKSIWjx17j4lDm5%2BRcry%2BdJHrKe8RmcnAJe2onXlWva7xuBg6WIpv2mmP1F%2Bqgo1CxigbVeD83PJm2tyXzoLSeog0y2zR8FAd7aDqNC6h04DoxcOgKDVi2SXsglJP7kphty98tpHrzLOoZuHYhRTOOXIJZ2dkmtbSGA73PEyhkhL3P9d7MlZswNOw%2B12NZh3zpsJfGO8F78Df8wejz%2BpImvbebDPSZcyPASLvdr2XzFEwxC5LGlCXaPO1RlOfEhKXcuPk0w6eoz66gCsU3ZF9Jhf3pkI&Expires=1614191167")


        list.add(
            app.bayut.coderbyte.domain.datamodels.Book(
                "2019-02-24 04:04:17.566515",
                "AED 5",
                "Notebook",
                "878bf592579410fba52941d00b62f94",
                listId,
                imageUrls,
                imageUrlsThumbnail
            )

        )
        var adapter: BookAdapter = Mockito.mock<BookAdapter>(BookAdapter::class.java)
        adapter = BookAdapter(list, mainActivity?.baseContext, null)
        rcv.adapter = adapter

        if (Mockito.`when`(rcv.getChildAt(0)) != null)
            openIntent()
        //adapter.addData(usersList)
    }

    private fun openIntent() {
        Log.d("intent", "Intent Pass")
    }

    @After
    fun tearDown() {
    }
}