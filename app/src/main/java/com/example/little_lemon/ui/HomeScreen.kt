package com.example.little_lemon.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.little_lemon.R
import com.example.little_lemon.db.MenuItem
import com.example.little_lemon.navigation.Profile
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.little_lemon.db.MenuItemRespository


@Composable
fun HomeScreen(navController: NavHostController) {
    //val viewModel: LittleLemonMenuViewModel = viewModel()
    val menuItemRepository = MenuItemRespository(LocalContext.current)




    Log.d("HomeScreen", "Before getting and observing")
    //val menuItems = viewModel.()
     //   .observeAsState(emptyList()).value
    val menuItems = menuItemRepository.getAllItems()
        .observeAsState(emptyList()).value
    Log.d("HomeScreen", "After getting and observing ${menuItems.size}")

    Column {
        UpperScreen(navController)
        MenuScreen(menuItems)
    }


}

@Composable
fun UpperScreen(navController: NavHostController) {
    Column {
        Header(navController)
        HeroSection()
    }

}

@Composable
fun Header(navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
    ) {
        Spacer(modifier = Modifier.weight(2f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.little_lemon_logo_desc),
            modifier = Modifier
                .height(50.dp)
                .width(200.dp)
                .weight(2f)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.little_lemon_logo_desc),
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .weight(1f)
                .clickable(
                    onClick = {
                        navController.navigate(Profile.route)
                    }
                )
        )
    }
}

@Composable
fun HeroSection() {
    var searchPhrase by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(Color(0xFF495E57))
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.restaurant_name),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF4CE14)
        )
        Text(
            text = stringResource(id = R.string.location),
            fontSize = 24.sp,
            color = Color(0xFFEDEFEE)
        )
        Row(
            modifier = Modifier
                .padding(top = 18.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = stringResource(id = R.string.hero_message),
                color = Color(0xFFEDEFEE),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 28.dp)
                    .fillMaxWidth(0.6f)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = stringResource(id = R.string.little_lemon_logo_desc),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.Top)
                    .height(175.dp)
                    .clip(RoundedCornerShape(20.dp))
            )


        }
        Row(
            modifier = Modifier
                .padding(top = 18.dp)
                .background(Color(0xFF495E57))

        ) {


            OutlinedTextField(
                value = searchPhrase,
                onValueChange =  { searchPhrase = it },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = null )
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_criteria_placeholder))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .clipToBounds(),
            )

        }

    }
}

@Composable
fun MenuScreen(menuItems: List<MenuItem>) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        DeliveryMessage()
        CategoriesScreen()
        MenuItems(menuItems)
    }
}

@Composable
fun DeliveryMessage() {
    Row (horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.Order_for_delivery),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Left)
    }

}

@Composable
fun CategoriesScreen() {
    LazyRow() {
        items(Categories.values()) {category ->
            MenuCategory(category = category.name)
        }
    }

}

@Composable
fun MenuCategory(category: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Color.LightGray),
        shape = RoundedCornerShape(40),
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = category
        )
    }
}

@Composable
fun MenuItems(menuItems: List<MenuItem>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(menuItems.size) {i ->
            val menuItem = menuItems[i]
            MenuItemCard(menuItem = menuItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemCard(menuItem: MenuItem) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = menuItem.title, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = menuItem.description,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = menuItem.price, color = Color.Gray, fontWeight = FontWeight.Bold
                )
            }
            GlideImage(
                model = menuItem.image,
                contentDescription = menuItem.description )
           /* Image(
                painter = painterResource(id = menuItem.image),
                contentDescription = "",
            )*/
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}
@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
