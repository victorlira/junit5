plugins {
	id("junitbuild.java-library-conventions")
}

description = "JUnit Jupiter (Aggregator)"

dependencies {
	api(platform(projects.junitBom))
	api(projects.junitJupiterApi)
	api(projects.junitJupiterParams)

	compileOnly(libs.errorprone.annotations)
	compileOnly(libs.jetbrainsAnnotations)

	runtimeOnly(projects.junitJupiterEngine)

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)
}

tasks {
	compileModule {
		options.compilerArgs.addAll(listOf(
			"--add-modules", "com.google.errorprone.annotations,org.jetbrains.annotations",
			"--add-reads", "org.junit.jupiter.api=com.google.errorprone.annotations",
			"--add-reads", "org.junit.jupiter.api=org.jetbrains.annotations",
		))
	}
}
