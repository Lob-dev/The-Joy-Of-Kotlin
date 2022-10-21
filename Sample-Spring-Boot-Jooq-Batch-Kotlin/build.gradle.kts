import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("nu.studer.jooq") version "7.1.1"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.sample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

val springBootVersion = "2.7.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")

	runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.0.6")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-batch:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-jooq:$springBootVersion")

	implementation("org.jooq:jooq:3.17.4")
	jooqGenerator("org.glassfish.jaxb:jaxb-runtime:4.0.0")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	jooqGenerator("org.mariadb.jdbc:mariadb-java-client:3.0.6")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
	implementation("io.github.microutils:kotlin-logging-jvm:3.0.2")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:$springBootVersion")
	testImplementation("org.springframework.batch:spring-batch-test:4.3.7")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
	configurations {
		create("main") {
			generateSchemaSourceOnCompilation.set(false)
			jooqConfiguration.apply {
				jdbc.apply {
					driver = "org.mariadb.jdbc.Driver"
					url = "jdbc:mariadb://localhost:3306/sample"
					user = "root"
					password = "1234"
				}
				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						name = "org.jooq.meta.mariadb.MariaDBDatabase"
						inputSchema = "sample"
						forcedTypes.addAll(listOf(
							org.jooq.meta.jaxb.ForcedType().apply {
								name = "varchar"
								includeExpression = ".*"
								includeTypes = "JSONB?"
							},
							org.jooq.meta.jaxb.ForcedType().apply {
								name = "varchar"
								includeExpression = ".*"
								includeTypes = "INET"
							},
							org.jooq.meta.jaxb.ForcedType().apply {
								name = "BOOLEAN"
								includeTypes = "(?i:TINYINT\\(1\\))"
							},
						))
					}
					generate.apply {
						withDeprecated(false)
						withRecords(true)
						withImmutablePojos(true)
						withFluentSetters(true)
						withJavaTimeTypes(true)
					}
					target.apply {
						withPackageName("jooq.dsl")
						withDirectory("src/generated/jooq")
						withEncoding("UTF-8")
					}
					strategy.name = "org.jooq.codegen.example.JPrefixGeneratorStrategy"
				}
			}
		}
	}
}
