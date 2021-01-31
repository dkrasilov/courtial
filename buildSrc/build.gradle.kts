plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

dependencies {
    // Kotlin Plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.4.21")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.4.21")

    // SonarQube Plugin
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.1.1")

    // Spring Boot Plugin
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.2")

    // Git Properties Plugin
    implementation("gradle.plugin.com.gorylenko.gradle-git-properties:gradle-git-properties:2.2.4")

}