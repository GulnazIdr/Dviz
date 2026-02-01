package com.example.dviz.data.room

import android.util.Log
import com.example.dviz.data.room.dao.EventDao
import com.example.dviz.data.room.dao.UserDao
import com.example.dviz.domain.DataStoreRepository
import com.example.dviz.domain.FetchResult
import com.example.dviz.domain.event.EventRepository
import com.example.dviz.domain.models.Category
import com.example.dviz.domain.models.Place
import com.example.dviz.domain.models.Places
import javax.inject.Inject

class LocalEventRepository @Inject constructor(
    private val eventDao: EventDao,
   // private val localOrderDao: LocalOrderDao,
    private val userDao: UserDao,
    private val datastoreRepository: DataStoreRepository
): EventRepository {
    override suspend fun fetchEventList(
        eventPage: Int,
        placePage: Int,
        moviePage: Int
    ): FetchResult<List<Places>> {
        TODO("Not yet implemented")
      //  return FetchResult.Success(eventDao.get)
    }

    override suspend fun fetchCategoryList(): FetchResult<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlaceById(id: Int): FetchResult<Place> {
        TODO("Not yet implemented")
    }
    //    suspend fun addToCart(sneakerId: Int): FetchResult<Int> {
//        localSneakerDao.addToCart(
//            LocalCartEntity(
//                userId = datastoreRepository.getCurrentUserId(),
//                sneakerId = sneakerId,
//                amount = 1
//            )
//        )
//        return FetchResult.Success(sneakerId)
//    }
//
//    suspend fun deleteFromCart(sneakerId: Int): FetchResult<Int> {
//        Log.d("DELETED ITEM", sneakerId.toString())
//        localSneakerDao.cleartCart(sneakerId = sneakerId, datastoreRepository.getCurrentUserId())
//        changeAmount(sneakerId, 0)
//        return FetchResult.Success(sneakerId)
//    }
//
//    suspend fun changeAmount(sneakerId: Int, amount: Int): FetchResult<Int> {
//        localSneakerDao.changeCartAmount(
//            sneakerId = sneakerId,
//            amount = amount,
//            userId =  datastoreRepository.getCurrentUserId()
//        )
//        return FetchResult.Success(sneakerId)
//    }
//
//    suspend fun fetchOrderContent(): FetchResult<List<OrderWithProductInfo>> {
//        val userId = datastoreRepository.getCurrentUserId()
//
//        if (localUserDao.getUser(userId)?.isIdTemp != true) {
//            val orders = localOrderDao.getOrders(userId)
//
//            if (orders.isNotEmpty()) {
//                val sneakers = localSneakerDao.getSneakers().filter {
//                    orders.associateBy { it.sneakerId }.contains(it.id)
//                }.associateBy { it.id }
//
//                val fetched = orders.map {
//                    OrderWithProductInfo(
//                        order = it.toOrder(),
//                        product = sneakers[it.sneakerId]?.toOrderSneaker()
//                    )
//                }
//
//                return FetchResult.Success(fetched)
//            } else
//                return FetchResult.Success(emptyList())
//        }else {
//            return FetchResult.Unauthorized()
//        }
//    }
//
//    suspend fun addToOrderList(order: OrderInfo): FetchResult<Int> {
//        return FetchResult.Unauthorized()
//    }
//
//    suspend fun getOrderById(id: Int?): FetchResult<OrderWithProductInfo> {
//        return FetchResult.Unauthorized()
//    }
}