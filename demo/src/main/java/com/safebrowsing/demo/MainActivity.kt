package com.safebrowsing.demo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sclpfybn.safebrowsing.SafeBrowsingHelper
import com.sclpfybn.safebrowsing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.safeBrowsingButton.setOnClickListener { startAccessibilityService() }
        binding.checkButton.setOnClickListener {
            showToast("Safebrowsing is ${getSafeBrowsingStatus()}")
        }
        checkNotificationPermission()
    }

    private fun checkNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1988)
            }
        }
    }

    private fun getSafeBrowsingStatus(): Boolean {
        return SafeBrowsingHelper.isAccessibilitySettingsOn(
            this, TestSafeBrowsingService::class.java.name)
    }

    private fun startAccessibilityService() {
        if (!SafeBrowsingHelper.goToAccessibilitySettings(this)) {
            val isSuccess = SafeBrowsingHelper.goToGeneralSettings(this)
            if (!isSuccess) {
                showToast("Please open Settings app and go to Settings - Accessibility")
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
