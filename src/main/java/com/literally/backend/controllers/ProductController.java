package com.literally.backend.controllers;

import com.literally.backend.dtos.ProductCatalogDTO;
import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.enums.LanguageEnum;
import com.literally.backend.filters.CatalogFilter;
import com.literally.backend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /*------------------------------------------------------------------------------------------------------------------
                                                   Get catalog products
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping()
    public List<ProductCatalogDTO> getCatalog(@RequestParam("language") LanguageEnum language, CatalogFilter filters) {
        return productService.getCatalogByLanguage(language, filters);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                                 Product CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    @GetMapping("/{productId}")
    public ProductDTO getProduct(@PathVariable UUID productId) {
        return productService.getById(productId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO createProduct(@RequestPart("dto") ProductDTO dto,
                                    @RequestPart(value = "coverImage", required = false) MultipartFile coverImage,
                                    @RequestPart(value = "backImage", required = false) MultipartFile backImage) throws IOException {
        return productService.create(dto, coverImage, backImage);
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
