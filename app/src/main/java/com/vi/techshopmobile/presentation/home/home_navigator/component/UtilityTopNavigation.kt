package com.vi.techshopmobile.presentation.home.home_navigator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vi.techshopmobile.R
import com.vi.techshopmobile.presentation.Dimens.IconSizeMedium
import com.vi.techshopmobile.presentation.common.SearchBar
import com.vi.techshopmobile.ui.theme.Gray_900
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme
import javax.annotation.Nullable

/**
 * A custom top-bar contains app utilities.
 *
 * In case you only want a SearchBar check this composable Instead [SearchBar]
 *
 * @param rightBtnIcon the icon to be displayed. If no value passed, it will not display anything
 * @param onRightBtnClick the function to handle right icon click event
 * @param leftBtnIcon the action icon to be displayed. This one is required
 * @param onLeftBtnClick the function to handle left action icon click event
 * @param title if this one not null, it will display title text instead of a search box
 * @param onSearch the function to handle search action, however you will need to pass this function even if you don't use the search bar for a work around you can just pass a blank function.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UtilityTopNavigation(
    rightBtnIcon: Int? = null,
    @Nullable onRightBtnClick: () -> Unit?,
    leftBtnIcon: Int? = null,
    @Nullable onLeftBtnClick: () -> Unit?,
    title: String? = null,
    titleSearch: String? = null,
    @Nullable onSearch: () -> Unit?,
) {
    TopAppBar(
        title = {
            if (!title.isNullOrEmpty())
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, if (rightBtnIcon == null) 48.dp else 0.dp, 0.dp)
                )
            else
                titleSearch?.let {
                    SearchBar(
                        text = it,
                        readOnly = false,
                        onValueChange = {},
                        onSearch = { onSearch() }
                    )
                }
        },

        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(
                elevation = 8.dp,
                ambientColor = Gray_900,
                spotColor = Color.Transparent
            ),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            if (leftBtnIcon != null)
                IconButton(onClick = { onLeftBtnClick() }) {
                    Icon(
                        painter = painterResource(id = leftBtnIcon),
                        modifier = Modifier.size(IconSizeMedium),
                        contentDescription = null
                    )
                }
        },
        actions = {
            if (rightBtnIcon != null)
                IconButton(onClick = { onRightBtnClick() }) {
                    Icon(
                        painter = painterResource(id = rightBtnIcon),
                        modifier = Modifier.size(IconSizeMedium),
                        contentDescription = null
                    )
                }
        }
    )
}

/**
 * A custom top-bar contains app utilities.
 *
 * This top bar background is invisible.
 *
 * @param rightBtnIcon the icon to be displayed. If no value passed, it will not display anything
 * @param onRightBtnClick the function to handle right icon click event
 * @param leftBtnIcon the action icon to be displayed. This one is required
 * @param onLeftBtnClick the function to handle left action icon click event
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UtilityTopNavigation(
    rightBtnIcon: Int? = null,
    @Nullable onRightBtnClick: () -> Unit?,
    leftBtnIcon: Int? = null,
    @Nullable onLeftBtnClick: () -> Unit?
) {
    TopAppBar(
        title = {},
        modifier = Modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            if (leftBtnIcon != null)
                IconButton(
                    onClick = { onLeftBtnClick() },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = leftBtnIcon),
                        modifier = Modifier.size(IconSizeMedium),
                        contentDescription = null
                    )
                }
        },
        actions = {
            if (rightBtnIcon != null)
                IconButton(
                    onClick = { onRightBtnClick() },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = rightBtnIcon),
                        modifier = Modifier.size(IconSizeMedium),
                        contentDescription = null
                    )
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUtilityTopNavigation() {
    TechShopMobileTheme {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize(), verticalArrangement = Arrangement.Top
        ) {
            SearchBar(
                text = "",
                readOnly = false,
                onValueChange = {},
                modifier = Modifier.padding(vertical = 11.dp, horizontal = 16.dp)
            ) {}
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_left_arrow,
                rightBtnIcon = R.drawable.ic_filter,
                titleSearch = "Laptop",
                onSearch = {})
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { /*TODO*/ },
                leftBtnIcon = R.drawable.ic_cross,
                title = "Title",
                onSearch = {})
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
            UtilityTopNavigation(
                onRightBtnClick = { /*TODO*/ },
                onLeftBtnClick = { /*TODO*/ },
                rightBtnIcon = R.drawable.ic_filter,
                leftBtnIcon = R.drawable.ic_cross,
                title = "Title",
                onSearch = {})
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
//            UtilityTopNavigation(
//                onRightBtnClick = { /*TODO*/ },
//                onLeftBtnClick = { /*TODO*/ },
//                rightBtnIcon = R.drawable.ic_filter,
//                leftBtnIcon = R.drawable.ic_left_arrow
//            )
        }
    }
}