package com.store.controllers

import com.store.model.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import javax.validation.Valid

@RestController
class Orders {
    @PostMapping("/orders")
    fun create( @Valid @RequestBody order: Order, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<Id> {
        validateAuthToken(header)

        DB.reserveProductInventory(order.productid, order.count)
        DB.addOrder(order)

        return ResponseEntity(Id(order.id), HttpStatus.CREATED)
    }

    @GetMapping("/orders/{id}")
    fun get(@PathVariable("id") id: Int) = DB.getOrder(id)

    @DeleteMapping("/orders/{id}")
    fun delete(@PathVariable("id") id: Int, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.deleteOrder(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/orders/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody status: Order, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.updateOrder(status)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/orders")
    fun search(@RequestParam(name="status", required=false) status: String?,
               @RequestParam(name="productid", required=false) productid: Int?): List<Order> = DB.findOrders(status, productid)
}
