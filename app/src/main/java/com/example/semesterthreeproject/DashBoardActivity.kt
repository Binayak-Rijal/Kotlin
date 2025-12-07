package com.example.semesterthreeproject

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.semesterthreeproject.ui.theme.Blue
import com.example.semesterthreeproject.ui.theme.White

class DashBoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashBoard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard() {

    val activity = (LocalContext.current) as Activity

    val email = activity.intent.getStringExtra("email")
    val password = activity.intent.getStringExtra("password")

    data class NavItem(val label: String, val icon: Int)

    var selectedIndex by remember { mutableIntStateOf(0) }

    val navList = listOf(
        NavItem("Home", R.drawable.baseline_home_24),
        NavItem("Search", R.drawable.baseline_search_24),
        NavItem("Notification", R.drawable.baseline_notifications_none_24),
        NavItem("Profile", R.drawable.outline_person_text_24)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ecommerce") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    navigationIconContentColor = White,
                    titleContentColor = White,
                    actionIconContentColor = White
                ),
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_search_24),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_business_messages_24),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = { selectedIndex = index },
                        icon = {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text("Email: $email")
            Text("Password: $password")
        }
    }
}
