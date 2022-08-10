package com.store.model

import java.util.concurrent.atomic.AtomicInteger
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class Order(@NotNull val productid: Int = 0, @NotNull val count: Int = 0, @NotNull var status: String = "pending", @Id @NotNull val id: Int = idGenerator.getAndIncrement()) {
    companion object {
        val idGenerator: AtomicInteger = AtomicInteger()
    }
}
