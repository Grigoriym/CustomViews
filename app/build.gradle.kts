import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
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

object Rx2 {
    const val android = "2.1.1"
    const val java = "2.2.9"
}

object Testing {
    const val junit = "4.13.2"
    const val androidJunit = "1.1.3"
    const val androidEspressoCore = "3.4.0"

    const val assertJCore = "3.18.1"
    const val androidCoreTesting = "1.1.1"
}


val junit = "junit:junit:${Testing.junit}"
val androidJunit = "androidx.test.ext:junit:${Testing.androidJunit}"
val androidEspressoCore =
    "androidx.test.espresso:espresso-core:${Testing.androidEspressoCore}"

val assertJCore = "org.assertj:assertj-core:${Testing.assertJCore}"

val androidCoreTesting =
    "android.arch.core:core-testing:${Testing.androidCoreTesting}"


val android = "io.reactivex.rxjava2:rxandroid:${Rx2.android}"
val java = "io.reactivex.rxjava2:rxjava:${Rx2.java}"

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

    implementation(android)
    implementation(java)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutines}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutines}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation(junit)
    testImplementation(assertJCore)
    testImplementation(androidCoreTesting)
    androidTestImplementation(androidJunit)
    androidTestImplementation(androidEspressoCore)
}