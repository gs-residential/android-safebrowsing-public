package com.safebrowsing.demo

import android.content.Context
import com.sclpfybn.safebrowsing.SafeBrowsingService
import com.sclpfybn.safebrowsing.detector.SafeBrowseNotifier

class TestSafeBrowsingService : SafeBrowsingService() {

    override fun createSafeBrowseNotifier(context: Context): SafeBrowseNotifier {
        return TestSafeNotifier(context)
    }
}
