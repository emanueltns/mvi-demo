package com.devforfun.mvidemo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devforfun.mvidemo.ui.theme.MVIDemoTheme

@Composable
fun EmojiCardHint(
    modifier: Modifier = Modifier,
    emoji: String = "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBC",
    title: String,
) {
    Row(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.fillMaxHeight().align(Alignment.Bottom),
            text = emoji,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.width(MVIDemoTheme.dimens.grid_2))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = MVIDemoTheme.colors.secondary,
            elevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(top = MVIDemoTheme.dimens.grid_1,
                    bottom = MVIDemoTheme.dimens.grid_1_5,
                    end = MVIDemoTheme.dimens.grid_1_5,
                    start = MVIDemoTheme.dimens.grid_1_5).fillMaxSize(),
               verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = MVIDemoTheme.colors.onPrimary,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

}
