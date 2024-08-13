package test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import org.junit.Rule
import androidx.navigation.testing.TestNavHostController
import com.example.baguessr30.BAApp
import com.example.baguessr30.BAScreen
import com.example.baguessr30.R
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals



class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            BAApp(navController = navController)
        }
    }

    @Test
    fun navHost_VerifyStartDestination(){
        navController.assertCurrentRouteName(BAScreen.Title.name)
    }

    @Test
    fun navHost_titleToGameMode(){
        val testInput = "Swag"

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
        composeTestRule.onNodeWithTag("textfield").performTextInput(testInput)
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
        navController.assertCurrentRouteName(BAScreen.Choose.name)
    }


}