package com.store.controllers

import com.store.model.DB
import com.store.model.Order
import com.store.model.notValid
import com.store.model.validateAuthToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException

@RestController
class Orders {
    @PostMapping("/orders")
    fun add(@RequestBody order: Order, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<Int> {
        validateAuthToken(header)

        DB.addOrder(order)
        return ResponseEntity(order.id, HttpStatus.CREATED)
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
               @RequestParam(name="productid", required=false) productid: Int?) = DB.findOrders(status, productid)
}
