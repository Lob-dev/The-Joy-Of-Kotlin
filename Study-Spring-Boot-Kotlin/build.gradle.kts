import org.springframework.boot.gradle.tasks.run.BootRun
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.allopen") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31" // Entity에 Multiple Constructor 지정을 위함
}

group = "lob.study"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation("io.projectreactor:reactor-core:3.1.1.RELEASE")
    implementation("org.postgresql:postgresql")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

// Kotlin Class 는 기본적으로 final 즉 상속 불가능한 Class 로 정의된다. Spring Boot 는 기본적으로 상속 기반의 CGLIB 를
// 사용하기 때문에, 상속이 되지 않는 클래스나 메서드에 대하여서 Proxy 를 만들 수 없다. 그렇기에 allOpen Configuration 을
// 이용하여 상속이 필요한 객체들에게 붙는 Annotation을 허용해야 한다.

// 추가적으로 "plugin.spring" 은 Component, Async, Transactional, Cacheable, SpringBootTest 등의 Annotation 이 붙은
// 클래스 혹은 메서드를 open 한다.
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<BootRun>("bootRun") {
    environment.put("SPRING_PROFILES_ACTIVE", environment.get("SPRING_PROFILES_ACTIVE") ?: "local")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
