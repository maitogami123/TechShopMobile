package com.vi.techshopmobile.presentation.filter.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.ui.theme.Blue
import com.vi.techshopmobile.ui.theme.Gray_900

@Composable
fun FilterRadioGroup(
    index: Int,
    lableName: String,
    selectedIndex: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 8.dp)
            .selectable(
                selected = selectedIndex == index,
                onClick = {
                    onClick()
                },
                role = Role.RadioButton,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedIndex == index,
            onClick = { },
            colors = RadioButtonDefaults.colors(
                selectedColor = Blue,
                unselectedColor = Gray_900,
                disabledUnselectedColor = Gray_900,
                disabledSelectedColor = Gray_900
            ),
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = lableName,
            style = MaterialTheme.typography.bodyMedium,
            color = if (index != selectedIndex) Gray_900 else Blue
        )


    }
}