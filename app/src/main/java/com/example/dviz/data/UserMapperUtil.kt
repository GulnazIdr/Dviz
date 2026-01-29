package com.example.dviz.data

import com.example.dviz.domain.User

object UserMapperUtil  {
    fun RemoteUserEntity.toUser(): User {
        return User(
            id =  id,
            name = name,
            phone = phone,
            password = password,
            email = email
        )
    }
}