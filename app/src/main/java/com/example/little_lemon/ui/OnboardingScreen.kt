package com.example.little_lemon.ui

import android.content.Context
import android.util.Log
import android.util.Patterns
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.little_lemon.R
import com.example.little_lemon.navigation.Home

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier) {

    val sharedPreferences = LocalContext.current.getSharedPreferences(
        "LittleLemon.prefs",
        Context.MODE_PRIVATE
    )

    var firstName by rememberSaveable {
        mutableStateOf("")
    }
    var lastName by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var showSuccessAlertDialog by remember {
        mutableStateOf(false)
    }

    var showFailureAlertDialog by remember {
        mutableStateOf(false)
    }

    var showEmailValidationAlertDialog by remember {
        mutableStateOf(false)
    }

    if (showSuccessAlertDialog) {
        Alert(
            title = stringResource(id = R.string.profile_update_successful),
            onDismiss = {
                showSuccessAlertDialog = false
                sharedPreferences.edit()
                    .putString("firstName", firstName)
                    .putString("lastName", lastName)
                    .putString("email", email)
                    .putBoolean("clientLoggedIn", true)
                    .apply()

                navController.navigate(Home.route)
            })
    }

    if (showFailureAlertDialog) {
        Alert(
            textLine1 = stringResource(id = R.string.profile__missing_values_error_message_1),
            textLine2 = stringResource(id = R.string.profile__missing_values_error_message_2),
            title = stringResource(id = R.string.profile_update_failure),
            onDismiss = {
                showFailureAlertDialog = false
            })
    }

    if (showEmailValidationAlertDialog) {
        Alert(
            textLine1 = stringResource(id = R.string.invalid_email_format),
            title = stringResource(id = R.string.profile_update_failure),
            onDismiss = {
                showEmailValidationAlertDialog = false
            })
    }

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
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xFF495E57), shape = RectangleShape)
            ) {
                Text(
                    text = stringResource(id = R.string.get_to_know_you),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
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

                PersonalInfoHeader(R.string.personal_information)
                Spacer(modifier = Modifier.height(20.dp))

                PersonalInfoEntryBox(
                    boxType = R.string.first_name,
                    dataValue = firstName,
                    onValueChange = { firstName = it },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.height(10.dp))
                PersonalInfoEntryBox(
                    boxType = R.string.last_name,
                    dataValue = lastName,
                    onValueChange = { lastName = it},
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                Spacer(modifier = Modifier.height(10.dp))
                PersonalInfoEntryBox(
                    boxType = R.string.email,
                    dataValue = email,
                    onValueChange = { email = it},
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
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
                    if (!inputFieldsPopulatedFully(firstName, lastName, email)) {
                        showFailureAlertDialog = true
                    } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        showEmailValidationAlertDialog = true
                    } else {
                        showSuccessAlertDialog = true
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFF4CE14)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.register_button_text),
                    color = Color(0xFF333333),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}


@Composable
fun Alert(
    textLine1: String = "",
    textLine2: String = "",
    title: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text =title)
        },
        text = {
            Column {
                Text(text = textLine1)
                Text(text = textLine2)
            }

        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        dismissButton = {}
    )

}

fun inputFieldsPopulatedFully(firstName: String, lastName: String, email: String): Boolean {
    if (firstName.isBlank()) { return false }
    if (lastName.isBlank()) { return false }
    if (email.isBlank()) { return false }
    return true
}

@Composable
fun PersonalInfoHeader(
    @StringRes label: Int
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun PersonalInfoEntryBox(
    @StringRes boxType: Int,
    dataValue: String,
    onValueChange: (value: String) -> Unit,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.padding(end = 20.dp)) {
        Row() {
            Text(
                text = stringResource(id = boxType),
                modifier = Modifier.padding(top = 12.dp, bottom = 5.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Row {
            OutlinedTextField(
                value = dataValue,
                onValueChange = onValueChange,
                shape = MaterialTheme.shapes.large,
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                textStyle = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

@Composable
//@Preview
fun OnboardingScreenPreview() {

}