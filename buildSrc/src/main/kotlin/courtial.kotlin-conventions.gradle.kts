plugins {
    java
    `maven-publish`

    // coverage
    jacoco
    id("org.sonarqube")

    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}
java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    // import a BOM
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.4.2"))
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2020.0.0"))

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

group = "ru.sfera"
version = "1.0.0"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

sonarqube {
    properties {
        property("sonar.host.url", "https://sonarqube.gitlab.bcs.ru")
        property("sonar.projectName", "sfera:${project.name}")
    }
}

jacoco {
    toolVersion = "0.8.6"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.isEnabled = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    maxParallelForks = 2
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

//tasks.withType<KotlinCompile> {
//    kotlinOptions {
//        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
//        jvmTarget = "11"
//    }
//}