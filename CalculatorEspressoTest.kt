package com.simplemobiletools.calculator

import com.simplemobiletools.calculator.activities.MainActivity
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.Rule
import android.support.test.rule.ActivityTestRule
import android.view.View
import org.junit.Test
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.action.ViewActions.*


import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import android.support.test.espresso.matcher.ViewMatchers.*
import java.util.*


@RunWith(AndroidJUnit4::class)
class CalculatorEspressoTest {
    @Rule @JvmField val activity =
            ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun openApplicationTest() {
        val textView = onView(
                allOf<View>(withText("Calculator_debug"),
                        childAtPosition(
                                allOf<View>(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()))
        textView.check(matches(withText("Calculator_debug")))
    }


    @Test
    fun changeRandomlyThemeTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        val settingsButton = onView(
                allOf(withId(R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`<String>("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()))
        settingsButton.perform(click())

        val customizedColor = onView(
                allOf(withId(R.id.settings_customize_colors_holder),
                        childAtPosition(
                                allOf(withId(R.id.settings_holder),
                                        childAtPosition(
                                                withId(R.id.settings_scrollview),
                                                0)),
                                0)))
        customizedColor.perform(scrollTo(), click())

        val chosenTheme = onView(
                allOf(withId(R.id.customization_theme_holder),
                        childAtPosition(
                                allOf(withId(R.id.customization_holder),
                                        childAtPosition(
                                                withId(R.id.customization_scrollview),
                                                0)),
                                0)))
        chosenTheme.perform(scrollTo(), click())

        var givenThemeList = Arrays.asList("Light", "Dark", "Dark red", "Black & White", "Custom")
        var rand = Random()
        var randomElement = givenThemeList[rand.nextInt(givenThemeList.size)]

        var positionNumber = 0
        when (randomElement) {
            "Light" -> positionNumber = 0
            "Dark" -> positionNumber = 1
            "Dark red" -> positionNumber = 2
            "Black & White" -> positionNumber = 3
            "Custom" -> positionNumber = 4
        }

        val themeChoice = onView(
                allOf(withText(randomElement),
                        childAtPosition(
                                allOf(withId(R.id.dialog_radio_group),
                                        childAtPosition(
                                                withId(R.id.dialog_radio_holder),
                                                0)),
                                positionNumber)))
        themeChoice.perform(scrollTo(), click())

        val saveButton = onView(
                allOf(withId(R.id.save), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        2),
                                0),
                        isDisplayed()))
        saveButton.perform(click())

        customizedColor.perform(scrollTo(), click())

        val checkedColor = onView(
                allOf(withId(R.id.customization_theme),
                        childAtPosition(
                                allOf(withId(R.id.customization_theme_holder),
                                        childAtPosition(
                                                withId(R.id.customization_holder),
                                                0)),
                                1),
                        isDisplayed()))
        checkedColor.check(matches(withText(randomElement)))
    }

    @Test
    fun randomWidgetColorTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        val settingsButton = onView(
                allOf(withId(R.id.title), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()))
        settingsButton.perform(click())

        val widgetColorButton = onView(
                allOf(withId(R.id.settings_customize_widget_colors_holder),
                        childAtPosition(
                                allOf(withId(R.id.settings_holder),
                                        childAtPosition(
                                                withId(R.id.settings_scrollview),
                                                0)),
                                1)))
        widgetColorButton.perform(scrollTo(), click())

        val chosenColor = onView(
                allOf(withId(R.id.config_text_color),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()))
        chosenColor.perform(click())

        val random = Random()
        val nextInt = random.nextInt(256 * 256 * 256)
        val colorCode = String.format("%06x", nextInt)

        val colorField = onView(
                allOf(withId(R.id.color_picker_new_hex), withText("F57C00"),
                        childAtPosition(
                                allOf(withId(R.id.color_picker_hex_codes_holder),
                                        childAtPosition(
                                                withId(R.id.color_picker_holder),
                                                1)),
                                3),
                        isDisplayed()))
        colorField.perform(replaceText(colorCode))

        pressBack()

        val saveButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)))
        saveButton.perform(scrollTo(), click())

        chosenColor.perform(click())

        val colorCodeHex = String.format("#%06x", nextInt)
        val colorCodeHexUpper = colorCodeHex.toUpperCase()
        val chosenColorHexCode = onView(
                allOf(withId(R.id.color_picker_old_hex),
                        childAtPosition(
                                allOf(withId(R.id.color_picker_hex_codes_holder),
                                        childAtPosition(
                                                withId(R.id.color_picker_holder),
                                                1)),
                                0),
                        isDisplayed()))
        chosenColorHexCode.check(matches(withText(colorCodeHexUpper)))
    }



    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

}