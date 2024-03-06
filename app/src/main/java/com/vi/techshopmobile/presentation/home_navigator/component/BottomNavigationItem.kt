package com.vi.techshopmobile.presentation.home_navigator.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun BottomNavigationBarItem(
    modifier: Modifier = Modifier,
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(modifier = modifier
        .size(if (isSelected) 78.dp else 64.dp)
        .animateContentSize()
        .clip(CircleShape)
        .clickable(interactionSource = interactionSource, indication = null) {
            onClick()
        }, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(if (isSelected) 8.dp else 0.dp)
                .fillMaxSize()
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .background(if (isSelected) Color(0x40FFFFFF) else Color.Transparent)
                    .padding(12.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomNavigationItem() {
    TechShopMobileTheme {
        Row(modifier = Modifier.background(Color.Transparent)) {
            BottomNavigationBarItem(icon = R.drawable.ic_home, isSelected = true, onClick = {})
        }
    }
}