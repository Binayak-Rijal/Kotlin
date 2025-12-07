package com.example.semesterthreeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semesterthreeproject.ui.theme.SemesterThreeProjectTheme

// Data class to hold card information
data class CardInfo(
    @DrawableRes val imageRes: Int,
    val text: String
)

class CardsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // It's a good practice to wrap the content with the app theme
            SemesterThreeProjectTheme {
                CardScreen()
            }
        }
    }
}

@Composable
fun CardScreen() {
    val cardItems = listOf(
        CardInfo(R.drawable.book, "TEXT"),
        CardInfo(R.drawable.house, "ADDRESS"),
        CardInfo(R.drawable.character, "CHARACTER"),
        CardInfo(R.drawable.creditcard, "BANK CARD"),
        CardInfo(R.drawable.key, "PASSWORD"),
        CardInfo(R.drawable.logistics, "LOGISTICS")
    )

    Scaffold { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.purple_200))
                .padding(paddingValues = padding)
                .verticalScroll(rememberScrollState()) // Make the column scrollable
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            // Title
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(text = "Card", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Simple and easy to use app", style = TextStyle(fontSize = 15.sp, color = Color.White))
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Cards Grid
            cardItems.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InfoCard(
                        info = rowItems[0],
                        modifier = Modifier.weight(1f)
                    )
                    if (rowItems.size > 1) {
                        InfoCard(
                            info = rowItems[1],
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f)) // Placeholder for alignment
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Settings Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .height(55.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.outline_settings_24),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "SETTINGS", style = TextStyle(fontWeight = FontWeight.Bold))
                }
            }
        }
    }
}

@Composable
fun InfoCard(info: CardInfo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(200.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(info.imageRes),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = info.text, style = TextStyle(fontWeight = FontWeight.Bold))
        }
    }
}

@Preview
@Composable
fun CardScreenPreview() {
    SemesterThreeProjectTheme {
        CardScreen()
    }
}