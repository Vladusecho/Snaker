package ru.mygames.snaker.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.snaker.ui.theme.SnakerTheme


@Composable
fun JetSwitch(
    items: List<String>,
    selectedItemId: Int,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(32.dp),
    onItemChange: (Int) -> Unit
) {

    val selectedItem = items.getOrNull(selectedItemId) ?: ""

    val isActivePreviousButton = selectedItemId > 0
    val isActiveNextButton = selectedItemId < items.lastIndex

    val previousButtonContainer = if (isActivePreviousButton) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val nextButtonContainer = if (isActiveNextButton) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.tertiaryContainer
    }

    val previousButtonContent = if (isActivePreviousButton) {
        MaterialTheme.colorScheme.onTertiaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }
    val nextButtonContent = if (isActiveNextButton) {
        MaterialTheme.colorScheme.onTertiaryContainer
    } else {
        MaterialTheme.colorScheme.onSecondaryContainer
    }

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape)
                .fillMaxWidth()
                .height(48.dp)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primaryContainer.copy(0.25f),
                    shape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = selectedItem.uppercase(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Box(
            modifier = Modifier
                .background(
                    previousButtonContainer,
                    shape.copy(topEnd = CornerSize(8.dp), bottomEnd = CornerSize(8.dp))
                )
                .size(48.dp)
                .clip(
                    shape.copy(topEnd = CornerSize(8.dp), bottomEnd = CornerSize(8.dp))
                )
                .then(if (isActivePreviousButton)
                    Modifier.clickable { onItemChange(selectedItemId - 1) }
                else Modifier
                )
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_left_16_filled,
                ),
                tint = previousButtonContent,
                contentDescription = "Left arrow",
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier
                .background(
                    nextButtonContainer,
                    shape.copy(topStart = CornerSize(8.dp), bottomStart = CornerSize(8.dp))
                )
                .size(48.dp)
                .clip(
                    shape.copy(topStart = CornerSize(8.dp), bottomStart = CornerSize(8.dp))
                )
                .then(if (isActiveNextButton)
                    Modifier.clickable { onItemChange(selectedItemId + 1) }
                else Modifier
                )
                .align(Alignment.CenterEnd),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_chevron_right_16_filled,
                ),
                tint = nextButtonContent,
                contentDescription = "Right arrow",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
@Preview
private fun ShowPreview() {
    SnakerTheme {
        JetSwitch(
            items = listOf("Легко", "Нормально", "Сложно"),
            selectedItemId = 1,
            modifier = Modifier.fillMaxWidth()
        ) {
        }
    }
}