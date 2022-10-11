package com.devforfun.mvidemo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devforfun.mvidemo.ui.theme.MVIDemoTheme


private val BUTTON_HEIGHT_LARGE = 48.dp
private val BUTTON_HEIGHT_SMALL = 24.dp

/**
 * Standard button for primary or secondary buttons of all sizes.
 * @param buttonType used to set the button type
 */
@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.PRIMARY,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    // In case some properties on buttons need to be changed, they can be hoisted up as a parameter
    // with a reasonable default for most cases.

    Button(
        onClick = { onClick() },
        modifier = modifier
            .height(BUTTON_HEIGHT_LARGE)
            .defaultMinSize(minHeight = MVIDemoTheme.dimens.minimumTappableArea),
        enabled = enabled,
        colors = buttonColors(buttonType),
        border = buttonBorderStroke(buttonType),
        contentPadding = contentPadding,
        shape = MaterialTheme.shapes.medium
    ) {
        ButtonText(text)
    }
}

@Composable
fun SmallTextButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.SECONDARY,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = MVIDemoTheme.dimens.grid_1,
        vertical = MVIDemoTheme.dimens.grid_0_5,
    ),
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(BUTTON_HEIGHT_SMALL),
        enabled = enabled,
        colors = buttonColors(buttonType),
        border = buttonBorderStroke(buttonType),
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            style = MaterialTheme.typography.button,
        )
    }
}

@Composable
private fun buttonBorderStroke(buttonType: ButtonType) =
    if (buttonType == ButtonType.SECONDARY) {
        BorderStroke(MVIDemoTheme.dimens.grid_0_25, SolidColor(MVIDemoTheme.colors.secondary))
    } else if (buttonType == ButtonType.DESTRUCTIVE) {
        BorderStroke(MVIDemoTheme.dimens.grid_0_25, SolidColor(MVIDemoTheme.colors.highlightDark))
    } else {
        null
    }

@Composable
private fun buttonColors(buttonType: ButtonType) =
    when (buttonType) {
        ButtonType.PRIMARY -> ButtonDefaults.buttonColors(
            backgroundColor = MVIDemoTheme.colors.primary,
            contentColor = MVIDemoTheme.colors.onPrimary
        )
        ButtonType.SECONDARY -> ButtonDefaults.buttonColors(
            backgroundColor = MVIDemoTheme.colors.secondary,
            contentColor = MVIDemoTheme.colors.onPrimary
        )
        ButtonType.VARIANT -> {
            ButtonDefaults.buttonColors(
                backgroundColor = MVIDemoTheme.colors.secondaryVariant,
                contentColor = MVIDemoTheme.colors.secondary
            )
        }
        ButtonType.DESTRUCTIVE -> {
            ButtonDefaults.buttonColors(
                backgroundColor = MVIDemoTheme.colors.surface,
                contentColor = MVIDemoTheme.colors.highlightDark
            )
        }
    }

@Composable
internal fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.button,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
    )
}
enum class ButtonType {
    PRIMARY, SECONDARY, VARIANT, DESTRUCTIVE
}

