import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
	id("junitbuild.kotlin-library-conventions")
	`java-test-fixtures`
	alias(libs.plugins.errorprone)
	alias(libs.plugins.nullaway)
}

description = "JUnit Jupiter API"

nullaway {
	annotatedPackages.addAll(
		"org.junit.jupiter.api.io",
	)
}

dependencies {
	api(platform(projects.junitBom))
	api(libs.opentest4j)
	api(projects.junitPlatformCommons)

	compileOnlyApi(libs.apiguardian)

	compileOnly(kotlin("stdlib"))
	compileOnly(libs.jetbrainsAnnotations)

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)

	errorprone(libs.errorprone.core)
	errorprone(libs.nullaway)
}

tasks {
	compileModule {
		options.compilerArgs.addAll(listOf(
			"--add-modules", "org.jetbrains.annotations",
			"--add-reads", "${javaModuleName}=org.jetbrains.annotations",
		))
	}
	jar {
		bundle {
			val version = project.version
			bnd("""
				Require-Capability:\
					org.junit.platform.engine;\
						filter:='(&(org.junit.platform.engine=junit-jupiter)(version>=${'$'}{version_cleanup;${version}})(!(version>=${'$'}{versionmask;+;${'$'}{version_cleanup;${version}}})))';\
						effective:=active
			""")
		}
	}
}
