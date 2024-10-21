package org.marshsoft.nextcalc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform