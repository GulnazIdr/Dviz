package com.example.ui_interface.models

data class OrderUi(
    val userUi: UserUi,
    val address: String
)
//
//abstract class EventOrderUi{
//    private var _ticketAmount = 0
//    private var _price: Int? = null
//
//    var ticketAmount: Int
//        get() = _ticketAmount
//        set(value) {_ticketAmount = value}
//
//    var price: Int?
//        get() = _price
//        set(value) {
//            value?.let {
//                if (it >= 0)
//                    _price = value
//            }
//        }
//}
//
//
//data object OrderWithoutPlaceUi: EventOrderUi()