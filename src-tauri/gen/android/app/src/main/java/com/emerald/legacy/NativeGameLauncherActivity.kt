package com.emerald.legacy

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import java.io.File

/**
 * Small Android-side gate between Emerald's launcher UI and the native LCE runtime.
 *
 * The game runtime must be bundled as:
 *   src/main/jniLibs/arm64-v8a/libMinecraftClient.so
 *
 * This deliberately does not fall back to Wine, Proton, Box64, FEX, or a container.
 */
class NativeGameLauncherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nativeLibrary = File(applicationInfo.nativeLibraryDir, "libMinecraftClient.so")
        if (!nativeLibrary.isFile) {
            AlertDialog.Builder(this)
                .setTitle("Native game runtime missing")
                .setMessage(
                    "This build does not contain the ARM64 native LCE runtime " +
                        "(libMinecraftClient.so). Emerald will not fall back to an emulator " +
                        "or Windows compatibility layer."
                )
                .setPositiveButton(android.R.string.ok) { _, _ -> finish() }
                .setOnCancelListener { finish() }
                .show()
            return
        }

        val gameIntent = Intent(this, NativeGameActivity::class.java)
        intent.extras?.let(gameIntent::putExtras)
        startActivity(gameIntent)
        finish()
    }
}
