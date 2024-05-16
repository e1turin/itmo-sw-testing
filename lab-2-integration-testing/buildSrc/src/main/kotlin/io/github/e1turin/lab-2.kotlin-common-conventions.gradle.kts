plugins {
    kotlin("jvm")
}

/** Reuse type-safe version catalogs
 *  See https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
 *
 *  > import org.gradle.accessors.dm.LibrariesForLibs
 *  > val libs = the<LibrariesForLibs>()
 *  Do not work because of `accessors` module is not found
 */

dependencies {
    testImplementation(kotlin("test"))

    val kotestVersion = "5.8.0"
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}