package com.example.dviz.data.user

import com.example.dviz.data.room.entities.UserEntity
import com.example.dviz.domain.models.User
import kotlin.toString


fun RemoteUserEntity.toUser(): User {
    return User(
        id =  id,
        name = name,
        phone = phone,
        password = password,
        email = email
    )
}

fun UserEntity.toUser(): User{
    return User(
        id =  uid,
        name = name,
        phone = phone,
        password = password,
        email = email
    )
}

fun User.toLocalUser(): UserEntity{
    return UserEntity(
        uid =  id.toString(),
        name = name,
        phone = phone,
        password = password,
        email = email
    )
}
