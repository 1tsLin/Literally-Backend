package com.literally.backend.services;

import com.literally.backend.dtos.ProductCatalogDTO;
import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.entities.Media;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.ProductLocalization;
import com.literally.backend.enums.LanguageEnum;
import com.literally.backend.enums.MediaCategoryEnum;
import com.literally.backend.mappers.ProductLocalizationMapper;
import com.literally.backend.mappers.ProductMapper;
import com.literally.backend.repositories.ProductLocalizationRepository;
import com.literally.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductLocalizationRepository productLocalizationRepository;
    private final ProductMapper productMapper;
    private final ProductLocalizationMapper productLocalizationMapper;
    private final MediaService mediaService;

    /*------------------------------------------------------------------------------------------------------------------
                                                Product CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public ProductDTO getById(UUID productId) {
        return productMapper.mapToDto(getProductById(productId));
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
    }

    @Transactional
    public ProductDTO create(ProductDTO dto, MultipartFile coverImage, MultipartFile backImage) {
        if (dto == null)
            throw new IllegalArgumentException("Product create dto is null");

        Product product = productRepository.save(productMapper.mapToEntity(dto));

        /*-- Create product localizations --*/
        createLocalizations(product);

        /*-- Create product medias --*/
         mediaService.create(coverImage, product.getId(), MediaCategoryEnum.PRODUCT_COVER);
         mediaService.create(backImage, product.getId(), MediaCategoryEnum.PRODUCT_BACK);

        return productMapper.mapToDto(product);
    }

    @Transactional
    public ProductDTO update(UUID productId, ProductDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Product update dto is null");

        if (!productRepository.existsById(productId))
            throw new RuntimeException("Product not found : " + productId);

        Product product = productMapper.mapToEntity(dto);
        product.setId(productId);

        productRepository.save(product);

        return productMapper.mapToDto(product);
    }

    @Transactional
    public void delete(UUID productId) {
        if (!productRepository.existsById(productId))
            throw new RuntimeException("Product not found : " + productId);

        productRepository.deleteById(productId);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                              Product localization CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public ProductLocalizationDTO getLocalization(UUID productId, LanguageEnum language) {
        ProductLocalization localization = productLocalizationRepository.findByProductIdAndLanguage(productId, language);
        if (localization == null)
            throw new RuntimeException("Product " + productId + " localization " + language + " not found");
        return productLocalizationMapper.mapToDto(localization);
    }

    @Transactional
    public void createLocalizations(Product product) {
        for (ProductLocalization productLocalization : product.getLocalizations()) {
            ProductLocalization localization = ProductLocalization.builder()
                    .product(product)
                    .language(productLocalization.getLanguage())
                    .name(productLocalization.getName())
                    .description(productLocalization.getDescription())
                    .isActive(true)
                    .build();
            productLocalizationRepository.save(localization);
        }
    }

    @Transactional
    public ProductLocalizationDTO updateLocalization(UUID localizationId, ProductLocalizationDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Product localization update dto is null");

        if (!productLocalizationRepository.existsById(localizationId))
            throw new RuntimeException("Product localization not found : " + localizationId);

        ProductLocalization localization = productLocalizationMapper.mapToEntity(dto);
        localization.setId(localizationId);

        productLocalizationRepository.save(localization);

        return productLocalizationMapper.mapToDto(localization);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                              Product localization CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public List<ProductCatalogDTO> getCatalogByLanguage(LanguageEnum language) {
        if (language == null)
            throw new IllegalArgumentException("Language is null while fetching catalog");

        // TODO : Add catalog filters
        return productLocalizationRepository.findCatalogByLanguage(language);
    }
}
