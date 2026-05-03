package com.example.cardsmvvmcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cardsmvvmcompose.view.MainViewModel
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.example.cardsmvvmcompose.data.TmCard

@Composable
fun MainLayout(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val cards = viewModel.cardState.collectAsStateWithLifecycle().value.cards

    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TitleText()

        LazyColumn(modifier=Modifier.fillMaxSize()) {
            itemsIndexed(cards) { index, card ->
                if(card.isText()) {
                    TextCard(text= card.card.value!!,
                        textColor= card.card.attributes!!.text_color,
                        fontSize= card.card.attributes!!.font.size)
                } else {
                    FullCard(card)
                }

                if(index < cards.size - 1) {
                    HorizontalDivider(modifier = Modifier.height(10.dp).background(color= Color.LightGray),
                        thickness= 0.dp, color= Color.LightGray
                    )
                }
            }
        }
    }
}

@Composable
fun FullCard(card: TmCard) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth().background(color= Color.White)
        .padding(10.dp)) {
        val (image, title, description) = createRefs()

        Box(modifier = Modifier.fillMaxWidth().constrainAs(image) {
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
        }) {
            if (card.isImage()) {
                ImageCtrl(imageUrl= card.card.image!!.url)
            }
        }
        Box(modifier = Modifier.fillMaxWidth().constrainAs(description) {
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
        }) {
            if(card.isDescription()) {
                TextCtrl(
                    text= card.card.description!!.value!!,
                    fontSize= card.card.description!!.attributes!!.font.size,
                    textColor= card.card.description!!.attributes!!.text_color
                )
            }
        }
        Box(modifier = Modifier.fillMaxWidth().constrainAs(title) {
            start.linkTo(parent.start)
            bottom.linkTo(description.top)
        }) {
            if(card.isTitle()) {
                TextCtrl(
                    text= card.card.title!!.value!!,
                    fontSize= card.card.title!!.attributes!!.font.size,
                    textColor= card.card.title!!.attributes!!.text_color
                    )
            }
        }
    }
}

@Composable
fun ImageCtrl(imageUrl: String) {
    AsyncImage(model= imageUrl, contentDescription= null, contentScale = ContentScale.FillWidth,
        modifier= Modifier.fillMaxWidth())
}

@Composable
fun TextCard(text: String, textColor: String, fontSize: Int) {
    Box(modifier= Modifier.fillMaxWidth().background(color= Color.White).padding(10.dp)) {
        TextCtrl(text=text, textColor=textColor, fontSize=fontSize)
    }
}

@Composable
fun TextCtrl(text: String, fontSize: Int, textColor: String?= null) {
    val color = if(textColor != null) Color(textColor.toColorInt()) else Color.Unspecified
    Text(modifier = Modifier.padding(10.dp), text=text,
        fontSize = fontSize.sp, color = color)
}

@Composable
fun TitleText() {
    Box(modifier= Modifier.padding(10.dp)) {
        TextCtrl(text= "Multi Card List", fontSize= 20)
    }
}