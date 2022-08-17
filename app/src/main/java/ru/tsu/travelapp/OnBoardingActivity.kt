package ru.tsu.travelapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class OnBoardingActivity : ComponentActivity() {
    private var step = 1
        set(value) {
            field = value
            this.setContent {
                when (step) {
                    2 -> {
                        SetupScreen(
                            R.drawable.ic_man2_onboarding,
                            R.drawable.ic_items2_onboarding)
                    }
                    else -> {
                        SetupScreen(
                            R.drawable.ic_man3_onboarding,
                            R.drawable.ic_items3_onboarding)
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupScreen(
                R.drawable.ic_man1_onboarding,
                R.drawable.ic_items1_onboarding
            )
        }
    }

    @Composable
    private fun SetupScreen(
        @DrawableRes
        manPic: Int,
        @DrawableRes
        itemsPic: Int,
    ) {
        Column {
            Box(
                modifier = Modifier.padding(top = 45.dp)
            ) {
                Image(
                    painter = painterResource(id = manPic),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                )
                Image(
                    painter = painterResource(id = itemsPic),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    if(step < 3) {
                        step++
                    } else {
                        startActivity(Intent(this@OnBoardingActivity, LoginActivity::class.java))
                        finish()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.orange),
                    contentColor = colorResource(id = R.color.white)
                ),
                shape = RoundedCornerShape(7.dp),
                modifier = Modifier
                    .padding(bottom = 50.dp, end = 10.dp)
                    .align(Alignment.End)
            ) {
                Text(
                    text = if (step < 3) "Продолжить" else "Начать",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(resId = R.font.roboto_slab))
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun screenPreview() {
        SetupScreen(
            manPic = R.drawable.ic_man1_onboarding,
            itemsPic = R.drawable.ic_items1_onboarding
        )
    }
}