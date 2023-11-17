plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.facebook.react")
}

react {
    root.set(file("../"))
}

android {
    namespace = "com.example.kmpreactnative"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        targetSdk = 34

        applicationId = "com.example.kmpreactnative"
        versionCode = 1
        versionName = "0.1"
    }
    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidComposeCompiler.get()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        // Do not include coroutines debug infrastructure in the resulting APK
        // See https://github.com/Kotlin/kotlinx.coroutines#avoiding-including-the-debug-infrastructure-in-the-resulting-apk
        resources.excludes += "DebugProbesKt.bin"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.material)

    // React Native
    implementation("com.facebook.react:react-android")
    implementation("com.facebook.react:hermes-android")
}

apply(from = "../node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
val applyNativeModulesAppBuildGradle: groovy.lang.Closure<Any> by extra
applyNativeModulesAppBuildGradle(project, null)
