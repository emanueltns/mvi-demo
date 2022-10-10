package com.devforfun.mvidemo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.devforfun.mvidemo.ui.theme.MVIDemoTheme

@Composable
fun BottomAppBarView(
    modifier: Modifier = Modifier,
    onNextScreen: () -> Unit,
    onBackScreen: () -> Unit,
    imageVectorBack: ImageVector? = null,
    imageVectorNext: ImageVector? = null,
    backgroundColor: Color = MVIDemoTheme.colors.secondary,
    buttonsTintColor: Color = MVIDemoTheme.colors.onPrimary
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        cutoutShape = RoundedCornerShape(80),
        backgroundColor = backgroundColor,
        content = {
            Box(modifier = modifier.fillMaxWidth()) {
                imageVectorBack?.let {
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterStart),
                        onClick = onBackScreen
                    ) {
                        Icon(
                            imageVector = imageVectorBack,
                            contentDescription = "",
                            tint = buttonsTintColor
                        )
                    }
                }

                imageVectorNext?.let {
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = onNextScreen
                    ) {
                        Icon(
                            imageVector = imageVectorNext,
                            contentDescription = "",
                            tint = buttonsTintColor
                        )
                    }
                }
            }
        }
    )
}
