package com.store.model

object DB {
    private val PRODUCTS: MutableList<Product> = mutableListOf(Product("XYZ Phone", "gadget", 10), Product("Gemini", "dog", 20))
    private val ORDERS: MutableList<Order> = mutableListOf(Order(10, 2, "pending", 10), Order(10, 1, "pending", 20))
    private val INVENTORY: MutableMap<Int, InventoryRecord> = mutableMapOf()

    fun addProduct(product: Product) { PRODUCTS.add(product) }

    fun findProduct(id: Int) = PRODUCTS.first { it.id == id }

    fun updateProduct(update: Product) {
        val index = PRODUCTS.indexOfFirst { pet -> pet.id == update.id }
        PRODUCTS[index].newProduct(update.name, update.type).let {
            PRODUCTS.set(index, it)
        }
    }

    fun deletePet(id: Int) { PRODUCTS.removeIf { it.id == id } }

    fun findProducts(name: String?, type: String?, status: String?) = PRODUCTS.filter { it.name == name || it.type == type || inventoryStatus(it.id) == status }

    private fun inventoryStatus(productid: Int): String {
        val quantity = INVENTORY[productid]?.quantity ?: 0
        return when(quantity) {
            0 -> "sold"
            else -> "available"
        }
    }

    fun addOrder(order: Order) { ORDERS.add(order) }

    fun getOrder(id: Int) = ORDERS.first { it.id == id }

    fun cancelOrder(id: Int) {
        val order = ORDERS.find { it.id == id }
        if(order != null) {
            val newOrder = order.copy(status = "cancelled")
            ORDERS.removeIf { it.id == id }
            ORDERS.add(newOrder)
        }
    }

    fun findOrders(status: String?, productid: Int?) = ORDERS.filter { it.status == status || it.productid == productid }

    fun cleanSlate() {
        PRODUCTS.clear()
        ORDERS.clear()
    }

    fun updateOrder(updatedOrder: Order) {
        val index = ORDERS.indexOfFirst { order -> order.id == updatedOrder.id }
        ORDERS[index] = updatedOrder
    }

    fun addInventory(inventoryUpdate: InventoryRecord) {
        val inventoryRecord = INVENTORY[inventoryUpdate.productid] ?: InventoryRecord(inventoryUpdate.productid, 0)
        val updatedRecord = inventoryRecord.copy(quantity = inventoryRecord.quantity + inventoryUpdate.quantity)
        INVENTORY[inventoryRecord.productid] = updatedRecord
    }

    fun getInventory(productid: Int): Int {
        return INVENTORY[productid]?.quantity ?: 0
    }
}
