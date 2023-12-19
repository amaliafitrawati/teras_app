package com.bangkit.teras_app.ui.screen.story


import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ViewModelFactory
import com.bangkit.teras_app.data.LockScreenOrientation
import com.bangkit.teras_app.data.response.ForumData
import com.bangkit.teras_app.di.Injection
import com.bangkit.teras_app.ui.common.UiState

@Composable
fun BrowseStoryScreen(
    modifier : Modifier = Modifier,
    viewModel : StoryViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository(LocalContext.current)))){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllStory()
            }
            is UiState.Success -> {
               StoryContent(
                   listStory = uiState.data
               )
            }
            is UiState.Error -> {}
        }
    }
}



@Composable
fun StoryData(
    id: Int,
    title: String,
    content: String,
    photo: String? = null,
    modifier: Modifier = Modifier
) {

    val truncatedContent =
        remember{
            if (content.length > 90) "${content.take(90)}..." else content }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.img_dummy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(105.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 5.dp)
                .fillMaxWidth()){
            Text(text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 2.dp))
            Text(
                text = truncatedContent,
                style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier =
                    Modifier
                        .size(23.dp)
                        .padding(end = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        tint = Color.Red,
                        contentDescription = stringResource(R.string.like))
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    Modifier
                        .size(23.dp)
                        .padding(end = 5.dp)) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.detail))
                }
            }
        }
    }
}


@Composable
fun StoryContent(
    listStory : List<ForumData>,
    modifier: Modifier = Modifier
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(10.dp))){
    Box(modifier = modifier) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState){

            items(listStory, key = { it.id }) { story ->
                StoryData(
                    id = story.id,
                    title = story.title,
                    content = story.content,
                    photo = story?.image,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

