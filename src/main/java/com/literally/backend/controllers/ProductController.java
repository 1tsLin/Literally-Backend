package com.literally.backend.controllers;

import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.enums.LanguageEnum;
import com.literally.backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /*------------------------------------------------------------------------------------------------------------------
                                                 Product CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{productId}")
    public ProductDTO getProduct(@PathVariable UUID productId) {
        return productService.getById(productId);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO dto) {
        return productService.create(dto);
    }

    @PutMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable UUID productId, @RequestBody ProductDTO dto) {
        return productService.update(productId, dto);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) {
        productService.delete(productId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                          Product Localization CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/localization/{productId}")
    public ProductLocalizationDTO getProductLocalization(@PathVariable UUID productId,
                                                         @RequestParam("language") LanguageEnum language) {
        return productService.getLocalization(productId, language);
    }

    @PutMapping("/localization/{localizationId}")
    public ProductLocalizationDTO updateProductLocalization(@PathVariable UUID localizationId,
                                                            @RequestBody ProductLocalizationDTO dto) {
        return productService.updateLocalization(localizationId, dto);
    }
}
