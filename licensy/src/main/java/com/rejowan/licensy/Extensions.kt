package com.rejowan.licensy

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

/**
 * Opens the given URL in an external browser.
 */
internal fun Context.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    startActivity(intent)
}
