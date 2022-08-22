package ru.tsu.travelapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tsu.travelapp.ui.theme.TravelAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        TravelAppTheme {
            Image(
                painter = painterResource(id = R.drawable.pic_credentials_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Путешествия",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 50.dp)
                )
                Text(
                    text = "Узнай свою страну!",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.orange_alpha),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                ) {
                    Text(
                        text = "Продолжить без авторизации",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp)
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.orange),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(162.dp, 52.dp)
                ) {
                    Text(
                        text = "Вход",
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.size(22.dp))
                Button(
                    onClick = {
                        startActivity(Intent(this@LoginActivity,
                            RegisterActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.orange),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.size(162.dp, 52.dp)
                ) {
                    Text(
                        text = "Регистрация",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}