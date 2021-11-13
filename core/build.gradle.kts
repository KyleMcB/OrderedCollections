/*
 * Copyright (c) 2021. Kyle McBurnett
 */

plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.github.KyleMcB.orderedcollections"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("OrderedCollections") {
            from(components["java"])

            artifactId = "core"
        }
    }
}
repositories {
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib"))
}