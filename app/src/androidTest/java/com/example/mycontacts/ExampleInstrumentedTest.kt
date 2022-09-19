package com.example.mycontacts

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

/**
 * Инструментальный тест, который будет выполняться на устройстве Android..
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Тест на добавление контакта и отображения в списке
     */

    @Test
    fun checkAllComponentIsVisible_isSuccess() {
        Espresso.onView(withId(R.id.fabAddContact))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        val name = "Nikita"

        Espresso.onView(withId(R.id.etName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.typeText(name))
            .check(ViewAssertions.matches(ViewMatchers.withText(name)))

        val surname = "Panchenko"

        Espresso.onView(withId(R.id.etSurname))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.typeText(surname))
            .check(ViewAssertions.matches(ViewMatchers.withText(surname)))

        val phone = "+799999999"

        Espresso.onView(withId(R.id.etNumber))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.typeText(phone))
            .check(ViewAssertions.matches(ViewMatchers.withText(phone)))

        Espresso.onView(withId(R.id.btnSave))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvContacts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * Тест на изменение контакта из списка по заданной позиции
     *  и отображение изменений в списке
     */

    @Test
    fun editContact() {
        Espresso.onView(withId(R.id.rvContacts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithId(R.id.tvEdit)
                )
            )
        val name = "Maksim"

        Espresso.onView(withId(R.id.etName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(setTextInTextView(name))
            .check(ViewAssertions.matches(ViewMatchers.withText(name)))

        val surname = "Naletov"

        Espresso.onView(withId(R.id.etSurname))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(setTextInTextView(surname))
            .check(ViewAssertions.matches(ViewMatchers.withText(surname)))

        val phone = "+77777777777"

        Espresso.onView(withId(R.id.etNumber))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(setTextInTextView(phone))
            .check(ViewAssertions.matches(ViewMatchers.withText(phone)))

        Espresso.onView(withId(R.id.btnSaveChanges))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvContacts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    /**
     * Тест на удаления контакта из списка
     */

    @Test
    fun deleteContact() {
        Espresso.onView(withId(R.id.rvContacts))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildViewWithId(R.id.tvEdit)
                )
            )
        Espresso.onView(withId(R.id.btnDelete))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        Espresso.onView(withId(R.id.rvContacts))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}