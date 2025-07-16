package com.example.fstest.controller

import com.example.fstest.entity.ProductCreateRequest
import com.example.fstest.service.ProductServiceInterface
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.math.BigDecimal

@Controller
class ProductController(private val productService: ProductServiceInterface) {
    @GetMapping("/")
    fun index(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        model: Model
    ): String {
        val productPage = productService.listAll(page, size)
        model.addAttribute("products", productPage.content)
        model.addAttribute("currentPage", page)
        model.addAttribute("totalPages", productPage.totalPages)
        return "index"
    }

    @PostMapping("/products")
    fun createFromForm(
        @RequestParam title: String,
        @RequestParam price: BigDecimal,
        @RequestParam(required = false) vendor: String?,
        @RequestParam(required = false) handle: String?,
        @RequestParam(required = false, name = "body_html") bodyHtml: String?,
        redirectAttributes: RedirectAttributes
    ): String {
        val request = ProductCreateRequest(
            title = title,
            price = price,
            vendor = vendor,
            handle = handle,
            bodyHtml = bodyHtml,
            variants = emptyList()
        )
        productService.create(request)
        redirectAttributes.addFlashAttribute("message", "Product added successfully")
        return "redirect:/"
    }

    @GetMapping("/products/partial")
    fun productsPartial(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        model: Model
    ): String {
        val productPage = productService.listAll(page, size)
        model.addAttribute("products", productPage.content)
        model.addAttribute("currentPage", page)
        model.addAttribute("totalPages", productPage.totalPages)
        return "fragments/products-table :: tableBody"
    }

}