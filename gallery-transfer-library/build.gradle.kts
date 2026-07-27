import java.util.Properties
import com.android.build.api.artifact.SingleArtifact

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

// Read the Dropbox app key from local.properties (git-ignored). Falls back to empty string so the
// project still builds before the developer has created their Dropbox app.
val dropboxAppKey: String = run {
    val props = Properties()
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) {
        localFile.inputStream().use { props.load(it) }
    }
    props.getProperty("DROPBOX_APP_KEY", "")
}

android {
    namespace = "com.gallerytransferlibrary"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.gallerytransferlibrary"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "DROPBOX_APP_KEY", "\"$dropboxAppKey\"")
        buildConfigField("String", "DROPBOX_REDIRECT_URI", "\"com.gallerytransferlibrary://oauth\"")
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = ".20"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

// Copy the built APK into the shared repo-level `apk/` folder after packaging.
androidComponents {
    onVariants { variant ->
        val capitalized = variant.name.replaceFirstChar { it.uppercase() }
        val copyApk = tasks.register<Copy>("copy${capitalized}ApkToSharedDir") {
            from(variant.artifacts.get(SingleArtifact.APK)) {
                include("*.apk")
                rename { it.replace("-debug", "") }
            }
            into(rootProject.layout.projectDirectory.dir("apk"))
        }
        afterEvaluate {
            tasks.named("package$capitalized").configure { finalizedBy(copyApk) }
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.coil.compose)
    implementation(libs.coil.video)

    // Dropbox stack (WebView auth + Retrofit/OkHttp upload). Core lives in :common; module wires it up.
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.work.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
