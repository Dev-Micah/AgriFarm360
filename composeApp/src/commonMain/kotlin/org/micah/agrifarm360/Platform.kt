package org.micah.agrifarm360

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform