plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.express"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.express"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
buildFeatures{
    viewBinding = true
}
}

dependencies {


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.ccp)
    implementation(libs.gson)
    implementation(libs.glide)
    implementation(libs.circleindicator)
    implementation(libs.circleimageview)
    implementation(libs.material.spinner)
    implementation(libs.roundedimageview)
    implementation(libs.com.google.gms.google.services.gradle.plugin)
    implementation(libs.firebase.bom)
    implementation(libs.firebase.analytics)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.inappmessaging.display)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}