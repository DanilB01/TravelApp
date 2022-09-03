package ru.tsu.travelapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.tsu.travelapp.ui.theme.TravelAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetupView()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SetupView()
}

@Composable
private fun SetupView() {
    var searchQuery by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        AlertDialog(
            title = {
                Text(text = "Test")
            },
            text = {
                Text("Test")
            },
            onDismissRequest = {

            },
            buttons = {
                Button(onClick = { showDialog = false }) {
                    Text("test")
                }
            }
        )
    }

    val imageUrl = "https://media.baamboozle.com/uploads/images/37562/1589041812_179097"
    val images = List(5) { imageUrl }

    TravelAppTheme {
        LazyColumn(
            modifier = Modifier
                .background(color = colorResource(id = R.color.white))
        ) {
            item {
                Spacer(Modifier.size(37.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = colorResource(id = R.color.grey_light)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Поиск",
                            color = colorResource(id = R.color.grey_light),
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.white),
                        textColor = colorResource(id = R.color.grey_light),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .shadow(1.dp)
                )
                Text(
                    text = "Рекомендации",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 25.dp, start = 22.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 22.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    items(images) { image ->
                        Box(
                            modifier = Modifier.clickable(
                                onClick = {
                                    showDialog = true
                                }
                            )
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pic_place),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(width = 125.dp, height = 208.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .align(Alignment.BottomCenter)
                            )
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(width = 145.dp, height = 200.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Text(
                                text = "Ульяновск",
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.white),
                                modifier = Modifier
                                    .padding(start = 9.dp, bottom = 21.dp)
                                    .align(Alignment.BottomStart)
                            )
                        }
                    }
                }
                Text(
                    text = "Популярное",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 30.dp, start = 22.dp)
                )
                Spacer(modifier = Modifier.size(12.dp))
            }
            items(images) { image ->
                Box(
                    modifier = Modifier.padding(horizontal = 22.dp)
                ) {
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(
                        text = "Ульяновск",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier
                            .padding(start = 9.dp, bottom = 21.dp)
                            .align(Alignment.BottomStart)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}