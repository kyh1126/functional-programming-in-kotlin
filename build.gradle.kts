import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
    kotlin("jvm") version "1.9.10"
    kotlin("kapt") version "1.9.10"
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

val arrowVersion = "0.11.0"
dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("io.arrow-kt:arrow-core-data:$arrowVersion")
    compileOnly("io.arrow-kt:arrow-fx:$arrowVersion")
    compileOnly("io.arrow-kt:arrow-mtl:$arrowVersion")
    compileOnly("io.arrow-kt:arrow-syntax:$arrowVersion")
    compileOnly("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5")
    compileOnly("io.github.microutils:kotlin-logging:3.0.5")
    compileOnly("org.awaitility:awaitility:4.2.0")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.9")

    // need this at compile level for chapter 8
    compileOnly("io.kotlintest:kotlintest-runner-junit5:3.4.2")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.suppressWarnings = true
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
