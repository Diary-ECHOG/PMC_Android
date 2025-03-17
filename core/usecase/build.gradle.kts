plugins {
    id("java-library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation("com.google.dagger:hilt-core:2.55")
    implementation(project(":core:repository"))
}
