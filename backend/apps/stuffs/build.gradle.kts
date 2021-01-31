plugins {
    id("courtial.kotlin-conventions")
    id("courtial.spring-application-conventions")
}

version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}