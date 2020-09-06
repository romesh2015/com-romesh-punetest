package com.romesh.punetest.profile.view.ui
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.romesh.punetest.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@Suppress("DEPRECATION")
@SuppressWarnings( "deprecation" )
@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(ProfileActivity::class.java)
    @Before
    fun setUp() {
    }
    @Test
    fun isActivityProfileResourceFileExist() {
        Espresso.onView(withId(R.id.activity_profile_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun isStackViewViewExist() {
        Espresso.onView(withId(R.id.stack_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
    @Test
    fun onCreateCalled() {
        val scenario = launchActivity<ProfileActivity>()
        scenario.onActivity { activity ->
            if(activity.hasWindowFocus()){
                return@onActivity
            }
        }
    }
    @After
    fun tearDown() {
    }
}