package com.vi.techshopmobile.presentation.filter.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.ui.theme.Blue
import com.vi.techshopmobile.ui.theme.Gray_900

@Composable
fun FilterCheckBox(
    index: Int,
    lableName: String,
    checkItemPos: List<Int>,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 8.dp)
            .selectable(
                selected = checkItemPos.contains(index),
                onClick = {
                    onClick()
                },
                role = Role.Checkbox,
            )
            , verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkItemPos.contains(index),
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Blue,
                uncheckedColor = Gray_900,
                checkmarkColor = Color.White,
                disabledCheckedColor = Gray_900,
                disabledIndeterminateColor = Gray_900,
                disabledUncheckedColor = Gray_900
            ),
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = lableName,
            color = if (index !in checkItemPos) Gray_900 else Blue
        )
    }
}

