package ru.jonik.androidlearn

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Options(
    val boxCount: Int,
    val isTimerEnabled: Boolean
) : Parcelable {
    companion object {
        @JvmStatic
        val DEFAULT = Options(boxCount = 1, isTimerEnabled = false)
    }
}