package com.medimates.extensions

import android.app.Activity
import android.text.format.DateUtils
import com.medimates.R
import java.text.SimpleDateFormat

fun Long.formatDate(activity: Activity): String {

    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")

    val time = this * 1000L

    return when {
        (DateUtils.isToday(time)) -> activity.getString(R.string.today)
        time.isYesterday() -> activity.getString(R.string.yesterday)
        else -> simpleDateFormat.format(time)
    }
}

fun Long.isYesterday(): Boolean {
    return DateUtils.isToday(this + DateUtils.DAY_IN_MILLIS)
}
