package com.example.newsapp.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.data.model.Article

@Composable
fun NewsCard(
    article: Article,
    isBookmarked : Boolean = false
) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .border(3.dp, MaterialTheme.colorScheme.secondary, MaterialTheme.shapes.medium)
    ) {
        val image = article.urlToImage

        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = "News Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(200.dp),
            contentScale = ContentScale.Crop

        )
        Box( modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(5.dp)
            .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(15.dp))
            .background(color = Color.DarkGray)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {

                    Text(
                        text = article.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )


                IconButton(onClick = { }) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        contentDescription = "Bookmark"
                    )
                }
            }
        }
    }
}