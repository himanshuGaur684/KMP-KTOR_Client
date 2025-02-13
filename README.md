# 🚀 Ktor Client in Kotlin Multiplatform (KMP) | Complete Guide

This repository contains the full source code from my **YouTube video** on using **Ktor Client** in a **Kotlin Multiplatform (KMP)** project. Learn how to make network requests in a cross-platform way using **Ktor, coroutines, and Kotlin serialization**! 🎯

## 📌 What You’ll Learn
- Setting up **Ktor Client** in a KMP project
- Making API requests in **Android & iOS**
- Using **Kotlin Coroutines** for network calls
- Handling **JSON serialization** with Kotlinx
- Implementing best practices for networking

## 🛠️ Setup & Installation
### 1️⃣ Add Ktor Dependencies
```kotlin
kotlin {
    sourceSets.commonMain.dependencies {
        implementation("io.ktor:ktor-client-core:2.x.x")
        implementation("io.ktor:ktor-client-cio:2.x.x") // For JVM
        implementation("io.ktor:ktor-client-darwin:2.x.x") // For iOS
        implementation("io.ktor:ktor-client-serialization:2.x.x")
    }
}
```

### 2️⃣ Initialize Ktor Client
```kotlin
val client = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}
```

### 3️⃣ Make a Network Request
```kotlin
suspend fun fetchData(): ApiResponse {
    return client.get("https://api.example.com/data").body()
}
```

## 📺 Watch the Full Video
🎥 **[Watch on YouTube](https://youtu.be/qMDtnZ8gSe4)** 

## 🔗 Resources
- [Ktor Documentation](https://ktor.io/docs/)
- [Kotlin Multiplatform Guide](https://kotlinlang.org/docs/multiplatform.html)

⭐ **Star this repo if you found it useful!** 🚀
