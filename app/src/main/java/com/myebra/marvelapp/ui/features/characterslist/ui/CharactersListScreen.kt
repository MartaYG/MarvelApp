package com.myebra.marvelapp.ui.features.characterslist.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.myebra.marvelapp.domain.models.features.characters.Character
import com.myebra.marvelapp.ui.features.characterslist.viewmodels.CharactersListViewModel
import com.myebra.marvelapp.ui.common.ResourceState

@Composable
fun CharactersListScreen(charactersListViewModel: CharactersListViewModel, onItemClick: (characterId:Long)->Unit){

    val appContext = LocalContext.current
    val loadingState by charactersListViewModel.loadingState.collectAsState()
    val charactersData = charactersListViewModel.charactersPaging().collectAsLazyPagingItems()

    when(loadingState){

        is ResourceState.Loading ->
            if((loadingState as ResourceState.Loading<*>).load == true) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }

        is ResourceState.Error ->
            Toast.makeText(appContext, "error", Toast.LENGTH_SHORT).show()

        is ResourceState.Success ->
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(count = charactersData.itemCount) { index ->
                    charactersData[index]?.let { item ->
                        CharacterItem(item){
                            onItemClick(item.characterId)
                        }
                    }
                }
            }

        else -> {}
    }
}

@Composable
fun CharacterItem(item: Character, onItemClick: (characterId: Long) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(250.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable { onItemClick(item.characterId) }
    ) {
        AsyncImage(
            model = item.thumbnail,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                ) {
                    Icon(
                        Icons.Filled.Book,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = item.comicCount.toString(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}
