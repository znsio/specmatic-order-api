package com.store.model

import java.util.concurrent.atomic.AtomicInteger

data class Product(val name: String = "", val type: String = "gadget", val id: Int = idGenerator.getAndIncrement()) {
    fun newProduct(name: String?, type: String?) =
            Product(name ?: this.name, type ?: this.type)

    companion object IDFactory {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}
