package com.muratdayan.common.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

sealed class UiText {
    data class RemoteString(val message: String) : UiText()

    class LocaleString(@StringRes val res: Int, vararg val args: Any) : UiText()

    data object Idle : UiText()

    fun getString(context: Context): String {
        return when (this) {
            is RemoteString -> message
            is LocaleString -> context.getString(res, *args)
            Idle -> ""

        }
    }

    /*@Composable
    fun getString(context: Context): String {
        return when (this) {
            is RemoteString -> message
            is LocaleString -> context.getString(res, *args)
            Idle -> ""

        }
    }*/
}