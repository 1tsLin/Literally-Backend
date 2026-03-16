package com.literally.backend.repositories;

import com.literally.backend.dtos.ProductCatalogDTO;
import com.literally.backend.entities.ProductLocalization;
import com.literally.backend.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductLocalizationRepository extends JpaRepository<ProductLocalization, UUID> {

    ProductLocalization findByProductIdAndLanguage(UUID productId, LanguageEnum language);

    @Query("""
                select new com.literally.backend.dtos.ProductCatalogDTO(
                    product.id,
                    product.price,
                    product.quantity,
                    localization.name,
                    contributor.name
                )
                from ProductLocalization localization
                join localization.product product
                join Contributor contributor on contributor.id = product.author.id
                where localization.language = :language
                  and contributor.category = com.literally.backend.enums.ContributorCategoryEnum.AUTHOR
            """)
    List<ProductCatalogDTO> findCatalogByLanguage(@Param("language") LanguageEnum language);
}
