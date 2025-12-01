# YuviTal Life SDK - Android (Java) Sample Integration Guide

This repository contains an **Android Java sample application** that demonstrates how to integrate the YuviTal Life SDK into a native Android app.

---

## Compatibility Notice

This SDK is designed exclusively for **native Android applications** developed using:

- **Android:** Kotlin or Java

### Prerequisites

- **Target OS:** Android API 29+

### Installation

- **Android**

**Maven repository**

Accessing the Yuvital Life SDK requires credential configuration. Please don't share these credentials in version control.

We recommend adding your credentials to the **`gradle.properties`** file.

```java
# Add this to ~/.gradle/gradle.properties on your local machine, or put it in your CI's secrets store
yuvitalLifeSdkPassword=YOUR_PASSWORD
```

**Add maven to your app's dependency resolution in `settings.gradle`:**

```java
 dependencyResolutionManagement {
     repositories {
        // Other repositories...
        google()
        mavenCentral()

        // Add JitPack
        maven { url "https://jitpack.io" }

        // Yuvital Life SDK (Cloudsmith)
        maven {
            url = uri("https://dl.cloudsmith.io/basic/yuvital/yuvital-life-sdk-production/maven/")
            credentials {
                username = "token"
                password = providers.gradleProperty("yuvitalLifeSdkPassword").get()
            }
        }
     }
 }
```

**Add the dependency to your app's `build.gradle`**

```java
dependencies {
    // Other dependencies...
    implementation "com.yuvital:yuvitallife-sdk:2.0.0"
}
```

**RTL Support**

YuviTal Life SDK has full RTL support
Add android:supportsRtl="true" on the host app AndroidManifest.xml inside <application>:

```xml
<!-- AndroidManifest.xml -->
    <application
        android:supportsRtl="true">
        <!-- ... -->
    </application>
```

### Debug build limitation

> **Note:** The YuviTal Life SDK currently **does not work in Android debug builds**.  
> It is fully supported in **release / signed builds** only.  
> Debug support is a work in progress and will be addressed in a future SDK version.
