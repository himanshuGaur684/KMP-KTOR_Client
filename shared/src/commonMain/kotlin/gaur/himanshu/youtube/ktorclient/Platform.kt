package gaur.himanshu.youtube.ktorclient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform