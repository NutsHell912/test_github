package com.test.nutshell.data.model

import com.test.nutshell.data.network.RepoDto

data class Repo(
        val fullName: String?,
        val description: String?,
        val url: String?
) {
    constructor(repoDto: RepoDto) : this(fullName = repoDto.fullName,
            description = repoDto.description,
            url = repoDto.owner?.avatarUrl)
}
