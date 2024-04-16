package com.vi.techshopmobile.presentation.filter.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.presentation.filter.FilterLabel
import com.vi.techshopmobile.ui.theme.Blue
import com.vi.techshopmobile.ui.theme.Gray_900

@Composable
fun FilterRadioGroup(heading: String, optionList: List<FilterLabel>){
    val disableItemPos = listOf<Int>()
    var selectedIndex by remember {
        mutableIntStateOf(-1)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .selectableGroup()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = heading, style = MaterialTheme.typography.displayMedium)
        optionList.forEachIndexed { index, option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        role = Role.RadioButton,
                        enabled = index !in disableItemPos
                    )
                    .padding(start = 16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedIndex == index,
                    onClick = {selectedIndex = index },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Blue,
                        unselectedColor = Gray_900,
                        disabledUnselectedColor = Gray_900,
                        disabledSelectedColor = Gray_900
                    ),
                    enabled = index !in disableItemPos
                )
                Text(
                    text = option.label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (index !in disableItemPos) Gray_900 else Blue
                )
            }
        }
    }
}