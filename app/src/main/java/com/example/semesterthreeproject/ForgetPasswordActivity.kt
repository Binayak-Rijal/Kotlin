package com.example.semesterthreeproject

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semesterthreeproject.ui.theme.Blue
import com.example.semesterthreeproject.ui.theme.PurpleGrey80
import com.example.semesterthreeproject.ui.theme.White
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ForgetPasswordBody()
        }
    }
}

@Composable
fun ForgetPasswordBody() {
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val activity = context as Activity
    val auth = FirebaseAuth.getInstance()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(White)
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                "Forgot Password",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Blue,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "Enter your email address and we'll send you a link to reset your password.",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { data ->
                    email = data
                },
                shape = RoundedCornerShape(15.dp),
                placeholder = {
                    Text("abc@gmail.com")
                },
                label = {
                    Text("Email")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = PurpleGrey80,
                    unfocusedContainerColor = PurpleGrey80,
                    focusedIndicatorColor = Blue,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledContainerColor = PurpleGrey80,
                    disabledIndicatorColor = Color.Transparent
                ),
                enabled = !isEmailSent && !isLoading
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (email.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Please enter your email",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(
                            context,
                            "Please enter a valid email",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        isLoading = true
                        // Send password reset email using Firebase
                        auth.sendPasswordResetEmail(email)
                            .addOnSuccessListener {
                                isLoading = false
                                isEmailSent = true
                                Toast.makeText(
                                    context,
                                    "Password reset email sent! Check your inbox.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            .addOnFailureListener { exception ->
                                isLoading = false
                                Toast.makeText(
                                    context,
                                    exception.message ?: "Failed to send reset email",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                },
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                enabled = !isEmailSent && !isLoading
            ) {
                Text(
                    when {
                        isLoading -> "Sending..."
                        isEmailSent -> "Email Sent"
                        else -> "Send Reset Link"
                    }
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(
                    "Back to Sign In",
                    color = Blue,
                    fontSize = 16.sp
                )
            }

            if (isEmailSent) {
                Spacer(modifier = Modifier.height(20.dp))

                TextButton(
                    onClick = {
                        isEmailSent = false
                        email = ""
                    }
                ) {
                    Text(
                        "Try another email",
                        color = Blue,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ForgetPasswordPreview() {
    ForgetPasswordBody()
}