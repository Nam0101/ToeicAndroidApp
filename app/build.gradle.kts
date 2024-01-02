plugins {
    id("com.android.application")
    //dagger hilt
    id("dagger.hilt.android.plugin")


}

android {
    namespace = "com.example.englishapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.englishapp"
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
    // databinding
    buildFeatures {
        dataBinding = true
    }
    //buildconfig enable
    buildFeatures {
        buildConfig = true
    }
    //base url
    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"http://192.168.1.120/tienganh/\"")

        }
        release {
            buildConfigField("String", "BASE_URL", "\"http://192.168.1.120/tienganh/\"")
        }
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")

    //RXJAVA
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //RETROFIT
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
    // http logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.2")

    //ROOM
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-rxjava3:2.6.1")
    //LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")

    //DAGGER HILT
    implementation("com.google.dagger:hilt-android:2.48.1")
    annotationProcessor("com.google.dagger:hilt-android-compiler:2.48.1")


    implementation("com.google.code.gson:gson:2.10.1")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}