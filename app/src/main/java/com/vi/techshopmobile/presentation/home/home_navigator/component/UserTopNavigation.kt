package com.vi.techshopmobile.presentation.home.home_navigator.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vi.techshopmobile.presentation.Dimens.SmallPadding
import com.vi.techshopmobile.ui.theme.TechShopMobileTheme

/**
 * User top-bar for user of TechShop Mobile application.
 *
 * Contain user's full name, avatar and email address
 *
 * @param userInfo see [userInfo]
 */
@Composable
fun UserTopNavigation(
    userInfo: UserInformation
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.padding(horizontal = SmallPadding, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(userInfo.avatarUrl)
                .build(), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(SmallPadding))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = userInfo.fullName, style = MaterialTheme.typography.headlineMedium)
            Text(text = userInfo.email, style = MaterialTheme.typography.labelMedium)
        }
    }
}
/**
 *
 * A data class contains essential information related to current logged user.
 *
 * @param fullName current logged in user's full name
 * @param email current logged in user's email
 * @param avatarUrl current logged in user's avatar url
 */
data class UserInformation(
    val fullName: String = "",
    val email: String = "",
    val avatarUrl: String = ""
)

@Preview
@Composable
fun PreviewUserTopNavigation() {
    TechShopMobileTheme {
        UserTopNavigation(
            userInfo = UserInformation(
                avatarUrl = "https://scontent.fsgn8-4.fna.fbcdn.net/v/t39.30808-6/305108279_3380406918948385_1634056947920206356_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=B1SCMNpOpfcAX_X4V9z&_nc_ht=scontent.fsgn8-4.fna&oh=00_AfBYroABAiS3Fc1XtbPPub0XZOJnR5wiCledwBNV_qO4yQ&oe=65EC0971",
                fullName = "Lê Thái Vi",
                email = "lethaivi.work@gmail.com"
            )
        )
    }
}