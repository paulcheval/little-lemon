package com.example.little_lemon.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.little_lemon.R
import com.example.little_lemon.navigation.Home
import com.example.little_lemon.navigation.Onboarding
import com.example.little_lemon.navigation.Profile

@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier) {

    val sharedPreferences = LocalContext.current.getSharedPreferences(
        "LittleLemon.prefs",
        Context.MODE_PRIVATE
    )

    val firstName= sharedPreferences.getString("firstName", "")?: ""
    val lastName= sharedPreferences.getString("lastName", "")?: ""
    val email= sharedPreferences.getString("email", "")?: ""

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .border(BorderStroke(2.dp, Color.Black))
            .padding(top = 15.dp, bottom = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            modifier = Modifier

                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.little_lemon_logo_desc),
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = stringResource(id = R.string.personal_information),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(
            modifier
                .fillMaxWidth()
                .weight(3f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(start = 15.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))


                PersonInfo(
                    boxType = R.string.first_name,
                    dataValue = firstName,

                )
                Spacer(modifier = Modifier.height(10.dp))
                PersonInfo(
                    boxType = R.string.last_name,
                    dataValue = lastName,

                )
                Spacer(modifier = Modifier.height(10.dp))
                PersonInfo(
                    boxType = R.string.email,
                    dataValue = email,

                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Button(
                onClick = {
                    sharedPreferences
                        .edit()
                        .remove("firstName")
                        .remove("lastName")
                        .remove("email")
                        .apply()
                    navController.navigate(Onboarding.route)
                },
                colors = ButtonDefaults.buttonColors(
                    Color(0xFFF4CE14)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.logout),
                    color = Color(0xFF333333)
                )
            }
        }
    }
}


@Composable
fun PersonInfo(
    @StringRes boxType: Int,
    dataValue: String,
    modifier: Modifier = Modifier
) {
    Column {
        Row() {
            Text(
                text = stringResource(id = boxType),
                modifier = Modifier.padding(top = 12.dp, bottom = 5.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row {
            Text(
                text = dataValue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp)
                    .border(1.dp, Color(0xFF333333), shape = MaterialTheme.shapes.large),
                style = MaterialTheme.typography.displayMedium,

            )
        }
    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    //ProfileScreen()
}