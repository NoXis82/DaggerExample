plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.noxis.daggerexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.noxis.daggerexample"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Android Architecture Components
//    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    implementation ("androidx.lifecycle:lifecycle-common-java8:2.7.0")
//    implementation ("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.7.0")
    //Dagger2
    implementation ("com.google.dagger:dagger:2.23.2")
    kapt ("com.google.dagger:dagger-compiler:2.23.2")
    implementation ("com.google.dagger:dagger-android:2.23.2")
    implementation ("com.google.dagger:dagger-android-support:2.23.2")
    kapt ("com.google.dagger:dagger-android-processor:2.23.2")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    kapt ("com.github.bumptech.glide:compiler:4.16.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}