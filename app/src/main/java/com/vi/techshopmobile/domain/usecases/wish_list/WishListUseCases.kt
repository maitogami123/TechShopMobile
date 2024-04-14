package com.vi.techshopmobile.domain.usecases.wish_list

data class WishListUseCases (
    val upsertWishItem: UpsertWishItem,
    val deleteWishItem: DeleteWishItem,
    val getWishList: GetWishList,
    val selectWishItem: SelectWishItem,
    val deleteWishItemByProductLine: DeleteWishItemByProductLine,
    val updateWishItem: UpdateWishItem
)