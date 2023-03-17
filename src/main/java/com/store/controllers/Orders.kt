package com.store.controllers

import com.store.exceptions.ValidationException
import com.store.model.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class Orders {
    @PostMapping("/orders")
    fun create(@Valid @RequestBody order: Order,  @AuthenticationPrincipal user: User): ResponseEntity<Id> {
        DB.reserveProductInventory(order.productid, order.count)
        DB.addOrder(order)

        return ResponseEntity(Id(order.id), HttpStatus.OK)
    }

    @GetMapping("/orders/{id}")
    fun get(@PathVariable("id") id: Int) = DB.getOrder(id)

    @DeleteMapping("/orders/{id}")
    fun delete(@PathVariable("id") id: Int,  @AuthenticationPrincipal user: User): ResponseEntity<String> {
        DB.deleteOrder(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/orders/{id}")
    fun update(@PathVariable("id") id: Int, @Valid @RequestBody order: Order, @AuthenticationPrincipal user: User): ResponseEntity<String> {
        if(order.id == 0)
            throw ValidationException("Product id cannot be null")

        DB.updateOrder(order)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/orders")
    fun search(@RequestParam(name="status", required=false) status: String?,
               @RequestParam(name="productid", required=false) productid: Int?): List<Order> = DB.findOrders(status, productid)

    @GetMapping("/orderinvoices/{id}")
    fun getInvoice(@PathVariable("id") id: Int) = "invoice"
}
