package ru.tsu.travelapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.tsu.travelapp.ui.theme.TravelAppTheme

class LaunchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupScreen()
        }
        GlobalScope.launch {
            delay(3000)
            startActivity(Intent(this@LaunchActivity, OnBoardingActivity::class.java))
            finish()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SetupScreen() {
    Image(
        painter = painterResource(id = R.drawable.ic_launch),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 75.dp)
    )
}