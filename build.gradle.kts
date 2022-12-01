// Import task types
import com.bmuschko.gradle.docker.tasks.image.*

buildscript {
	dependencies {
		classpath("com.bmuschko:gradle-docker-plugin:9.0.1")
	}
}


plugins {
	java
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.bmuschko.docker-spring-boot-application") version "9.0.1"
	`maven-publish`
}

tasks.create("buildMyAppImage", DockerBuildImage::class) {
	inputDir.set(file("src/main/docker"))
	images.add("test/app:latest")
}

group = "org.octopusden"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
