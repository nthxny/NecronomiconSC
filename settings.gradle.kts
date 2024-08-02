import dev.kikugie.stonecutter.StonecutterSettings

pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/")
		maven("https://maven.architectury.dev")
		maven("https://maven.minecraftforge.net")
		maven("https://maven.neoforged.net/releases/")
		maven("https://maven.kikugie.dev/releases")
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.4.+"
}

extensions.configure<StonecutterSettings> {
	kotlinController = true
	centralScript = "build.gradle.kts"
	shared {
		fun mc(version: String, vararg loaders: String) {
			for (it in loaders) vers("$version-$it", version)
		}
		//mc("1.18.2", "fabric", "forge")
		mc("1.19.2", "fabric", "forge")
		mc("1.20.1", "fabric", "forge")
		mc("1.21", "fabric", "neoforge")
	}
	create(rootProject)
}
rootProject.name = "Necronomicon"