package com.store.controllers

import com.store.model.DB
import com.store.model.Id
import com.store.model.Product
import com.store.model.validateAuthToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class Products {
    @PostMapping("/products/{id}")
    fun update(@PathVariable id: Int, @RequestBody product: Product, @RequestHeader("Authenticate", defaultValue = "") header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.updateProduct(product)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/products/{id}")
    fun get(@PathVariable("id") id: Int) = DB.findProduct(id)

    @PostMapping("/products")
    fun create(@RequestBody newProduct: Product, @RequestHeader("Authenticate", defaultValue = "") header: String): ResponseEntity<Id> {
        validateAuthToken(header)

        DB.addProduct(newProduct)
        return ResponseEntity(Id(newProduct.id), HttpStatus.OK)
    }

    @DeleteMapping("/products/{id}")
    fun delete(@PathVariable("id") id: Int, @RequestHeader("Authenticate", defaultValue = "") header: String): ResponseEntity<String> {
        validateAuthToken(header)

        DB.deletePet(id)

        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/products")
    fun search(@RequestParam(name="name", required=false) name: String?,
               @RequestParam(name="type", required=false) type: String?,
               @RequestParam(name="status", required=false) status: String?): ResponseEntity<List<Product>> {

        // An exception thrown by some internal bug...
        if(name == "unknown")
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        val products = DB.findProducts(name, type, status)
        if(products.isEmpty())
            return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)

        return ResponseEntity(products, HttpStatus.OK)
   }
}
