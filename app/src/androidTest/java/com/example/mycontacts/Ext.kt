package com.example.mycontacts

import android.view.View
import android.widget.TextView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher as Matcher1

/** Функция которая нажимает на конпку в нужной аозиции RecyclerView **/

fun clickChildViewWithId(id: Int): ViewAction{
    return object : ViewAction {
        override fun getConstraints(): Matcher1<View>? {
            return null
        }

        override fun getDescription(): String {
            return "нажать кнопку изменить"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(id)
            v.performClick()
        }
    }
}
/** Функция которая изменяет текст в поле textView  **/

fun setTextInTextView(value: String): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): org.hamcrest.Matcher<View> {
            return CoreMatchers.allOf(
                ViewMatchers.isDisplayed(), ViewMatchers.isAssignableFrom(
                TextView::class.java))
        }

        override fun perform(uiController: UiController, view: View) {
            (view as TextView).text = value
        }

        override fun getDescription(): String {
            return "replace text"
        }
    }
}