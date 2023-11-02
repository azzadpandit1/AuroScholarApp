package com.auro.application.core.models

data class UserResponse(
    val token: String,
    val user: User
)