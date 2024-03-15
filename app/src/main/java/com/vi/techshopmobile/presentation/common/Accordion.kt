package com.vi.techshopmobile.presentation.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vi.techshopmobile.R
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun Accordion(
    heading: String,
    items: List<String>
) {
    var isExpanded by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier.animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = heading, style = MaterialTheme.typography.displaySmall)
            Icon(
                painter = painterResource(id = if (!isExpanded) R.drawable.ic_down_arrow else R.drawable.ic_up_arrow),
                contentDescription = null
            )
        }
        if (isExpanded) {
            Spacer(modifier = Modifier.height(12.dp))
            BulletList(
                items = items,
                style = MaterialTheme.typography.bodySmall,
                lineSpacing = 8.dp,
            )
        }
        Divider()
    }
}

@Preview
@Composable
private fun PreviewAccordion() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ) {
            Accordion(
                items = listOf("item 1", "item 2"),
                heading = "test"
            )
        }
    }
}