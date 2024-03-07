package com.vi.techshopmobile.presentation.home_navigator.component

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

@Composable
fun TechShopBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
                .height(64.dp)
                .background(MaterialTheme.colorScheme.primary)
                .align(Alignment.BottomCenter),
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationBarItem(
                    icon = item.icon,
                    isSelected = selectedItem == index,
                    onClick = {
                        onItemClick(index)
                    })
            }
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewBottomNavigation() {
    var selectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }

    TechShopMobileTheme {
        Box(
            modifier = Modifier
                .background(Color(0xFFFFFFFF))
                .fillMaxSize(), contentAlignment = Alignment.BottomCenter
        ) {
            TechShopBottomNavigation(items = listOf(
                BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
                BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
                BottomNavigationItem(icon = R.drawable.ic_user, text = "User"),
            ), selectedItem = selectedItem
            ) { index ->
                selectedItem = index
            }
        }
    }
}