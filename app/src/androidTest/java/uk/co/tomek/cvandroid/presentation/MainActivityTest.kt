package uk.co.tomek.cvandroid.presentation

import android.support.test.filters.MediumTest
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.content_scrolling.view.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.tomek.cvandroid.R

/**
 * UI/Integration test independent needs reliable network connection to work properly.
 * And also is not very reliable, recommended would be mocked server test.
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun verifyThatCvDataIsDisplayed() {
        // when
        activityTestRule.launchActivity(null)

        // then
        onView(withId(R.id.recycler_items_list)).check(matches(isDisplayed()))
    }
}