@file:Suppress("UNUSED_PARAMETER")

package com.bangkit.teras_app.ui.screen.board

import android.content.pm.ActivityInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ViewModelFactory
import com.bangkit.teras_app.data.LockScreenOrientation
import com.bangkit.teras_app.data.RiceProductionRepository
import com.bangkit.teras_app.data.checkMinus
import com.bangkit.teras_app.data.response.PredictionData
import com.bangkit.teras_app.ui.common.UiState
import com.bangkit.teras_app.ui.components.CircularLoading
import com.bangkit.teras_app.ui.components.loadingAnimation

@Composable
fun BoardScreen(
    modifier : Modifier = Modifier,
    viewModel : BoardViewModel = viewModel(factory = ViewModelFactory(RiceProductionRepository()))){
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    loadingAnimation(true)
                }
                viewModel.getAllPrediction()
            }
            is UiState.Success -> {
                Leaderboard(uiState.data)
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun Leaderboard(riceProduction :  List<PredictionData>) {
    LazyColumn {
        items(riceProduction) { entry ->
            if(entry.rank > 0){
                BoardContent(entry)
            }
        }
    }
}

@Composable
fun BoardContent(
    rice: PredictionData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Column{
            val status : String
            val statusColor : Color
            if(checkMinus(rice.prediction)){
                status = "Defisit"
                statusColor = Color.Red
            }else{
                status = "Surplus"
                statusColor = colorResource(R.color.greenBtn)
            }

            Text(text = rice.province)
            Text(text = status, color = statusColor)
            Text(text = "Total Produksi : ${rice.prediction}")
        }
        Row{
            if(rice.rank <= 3) {
                Image(
                    painter =
                    painterResource(R.drawable.ic_rank),
                    contentDescription = "Ranking",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 5.dp)
                )
            }
            Text(text = "Peringkat ${rice.rank}")
        }
    }

}

