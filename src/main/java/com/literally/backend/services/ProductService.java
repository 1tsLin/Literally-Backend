package com.literally.backend.services;

import com.literally.backend.dtos.ProductCatalogDTO;
import com.literally.backend.dtos.ProductDTO;
import com.literally.backend.dtos.ProductLocalizationDTO;
import com.literally.backend.entities.Product;
import com.literally.backend.entities.ProductLocalization;
import com.literally.backend.enums.LanguageEnum;
import com.literally.backend.enums.MediaCategoryEnum;
import com.literally.backend.filters.CatalogFilter;
import com.literally.backend.mappers.ProductLocalizationMapper;
import com.literally.backend.mappers.ProductMapper;
import com.literally.backend.repositories.ProductCatalogRepository;
import com.literally.backend.repositories.ProductLocalizationRepository;
import com.literally.backend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductLocalizationRepository productLocalizationRepository;
    private final ProductMapper productMapper;
    private final ProductLocalizationMapper productLocalizationMapper;
    private final MediaService mediaService;
    private final ProductCatalogRepository productCatalogRepository;

    /*------------------------------------------------------------------------------------------------------------------
                                                Product CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public ProductDTO getById(UUID productId) {
        ProductDTO product = productMapper.mapToDto(getProductById(productId));
        product.setLocalizations(getLocalizations(product.getId()));
        product.setMedias(mediaService.getByEntity(productId));
        return product;
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
    }

    @Transactional
    public ProductDTO create(ProductDTO dto, MultipartFile coverImage, MultipartFile backImage) throws IOException {
        if (dto == null)
            throw new IllegalArgumentException("Product create dto is null");

        System.out.println(dto);

        Product product = productRepository.save(productMapper.mapToEntity(dto));

        /*-- Create product localizations --*/
        createLocalizations(product, dto.getLocalizations());

        /*-- Create product medias --*/
        if(coverImage != null && !coverImage.isEmpty()) mediaService.create(coverImage, product.getId(), MediaCategoryEnum.PRODUCT_COVER);
        if(backImage != null && !backImage.isEmpty()) mediaService.create(backImage, product.getId(), MediaCategoryEnum.PRODUCT_BACK);

        return productMapper.mapToDto(product);
    }

    @Transactional
    public ProductDTO update(UUID productId, ProductDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Product update dto is null");

        if (!productRepository.existsById(productId))
            throw new RuntimeException("Product not found : " + productId);

        Product product = getProductById(productId);
        productMapper.updateFromDto(dto, product);
        productRepository.save(product);

        updateLocalizations(productId, dto.getLocalizations());

        ProductDTO productDTO = productMapper.mapToDto(product);
        productDTO.setLocalizations(getLocalizations(product.getId()));
        productDTO.setMedias(mediaService.getByEntity(productId));
        return productDTO;
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

    public List<ProductLocalizationDTO> getLocalizations(UUID productId){
        List<ProductLocalization> localizations = productLocalizationRepository.findAllByProductId(productId);
        if(localizations == null){
            throw new RuntimeException("Product " + productId + " localization not found");
        }
        return localizations.stream().map(productLocalizationMapper::mapToDto).collect(Collectors.toList());
    }

    public ProductLocalizationDTO getLocalization(UUID productId, LanguageEnum language) {
        ProductLocalization localization = productLocalizationRepository.findByProductIdAndLanguage(productId, language);
        if (localization == null)
            throw new RuntimeException("Product " + productId + " localization " + language + " not found");
        return productLocalizationMapper.mapToDto(localization);
    }

    @Transactional
    public void createLocalizations(Product product, List<ProductLocalizationDTO> localizations) {
        for (ProductLocalizationDTO productLocalization : localizations) {
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
    public void updateLocalizations(UUID productId, List<ProductLocalizationDTO> dtos){
        List<ProductLocalization> localizations = productLocalizationRepository.findAllByProductId(productId);
        if(localizations == null){
            throw new RuntimeException("Product " + productId + " localization not found");
        }

        List<ProductLocalizationDTO> result = new ArrayList<>();

        for (ProductLocalizationDTO dto : dtos) {
            ProductLocalization localization = localizations.stream()
                    .filter(l -> l.getLanguage() == dto.getLanguage())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "No localization found for language " + dto.getLanguage()
                    ));

            result.add(updateLocalization(localization, dto));
        }
    }

    @Transactional
    public ProductLocalizationDTO updateLocalization(ProductLocalization localization, ProductLocalizationDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Product localization update dto is null");

        if (localization == null)
            throw new RuntimeException("Product localization is null");

        productLocalizationMapper.updateFromDto(dto, localization);
        productLocalizationRepository.save(localization);

        return productLocalizationMapper.mapToDto(localization);
    }

    @Transactional
    public ProductLocalizationDTO updateLocalization(UUID localizationId, ProductLocalizationDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Product localization update dto is null");

        ProductLocalization localization = productLocalizationRepository.findById(localizationId)
                .orElseThrow(() -> new RuntimeException("Product localization not found: " + localizationId));

        productLocalizationMapper.updateFromDto(dto, localization);
        productLocalizationRepository.save(localization);

        return productLocalizationMapper.mapToDto(localization);
    }

    /*------------------------------------------------------------------------------------------------------------------
                                              Product localization CRUD operations
    ------------------------------------------------------------------------------------------------------------------*/

    public List<ProductCatalogDTO> getCatalogByLanguage(LanguageEnum language, CatalogFilter filters) {
        if (language == null)
            throw new IllegalArgumentException("Language is null while fetching catalog");

        // TODO : Add catalog filters
        return productCatalogRepository.getCatalog(language, filters);
    }
}
