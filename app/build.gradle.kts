plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.drawit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.drawit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        //
        renderscriptTargetApi= 21
        renderscriptSupportModeEnabled= true
        //
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation ("com.github.QuadFlask:colorpicker:0.0.15")
    implementation("com.google.android.material:material:1.1.0-alpha10")
    implementation ("com.airbnb.android:lottie:6.1.0")

    // DS Photo Editor SDK

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(files("libs/ds-photo-editor-sdk-v10.aar"))

    // SDK related dependencies

    implementation ("io.reactivex.rxjava3:rxjava:3.1.4")

    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    implementation ("com.makeramen:roundedimageview:2.3.0")

    implementation("com.android.volley:volley:1.2.1")
}