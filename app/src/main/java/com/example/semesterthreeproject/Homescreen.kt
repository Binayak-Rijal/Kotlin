package com.example.semesterthreeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semesterthreeproject.ui.theme.SemesterThreeProjectTheme

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SemesterThreeProjectTheme {
                HomeUI()
            }
        }
    }
}

@Composable
fun HomeUI() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            SearchBar()
            Spacer(Modifier.height(10.dp))
            LocationBar()
            Spacer(Modifier.height(15.dp))
            BannerSection()
            Spacer(Modifier.height(15.dp))
            CategoryRow()
            Spacer(Modifier.height(20.dp))
            FlashSaleSection()
        }
    }
}

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        placeholder = { Text("Search here") },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun LocationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6), RoundedCornerShape(12.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFFF6A00))
        Spacer(Modifier.width(8.dp))
        Text("St. no 8, Korangi, Khi")
    }
}

@Composable
fun BannerSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(0xFFFFE082), RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Column {
            Text("Time for Special Deal", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("70% Off", fontSize = 28.sp, fontWeight = FontWeight.Black, color = Color(0xFFE65100))
            Spacer(Modifier.height(10.dp))
            Button(onClick = {}, shape = RoundedCornerShape(10.dp)) {
                Text("Shop Now")
            }
        }
    }
}

@Composable
fun CategoryRow() {
    val categories = listOf("Beauty", "Home", "Fashion", "Appliances", "Party", "Bags")

    LazyRow {
        items(categories) { item ->
            Column(
                modifier = Modifier
                    .padding(end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFFFF3E0), RoundedCornerShape(16.dp))
                )
                Spacer(Modifier.height(5.dp))
                Text(item, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun FlashSaleSection() {
    Text("Flash Sale", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(6.dp))
    Text("Only few items left, ending soon!")

    Spacer(Modifier.height(10.dp))

    LazyRow {
        items(3) { index ->
            FlashCard()
        }
    }
}

@Composable
fun FlashCard() {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(end = 12.dp)
            .background(Color.White, RoundedCornerShape(14.dp))
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color(0xFFFBE9E7), RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.height(8.dp))
        Text("Product Name", fontWeight = FontWeight.Bold)
        Text("PKR 800", color = Color.Red)
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = {},
            icon = { Text("🏠") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("🔍") },
            label = { Text("Explore") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("🔔") },
            label = { Text("Notifications") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("🛒") },
            label = { Text("My Cart") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = { Text("👤") },
            label = { Text("Me") }
        )
    }
}
