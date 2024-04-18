package com.vi.techshopmobile.presentation.onboarding

import androidx.annotation.DrawableRes
import com.vi.techshopmobile.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Chào mừng đến với TechShop",
        description = "Trải nghiệm mua hàng thông minh và tiện lợi.",
        image = R.drawable.start_screen_1
    ),
    Page(
        title = "Hiện đại và xu hướng",
        description = "Đem đến sự mới mẻ của công nghệ. Hiện đại không chỉ là việc theo kịp thời đại mà còn là việc dẫn dắt thời đại",
        image = R.drawable.start_screen_2
    ),
    Page(
        title = "Nhanh chóng và tận tình",
        description = "Nhận được sự hỗ trợ. Tận tình không chỉ là làm việc hết mình mà là việc hoàn thành một cách hiệu quả và kịp thời.",
        image = R.drawable.start_screen_3
    )
)