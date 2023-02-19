package com.store.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.concurrent.atomic.AtomicInteger
import javax.validation.constraints.NotNull

data class Product(@field:NotNull val name: String = "", @field:NotNull val type: String = "gadget", @field:NotNull val inventory: Int = 0, @field:NotNull val id: Int = idGenerator.getAndIncrement()) {
    fun newProduct(name: String?, type: String?, inventory: Int) =
            Product(name ?: this.name, type ?: this.type, inventory)

    companion object IDFactory {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}
