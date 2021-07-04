package com.store.model

import java.util.concurrent.atomic.AtomicInteger

data class Product(val name: String = "", val type: String = "gadget", val inventory: Int = 0, val id: Int = idGenerator.getAndIncrement()) {
    fun newProduct(name: String?, type: String?, inventory: Int) =
            Product(name ?: this.name, type ?: this.type, inventory)

    companion object IDFactory {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}
