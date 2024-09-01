plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    groovy
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    runtimeOnly("org.postgresql:postgresql")


    implementation  ("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor  ("org.mapstruct:mapstruct-processor:1.5.5.Final")




    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    implementation("org.liquibase:liquibase-core")

    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.h2database:h2")

    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")



    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.swagger:swagger-annotations:1.6.4")
//AWS s3
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.569")



    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.security:spring-security-oauth2-core")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    //testImplementation("org.spockframework:spock-spring:2.3-groovy-4.0")
    testImplementation("org.spockframework:spock-core:2.2-groovy-3.0")
    testImplementation("org.spockframework:spock-spring:2.2-groovy-3.0")
    testImplementation("org.codehaus.groovy:groovy-all:3.0.9")
    testImplementation("io.github.benas:random-beans:3.9.0")
    testImplementation("org.mockito:mockito-core:4.6.1")


    //testImplementation ("io.github.benas:random-beans:3.10.0") // Or the appropriate version you need



    //  implementation("org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:2.1.7.RELEASE")

}


tasks.withType<Test> {
    useJUnitPlatform()
}
