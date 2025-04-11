package com.example.feature.home

import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * This is a full integration test that may make more sense to test the HomeView,
 * but at the current stage of development it's simpler to test smaller Composable units.
 */
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class HomeViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `test transient counter`() {
        composeRule.activity.setContent {
            viewModel = viewModel()
            Text(
                text = "${viewModel.transientCounterSource.currentState()}"
            )
        }

        composeRule.onNodeWithText("0").assertIsDisplayed()

        viewModel.transientCounterSource.increment()

        composeRule.onNodeWithText("1").assertIsDisplayed()
    }

    @Test
    fun `test persistent counter`() = runTest {
        composeRule.activity.setContent {
            viewModel = viewModel()
            Text(
                text = "${viewModel.persistentCounterSource.currentState()}"
            )
        }

        composeRule.onNodeWithText("0").assertIsDisplayed()

        viewModel.persistentCounterSource.increment()

        composeRule.onNodeWithText("1").assertIsDisplayed()
    }
}
