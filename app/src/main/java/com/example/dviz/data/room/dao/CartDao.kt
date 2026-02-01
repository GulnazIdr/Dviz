package com.example.dviz.data.room.dao

import androidx.room.Dao

@Dao
interface CartDao {

    //cart
//    @Upsert
//    suspend fun addToCart(cartSneakers: LocalCartEntity)
//
//    @Query("update cart_table set amount = :amount " +
//            "where userId = :userId and sneakerId = :sneakerId"
//    )
//    suspend fun changeCartAmount(userId: String, sneakerId: Int, amount: Int)
//
//    @Query("delete from cart_table where userId = :userId and sneakerId = :sneakerId")
//    suspend fun cleartCart(sneakerId: Int, userId: String)
//
//
//    @Query("SELECT * FROM cart_table WHERE userId = :userId")
//    suspend fun getCartSneaker(userId: String): List<LocalCartEntity>
//
//    @Query("update cart_table set userId = :newUserId where userId = :tempUserId")
//    suspend fun updateCartsUserId(tempUserId: String, newUserId: String)

    //search
}