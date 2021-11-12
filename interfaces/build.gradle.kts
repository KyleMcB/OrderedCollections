/*
 * Copyright (c) 2021. Kyle McBurnett
 */

plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "com.xingpeds.orderedcollections"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("OrderedCollections") {
                from(components["java"])
                groupId = "com.github.KyleMcB.orderedcollections"
                artifactId = "interface"
            }
        }
    }
    repositories {
        mavenLocal()
    }
}
dependencies {
    implementation(kotlin("stdlib"))
}