package com.emerald.legacy

import android.app.NativeActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager

/**
 * Hosts the ARM64 Android build of LCE directly through Android NativeActivity.
 *
 * libMinecraftClient.so must export ANativeActivity_onCreate. Launch metadata such
 * as instance_path and extra_args is passed through this Activity's Intent extras.
 */
class NativeGameActivity : NativeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}
