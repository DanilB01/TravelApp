package ru.tsu.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.tsu.travelapp.ui.theme.TravelAppTheme

class RegisterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    TravelAppTheme {
        Image(
            painter = painterResource(id = R.drawable.pic_credentials_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp)
                    .background(
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 22.dp)
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_name_credentials),
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey_light),
                            modifier = Modifier.size(15.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Ник",
                            color = colorResource(id = R.color.grey_light),
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.grey_light_alpha)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp)
                        .padding(top = 25.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey_light),
                            modifier = Modifier.size(15.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Пароль",
                            color = colorResource(id = R.color.grey_light),
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.grey_light_alpha)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp)
                        .padding(top = 25.dp)
                )
                OutlinedTextField(
                    value = password2,
                    onValueChange = { password2 = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_password),
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey_light),
                            modifier = Modifier.size(15.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Повторите пароль",
                            color = colorResource(id = R.color.grey_light),
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.grey_light_alpha)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp)
                        .padding(top = 25.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey_light),
                            modifier = Modifier.size(15.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "E-mail",
                            color = colorResource(id = R.color.grey_light),
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = colorResource(id = R.color.grey_light_alpha)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 22.dp)
                        .padding(top = 25.dp)
                )
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.orange),
                        contentColor = colorResource(id = R.color.white)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                        .height(52.dp)
                ) {
                    Text(
                        text = "Зарегистрироваться",
                        fontSize = 16.sp
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_separator_credentials),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_login_button_credentials),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp)
            )
        }
    }
}
