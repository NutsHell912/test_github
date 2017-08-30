package com.test.nutshell.data.network

import com.google.gson.annotations.SerializedName

data class RepoDtoFull(
        @SerializedName("incomplete_results")
        val results : Boolean,
        @SerializedName("items")
        val items : List<RepoDto>
)

data class RepoDto(
        @SerializedName("full_name")
        val fullName: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("owner")
        val owner: UserDto?
)

data class UserDto(
        @SerializedName("avatar_url")
        val avatarUrl: String?
)