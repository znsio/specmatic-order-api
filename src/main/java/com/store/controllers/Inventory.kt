package com.store.controllers

import com.store.model.DB
import com.store.model.InventoryRecord
import com.store.model.Product
import com.store.model.validateAuthToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Inventory {
    @PostMapping("/inventory")
    fun update(@RequestBody inventoryRecord: InventoryRecord, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.addInventory(inventoryRecord)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/inventory")
    fun get(@RequestParam(name="productid", required = false) productId: Int): Int {
        return DB.getInventory(productId)
    }
}
