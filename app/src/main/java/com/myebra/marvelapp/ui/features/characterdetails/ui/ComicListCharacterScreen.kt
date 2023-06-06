package com.myebra.marvelapp.ui.features.characterdetails.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.myebra.marvelapp.R
import com.myebra.marvelapp.domain.models.features.characters.Comic
import com.myebra.marvelapp.ui.common.ResourceState
import com.myebra.marvelapp.ui.features.characterdetails.viewmodels.CharacterDetailsViewModel

@Composable
fun ComicListCharacter(
    characterDetailsViewModel: CharacterDetailsViewModel
){

    var loadedComic : List<Comic>? = null
    val appContext = LocalContext.current
    val comicState by characterDetailsViewModel.comicState.collectAsStateWithLifecycle()

    when(comicState){

        is ResourceState.Error -> {
            Toast.makeText(appContext, "error", Toast.LENGTH_SHORT).show()
        }

        is ResourceState.Success -> {
            loadedComic = (comicState as ResourceState.Success<*>).data as List<Comic>

            if(loadedComic.isNotEmpty()){
                Box() {
                    Text(
                        text = stringResource(R.string.comics_character_title),
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .padding(top = 40.dp)
                    ) {
                        items(loadedComic) { item ->
                            ComicCharacterItem(item)
                        }
                    }
                }
            }
        }

        else->{}
    }
}

@Composable
fun ComicCharacterItem(item: Comic){

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(300.dp)
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = item.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)

                ) {
                    Icon(
                        Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = item.onSaleDate,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}