package org.micah.agrifarm360.domain.models

import org.jetbrains.compose.resources.DrawableResource

data class Revenue(
    val id: Int,
    val amount: Double,
    val source: String,
    val date: Long
)