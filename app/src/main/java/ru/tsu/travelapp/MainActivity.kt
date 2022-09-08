package ru.tsu.travelapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.tsu.travelapp.ui.theme.TravelAppTheme


data class BottomNavItem(
    val name: String,
    @DrawableRes
    val icon: Int
)

private val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.ic_home),
    BottomNavItem("Gallery", R.drawable.ic_gallery),
    BottomNavItem("Map", R.drawable.ic_map),
    BottomNavItem("Notifications", R.drawable.ic_notifications),
    BottomNavItem("Profile", R.drawable.ic_profile)
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                SetupView()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    TravelAppTheme {
        SetupView()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun SetupView() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    systemUiController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = useDarkIcons
    )

    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController = navController) }
    ) {
        NavGraph(navController = navController)
    }
}

@Composable
private fun BottomNav(navController: NavHostController) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier
            .height(51.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.name
            BottomNavigationItem(
                selected = isSelected,
                icon = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(
                                id = item.icon
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(21.dp)
                                .align(Alignment.Center)
                        )
                        if (isSelected) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_active),
                                contentDescription = null,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(5.dp)
                                    .align(Alignment.BottomCenter)
                            )
                        }
                    }
                },
                onClick = {
                    navController.navigate(item.name) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = bottomNavItems.first().name,
    ) {
        composable(bottomNavItems.first().name) { MainScreen() }
        composable(bottomNavItems[1].name) {}
        composable(bottomNavItems[2].name) {}
        composable(bottomNavItems[3].name) {}
        composable(bottomNavItems.last().name) { ProfileScreen() }
    }
}

@Composable
private fun MainScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchState by remember { mutableStateOf(false)}

    var cityDetails by remember { mutableStateOf(Pair("","")) }
    if(cityDetails.first.isNotBlank()) {
        DetailsScreen(cityDetails) {
            cityDetails = Pair("","")
        }
        return
    }

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

    val imageUrl =
        "https://mir-s3-cdn-cf.behance.net/project_modules/fs/731afa34542037.56d4c4da102e7.jpg"

    val cities = listOf(
        Pair("Новокузнецк", imageUrl),
        Pair("Екатеринбург", imageUrl),
        Pair("Калининград", imageUrl),
        Pair("Владивосток", imageUrl),
        Pair("Петропавловск-Камчатский", imageUrl),
        Pair("Иркутск", imageUrl),
        Pair("Казань", imageUrl),
        Pair("Воркута", imageUrl),
        Pair("Магадан", imageUrl),
        Pair("Архангельск", imageUrl)
    )

    var currentCities by remember { mutableStateOf(cities) }

    TravelAppTheme {
        LazyColumn(
            modifier = Modifier
                .background(color = colorResource(id = R.color.white))
        ) {
            item {
                Spacer(Modifier.size(30.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        isSearchState = searchQuery.isNotEmpty()
                        currentCities = cities.filter {
                            it.first.contains(searchQuery, ignoreCase = true)
                        }
                    },
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
                if(!isSearchState) {
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
                        items(cities) { city ->
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
                                    model = city.second,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(width = 145.dp, height = 200.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Text(
                                    text = city.first,
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
                } else {
                    Spacer(modifier = Modifier.size(35.dp))
                }
            }
            items(currentCities) { city ->
                Box(
                    modifier = Modifier.padding(horizontal = 22.dp)
                        .clickable {
                            cityDetails = city
                        }
                ) {
                    AsyncImage(
                        model = city.second,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(
                        text = city.first,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier
                            .padding(start = 9.dp, bottom = 21.dp)
                            .align(Alignment.BottomStart)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
            item {
                Spacer(modifier = Modifier.size(51.dp))
            }
        }
    }
}

@Composable
private fun DetailsScreen(cityDetails: Pair<String, String>, exit: () -> Unit) {
    LazyColumn {
        item {
            Box {
                AsyncImage(
                    model = cityDetails.second,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp)
                        .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(26.dp)
                        .size(height = 15.dp, width = 30.dp)
                        .clickable { exit() }
                )
                Text(
                    text = cityDetails.first,
                    color = Color.White,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 26.dp, bottom = 16.dp)
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Кра́сная пло́щадь — главная площадь Москвы, расположена между Московским Кремлём (к западу) и Китай-городом (на восток). Выходит к берегу Москвы-реки через пологий Васильевский спуск. Площадь тянется вдоль северо-восточной стены Кремля, от Кремлёвского проезда и проезда Воскресенские Ворота до Васильевского спуска, выходящего к Кремлёвской набережной. На восток от Красной площади отходят Никольская улица, Ильинка и Варварка. Вдоль западной стороны площади расположен Московский Кремль, вдоль восточной — Верхние торговые ряды и Средние торговые ряды. Входит в единый ансамбль с Московским Кремлём, однако исторически является частью Китай-города",
                color = colorResource(id = R.color.description_color),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "Рейтинг",
                color = colorResource(id = R.color.title_color),
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
    }
}

@Composable
private fun ProfileScreen() {

}