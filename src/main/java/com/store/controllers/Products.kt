package com.store.controllers

import com.store.model.DB
import com.store.model.Product
import com.store.model.validateAuthToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Products {
    @PostMapping("/products/{id}")
    fun update(@PathVariable id: Int, @RequestBody product: Product, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.updateProduct(product)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/products/{id}")
    fun get(@PathVariable("id") id: Int) = DB.findProduct(id)

    @PostMapping("/products")
    fun create(@RequestBody newProduct: Product, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<Int> {
        validateAuthToken(header)

        DB.addProduct(newProduct)
        return ResponseEntity(newProduct.id, HttpStatus.CREATED)
    }

    @DeleteMapping("/products/{id}")
    fun delete(@PathVariable("id") id: Int, @RequestHeader("Authenticate", required = true) header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.deletePet(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @GetMapping("/products")
    fun search(@RequestParam(name="name", required=false) name: String?,
               @RequestParam(name="type", required=false) type: String?,
               @RequestParam(name="status", required=false) status: String?) = DB.findProducts(name, type, status)
}
