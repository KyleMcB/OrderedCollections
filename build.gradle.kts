plugins {
    kotlin("jvm") version "1.5.31"
}

group = "com.xingpeds.orderedCollections"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}