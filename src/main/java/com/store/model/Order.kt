package com.store.model

import java.util.concurrent.atomic.AtomicInteger
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class Order(@field:Positive val productid: Int = 0, @field:Positive val count: Int = 0, @field:NotNull var status: String = "pending", val id: Int = idGenerator.getAndIncrement()) {
    companion object {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}
