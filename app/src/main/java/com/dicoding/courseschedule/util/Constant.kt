package com.dicoding.courseschedule.util

import java.util.concurrent.Executors

const val NOTIFICATION_CHANNEL_NAME = "Course Channel"
const val NOTIFICATION_CHANNEL_ID = "notify-schedule"
const val NOTIFICATION_ID = 32
const val ID_REPEATING = 101
const val PERMISSION_REQUEST_CODE = 111

const val START_PICKER = "startPicker"
const val END_PICKER = "endPicker"

private val SINGLE_EXECUTOR = Executors.newSingleThreadExecutor()

fun executeThread(f: () -> Unit) {
    SINGLE_EXECUTOR.execute(f)
}
