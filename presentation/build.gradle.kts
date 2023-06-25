
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ponyo.presentation"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.ktx)
    implementation(libs.appcompat.appcompat)
    implementation(libs.material.material)
    testImplementation(libs.junit.junit)
    testImplementation(libs.test.ext)
    testImplementation(libs.test.espresso)
    testImplementation(libs.androidx.testing)
    implementation(libs.bundles.retrofit)
    implementation(libs.coroutine.android)
    implementation(libs.hilt.android)
    implementation(libs.activity.ktx)
    implementation(libs.bundles.compose)
    kapt(libs.hilt.compiler)
    kaptTest(libs.hilt.compiler)
    testAnnotationProcessor(libs.hilt.compiler)
    kaptAndroidTest(libs.hilt.compiler)
    testImplementation(libs.hilt.testing)
    androidTestImplementation(libs.hilt.testing)
    testImplementation(libs.mockito.mockito)
    testImplementation(libs.robolectric.robolectric)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.mockk.mockk)
    androidTestImplementation(libs.mockk.android)

    implementation("com.holix.android:bottomsheetdialog-compose:1.2.1")



    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.7.1")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata")


    implementation("androidx.compose.foundation:foundation:1.4.3")
    // Material Design
    implementation("androidx.compose.material:material:1.4.3")
    implementation("io.coil-kt:coil-compose:2.3.0")



}

kapt {
    correctErrorTypes = true
}