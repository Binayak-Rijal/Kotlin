package com.example.semesterthreeproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semesterthreeproject.repository.ProductRepoImpl
import com.example.semesterthreeproject.ui.theme.Blue
import com.example.semesterthreeproject.ui.theme.White
import com.example.semesterthreeproject.viewmodel.ProductViewModel

@Composable
fun HomeScreen(){
    val productViewModel = remember { ProductViewModel(ProductRepoImpl())}
    LaunchedEffect(Unit) {
        productViewModel.getAllProducts()
    }
    Scaffold { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(
                        color = Blue,
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(16.dp)
            ) {
                Text("Himachal Pradesh, India")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Good morning, Alex!")
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Find your perfect\nadventure guide",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp)
                    .background(White)
            ) {
                Spacer(modifier = Modifier.height(48.dp))
            }
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Search destinations...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = 250.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Blue,
                    unfocusedContainerColor = White  // Fixed the typo here
                ),
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}