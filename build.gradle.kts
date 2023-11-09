import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
    kotlin("jvm") version "1.9.10"
    kotlin("kapt") version "1.9.10"
}

val arrowVersion = "0.11.0"
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("io.arrow-kt:arrow-core-data:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx:$arrowVersion")
    implementation("io.arrow-kt:arrow-mtl:$arrowVersion")
    implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.awaitility:awaitility:4.2.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")

    // need this at compile level for chapter 8
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.suppressWarnings = true
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

ktlint {
    verbose.set(true)
    disabledRules.set(
        setOf(
            "comment-spacing",
            "filename",
            "import-ordering",
            "no-line-break-before-assignment"
        )
    )
}

kapt {
    useBuildCache = false
}
