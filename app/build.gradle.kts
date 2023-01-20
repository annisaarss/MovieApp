plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")

}

android {
    namespace = "com.annisaarss.movieapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.annisaarss.movieapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"https://imdb-api.com/en/API/\"")
        buildConfigField("String", "API_KEY", "\"k_mco7r6dy\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("io.reactivex.rxjava2:rxjava:2.1.9")

    //Nucleo
    implementation("com.nbsengineering:nucleo:1.1.4")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation ("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.0")

    implementation("com.tbuonomo:dotsindicator:4.3")

    debugImplementation ("com.github.chuckerteam.chucker:library:3.5.2")

}