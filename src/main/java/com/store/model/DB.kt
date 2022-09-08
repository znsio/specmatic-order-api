package com.store.model

object DB {
    private var PRODUCTS: MutableMap<Int, Product> = mutableMapOf(10 to Product("XYZ Phone", "gadget", 10, 10), 20 to Product("Gemini", "dog", 10, 20))
    private var ORDERS: MutableMap<Int, Order> = mutableMapOf(10 to Order(10, 2, "pending", 10), 20 to Order(10, 1, "pending", 20))

    fun resetDB() {
        PRODUCTS = mutableMapOf(10 to Product("XYZ Phone", "gadget", 10, 10), 20 to Product("Gemini", "dog", 10, 20))
        ORDERS = mutableMapOf(10 to Order(10, 2, "pending", 10), 20 to Order(10, 1, "pending", 20))
    }

    fun addProduct(product: Product) {
        PRODUCTS[product.id] = product
    }

    fun findProduct(id: Int): Product = PRODUCTS.getValue(id)

    fun updateProduct(update: Product) {
        PRODUCTS[update.id] = PRODUCTS.getValue(update.id).newProduct(update.name, update.type, update.inventory)
    }

    fun deletePet(id: Int) { PRODUCTS.remove(id) }

    fun findProducts(name: String?, type: String?, status: String?): List<Product> {
        return PRODUCTS.filter { (id, product) ->
            product.name == name || product.type == type || inventoryStatus(id) == status
        }.values.toList()
    }

    private fun inventoryStatus(productid: Int): String {
        return when (PRODUCTS.getValue(productid).inventory) {
            0 -> "sold"
            else -> "available"
        }
    }

    fun addOrder(order: Order) {
        ORDERS[order.id] = order
    }

    fun getOrder(id: Int): Order = ORDERS.getValue(id)

    fun deleteOrder(id: Int) {
        ORDERS.remove(id)
    }

    fun findOrders(status: String?, productId: Int?): List<Order> {
        return ORDERS.filter { (_, order) ->
            order.status == status || order.productid == productId
        }.values.toList()
    }

    fun cleanSlate() {
        PRODUCTS.clear()
        ORDERS.clear()
    }

    fun updateOrder(updatedOrder: Order) {
        ORDERS[updatedOrder.id] = updatedOrder
    }

    fun reserveProductInventory(productId: Int, count: Int) {
        val updatedProduct = PRODUCTS.getValue(productId).let {
            it.copy(inventory = it.inventory - count)
        }

        PRODUCTS[productId] = updatedProduct
    }
}
