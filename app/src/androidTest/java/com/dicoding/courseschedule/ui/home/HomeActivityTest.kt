package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
        Intents.init()
    }

    @Test
    fun whenAddCourseClickedDisplayedAddCourseActivity(){
        onView(withId(R.id.action_add)).perform(click())
        intended(hasComponent(AddCourseActivity::class.java.name))
        onView(withHint(R.string.course_name)).check(matches(isDisplayed()))
        onView(withHint(R.string.note)).check(matches(isDisplayed()))
        onView(withHint(R.string.lecturer)).check(matches(isDisplayed()))
    }


}