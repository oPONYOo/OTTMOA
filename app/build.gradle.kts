plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}



android {
    namespace = "com.ponyo.ottmoa"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ponyo.ottmoa"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat.appcompat)
    implementation(libs.material.material)
    testImplementation(libs.junit.junit)
    testImplementation(libs.test.ext)
    testImplementation(libs.test.espresso)
    testImplementation(libs.androidx.testing)
    implementation(libs.bundles.retrofit)
    implementation(libs.coroutine.android)
    implementation(libs.bundles.lifecycle)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    kaptTest(libs.hilt.compiler)
    testAnnotationProcessor(libs.hilt.compiler)
    kaptAndroidTest(libs.hilt.compiler)
    testImplementation(libs.hilt.testing)
    androidTestImplementation(libs.hilt.testing)
    testImplementation(libs.mockito.mockito)
    testImplementation(libs.robolectric.robolectric)

    debugImplementation("com.facebook.flipper:flipper:0.191.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.4")
    releaseImplementation("com.facebook.flipper:flipper-noop:0.154.0")
    releaseImplementation("com.github.theGlenn:flipper-android-no-op:0.9.0")
    debugImplementation ("com.facebook.flipper:flipper-network-plugin:0.191.0")

    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))








}

kapt {
    correctErrorTypes = true
}