package com.store.model

import java.util.concurrent.atomic.AtomicInteger

class Order(val productid: Int = 0, val count: Int = 0, var status: String = "pending", val id: Int = idGenerator.getAndIncrement()) {
    companion object {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}
