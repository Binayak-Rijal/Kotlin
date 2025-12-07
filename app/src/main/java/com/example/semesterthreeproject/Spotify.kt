package com.example.semesterthreeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Spotify : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppScreen()
        }
    }
}

@Composable
fun MusicAppScreen() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF121212))
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Recently played",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            // Recently Played
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                ArtistItem("Godsmack", R.drawable.godsmack)
                Spacer(modifier = Modifier.width(16.dp))
                ArtistItem("Led Zepplin", R.drawable.zepplin)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Wrapped 2021 section
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "#SPOTIFYWRAPPED",
                    color = Color(0xFF888888),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your 2021 in review",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Review Cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                YearReviewCard(
                    text = "Your Top Songs in 2021",
                    textColor = Color.White,
                    imageRes = R.drawable.led
                )
                Spacer(modifier = Modifier.width(12.dp))
                YearReviewCard(
                    text = "Your Artists\nRevealed",
                    textColor = Color.White,
                    imageRes = R.drawable.album
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Editor's Picks
            Text(
                text = "Editor's picks",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                EditorPickItem(
                    number = "1",
                    artists = "Ed Sheeran, Big Sean,\nJuice WRLD, Post Malone",
                    numberColor = Color(0xFFD4E157),
                    imageRes = R.drawable.shapeofyou
                )
                Spacer(modifier = Modifier.width(12.dp))
                EditorPickItem(
                    number = "2",
                    artists = "Mitski, Tame Impala,\nGlass Animals, Charli XCX",
                    numberColor = Color(0xFF80CBC4),
                    imageRes = R.drawable.letithappen
                )
            }
        }
    }
}

@Composable
fun ArtistItem(name: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun YearReviewCard(text: String, textColor: Color, imageRes: Int) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        // Full bright image (no dim)
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = text,
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

@Composable
fun EditorPickItem(
    number: String,
    artists: String,
    numberColor: Color,
    imageRes: Int
) {
    Column(modifier = Modifier.width(150.dp)) {

        Box(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            // Full bright image
            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Large number on top
            Text(
                text = number,
                color = numberColor.copy(alpha = 0.4f),
                fontSize = 120.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = artists,
            color = Color.White,
            fontSize = 11.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMusicApp() {
    MusicAppScreen()
}
