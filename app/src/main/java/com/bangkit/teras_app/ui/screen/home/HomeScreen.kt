@file:OptIn(ExperimentalPagerApi::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class)

package com.bangkit.teras_app.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.teras_app.R
import com.bangkit.teras_app.ui.theme.plusjakartasansFontFamily
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column {
            GreetingSection()
            AutoSlidingImage()
            TopThree()
            SurplusDart()

        }
    }
}


@Composable
fun GreetingSection(
    name: String = "George"
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment =  Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hallo!",
                style = TextStyle(
                    fontFamily = plusjakartasansFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color(0xFFA0A0A0)
                ),
                modifier = Modifier
                    .padding(start = 21.dp, top = 21.dp)
            )
            Text(
                text = "$name",
                style = TextStyle(
                    fontFamily = plusjakartasansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                ),
                modifier = Modifier
                    .padding(start = 21.dp, top = 5.dp)
            )
            Box(
                modifier = Modifier
                    .padding(start = 21.dp, top = 25.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = "Malang, JawaTimur, Indonesia",
                    style = TextStyle(
                        fontFamily = plusjakartasansFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color(0xFF5F85E5)
                    ),
                    modifier = Modifier.padding(start = 31.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(end = 29.dp)
                .shadow(25.dp, shape = CircleShape)
        ){
            Image(
                painter = painterResource(id = R.drawable.photoprofile),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )

}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots : Int,
    selectedIndex: Int,
    selectedColor: Color = Color(0xFF5F85E5),
    unSelectedColor: Color = Color(0xFFCEE4FE),
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                size = dotSize,
                color = if (index == selectedIndex) selectedColor else unSelectedColor
            )

            if (index != totalDots - 1) {
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 5000L,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) ->  Unit,

    ) {
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HorizontalPager(
            count = itemsCount,
            state = pagerState
        ) {page ->
            itemContent(page)
        }


        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Transparent
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage,
                dotSize = 8.dp
            )
        }
    }
}

@Composable
fun AutoSlidingImage() {
    val drawableIds = listOf(
        R.drawable.slider1,
        R.drawable.slider2,
        R.drawable.slider3,
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(340.dp)
                .height(210.dp),
            shape = RoundedCornerShape(32.dp),
        ) {
            AutoSlidingCarousel(
                itemsCount = drawableIds.size
            ) {index ->
                Image(
                    painter = painterResource(id = drawableIds[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(210.dp)
                        .width(340.dp)
                )
            }
        }
    }
}



@Composable
fun TopThree() {
    val drawableIds = listOf(
        R.drawable.medal1,
        R.drawable.medal2,
        R.drawable.medal3
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Text(
            text = "Top Three",
            style = TextStyle(
                fontFamily = plusjakartasansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            ),
            modifier = Modifier
                .padding(start = 31.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(45.dp),
            modifier = Modifier
                .padding(start = 42.dp, top = 40.dp)
        ) {
            items(3) { index ->
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {

                        }
                        .background(color = Color(0xFFCEE4FE))
                ) {
                    Image(
                        painter = painterResource(id = drawableIds[index]),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SurplusDart() {
    val drawableIds = listOf(
        R.drawable.beraskantong,
        R.drawable.beraskantong,
        R.drawable.beraskantong
    )

    LazyColumn {
        items(3) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 42.dp, top = 32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(330.dp)
                        .height(116.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color(0xFFF0F0F0))
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(116.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                    ) {
                        Image(
                            painter = painterResource(id = drawableIds[index]),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 110.dp, top = 15.dp, end = 8.dp, bottom = 20.dp)
                    ) {
                        Text(
                            text = "Jawa Timur",
                            style = TextStyle(
                                fontFamily = plusjakartasansFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "Beras",
                            style = TextStyle(
                                fontFamily = plusjakartasansFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}