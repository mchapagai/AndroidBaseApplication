package com.example.mchapagai.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ContributorResponse(
    val contributors: List<Contributors>
) : Parcelable


@Parcelize
class Contributors(
    val name: String,
    val github_url: String,
    val bio: String,
    val avatar: String,
    val email: String,
) : Parcelable
