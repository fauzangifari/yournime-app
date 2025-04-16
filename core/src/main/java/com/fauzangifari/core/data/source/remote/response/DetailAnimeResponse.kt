package com.fauzangifari.core.data.source.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailAnimeResponse(

	@field:SerializedName("data")
	val data: DataItem? = null
) : Parcelable


