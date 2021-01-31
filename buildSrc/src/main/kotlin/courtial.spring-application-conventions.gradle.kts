import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    java
    id("org.springframework.boot")

    // git properties to actuator
    id("com.gorylenko.gradle-git-properties")

    // dev
    idea
}

group = "ru.atlhnm"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")

    implementation("io.micrometer:micrometer-registry-prometheus")

//    implementation(project(":backend:libs:logging"))

    // ? оно надо здесь?
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    // ? оно надо здесь?
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // Testing
//    testImplementation(project(":backend:libs:testing"))
}


tasks.getByName<BootBuildImage>("bootBuildImage") {
//    $CI_REGISTRY/$CI_PROJECT_NAMESPACE/$CI_PROJECT_NAME:$CI_COMMIT_REF_NAME $DOCKER_BUILD_ARGS $CI_PROJECT_NAME-app
//    imageName = "kto/takie/musora"

    docker {
        publishRegistry {
//            token = ""
        }
    }
}

tasks.bootRun {
    args = listOf(
        "--spring.profiles.active=dev",
        "--spring.config.location=file:./../../configs/application.yml,file:./../../configs/${project.name}.yml"
    )
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
