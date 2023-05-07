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
    implementation(libs.junit.junit)
    implementation(libs.test.ext)
    implementation(libs.test.espresso)
    implementation(libs.bundles.retrofit)
    implementation(libs.coroutine.android)
    implementation(libs.bundles.lifecycle)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))


}

kapt {
    correctErrorTypes = true
}