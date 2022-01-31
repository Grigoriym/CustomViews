import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.grappim.customviews"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType(KotlinCompile::class).configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

val material = "1.5.0"
val coroutines = "1.6.0"
val lifecycle = "2.4.0"
val activityV = "1.4.0"

val lifecycleLiveData =
    "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle}"
val lifecycleViewModel =
    "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle}"
val lifecycleRuntime =
    "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle}"
val activity = "androidx.activity:activity:${activityV}"

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.fragment:fragment-ktx:1.4.1")
    implementation(activity)

    implementation("com.google.android.material:material:${material}")

    implementation(lifecycleLiveData)
    implementation(lifecycleViewModel)
    implementation(lifecycleRuntime)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}")
}