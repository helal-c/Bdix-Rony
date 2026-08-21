plugins {
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly("com.lagradost:cloudstream3:pre-release")
}
