package org.micah.agrifarm360.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class Revenue(
    val amount: Double,
    val image: DrawableResource,
    val source: String,
    val date: Long
)