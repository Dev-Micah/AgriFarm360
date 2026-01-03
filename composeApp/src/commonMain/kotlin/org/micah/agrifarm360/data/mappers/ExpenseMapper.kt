package org.micah.agrifarm360.data.mappers

import org.micah.agrifarm360.data.local.entities.ExpenseEntity
import org.micah.agrifarm360.domain.models.Expense

fun ExpenseEntity.toDomain(): Expense {
    return Expense(
        id = id,
        name = name,
        amount = amount,
        date = date,
        description = description
    )
}

fun Expense.toEntity(): ExpenseEntity {
    return ExpenseEntity(
        id = id,
        name = name,
        amount = amount,
        date = date,
        description = description
    )
}
