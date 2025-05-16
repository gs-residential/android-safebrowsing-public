## Safebrowsing

### How to use
Check demo app 🙂 and please note the following:
1. Provide via AndroidManifest unless you have custom lib version `meta-data`: `sclpfybn_safebrowsing_distributorId`, `sclpfybn_safebrowsing_partnerId`.
2. Provide optional via AndroidManifest `meta-data`: `sclpfybn_safebrowsing_endpoint`.
3. Override string resources: `accessibility_service_title`, `accessibility_service_description`, `accessibility_service_summary`.
Here means: just provide your own strings with the same names.
4. Extend `com.sclpfybn.safebrowsing.detector.SafeBrowseNotifier`.
5. Extend `com.sclpfybn.safebrowsing.SafeBrowsingService` and provide your extended `Notifier`.
6. Optionally, you can provide your own logger to enable logs. 
To do that call in the you `App` class: `com.sclpfybn.safebrowsing.monitoring.LibLogger.init(* extends com.sclpfybn.safebrowsing.monitoring.LoggerApi)`.
7. Check helpers for Accessibility checks and settings navigation `com.sclpfybn.safebrowsing.SafeBrowsingHelper`.

### Download
1. Add the token to `$HOME/.gradle/gradle.properties` or `project_root/gradle.properties`:
```
authToken=your_token
```

2. Add the ***JitPack*** repository the *for safebrowsing*:
```groovy
maven {  
  url "https://jitpack.io"  
  credentials { username authToken }  
}
```

3. Add the dependency:
```groovy
dependencies {
	implementation 'com.github.gs-residential:android-safebrowsing:1.0.9'
}
```

### Proguard
```groovy
-keepclasseswithmembers public class * extends android.app.Service
-keep class com.sclpfybn.safebrowsing.detector.data.** { *; }
-keep class com.sclpfybn.safebrowsing.monitoring.** { *; }
-keep class com.sclpfybn.safebrowsing.SafeBrowsingHelper { *; }
-keep class com.sclpfybn.safebrowsing.detector.SafeBrowseNotifier { *; }
```

### How to build
1. Clone repo
2. Open project
3. Have fun 🎉
