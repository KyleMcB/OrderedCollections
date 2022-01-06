/*
 * Copyright (c) 2021-2022. Kyle McBurnett
 */

plugins {
    kotlin("jvm")
    `maven-publish`
    jacoco
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
            artifactId = "impl"
        }
    }
}
repositories {
    mavenLocal()
}
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}


tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
dependencies {
    implementation(project(":core"))
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    implementation(project(":OrderedCollectionTests"))
}