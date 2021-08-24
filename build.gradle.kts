import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
    id("maven-publish")
}

group = "io.github.rgiaviti"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Dependencies Versions
val serializationJsonVersion = "1.2.2"
val okHttpVersion = "4.9.1"
val mockkVersion = "1.12.0"

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationJsonVersion")
    compileOnly("com.squareup.okhttp3:okhttp:$okHttpVersion")
    testImplementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/rgiaviti/viacep-klient")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["kotlin"])
        }
        publications {
            create<MavenPublication>("mavenKotlin") {
                pom {
                    name.set("ViaCEP Klient")
                    description.set("A client SDK to retrieve brazilian CEP informations from ViaCEP service")
                    url.set("https://github.com/rgiaviti/viacep-klient")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("rg")
                            name.set("Ricardo Giaviti")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com:rgiaviti/viacep-klient.git")
                        developerConnection.set("scm:git:ssh://git@github.com:rgiaviti/viacep-klient.git")
                        url.set("https://github.com/rgiaviti/viacep-klient")
                    }
                }
            }
        }
    }
}