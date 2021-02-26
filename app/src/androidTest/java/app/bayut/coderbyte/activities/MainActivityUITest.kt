package app.bayut.coderbyte.activities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule
import app.bayut.coderbyte.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

public class MainActivityUITest {
    @Rule @JvmField
    public val activityTest = ActivityTestRule(MainActivity::class.java)
    var mainActivity: MainActivity? = null

    @Before
    fun setUp() {
        mainActivity = activityTest.activity as MainActivity
    }

    @Test
    fun UiInit() {
        //recyclerviewInit
        val books_rv = mainActivity?.findViewById<RecyclerView>(R.id.books_rv)
        assertNotNull(books_rv)
    }

    @After
    fun tearDown() {
    }
}