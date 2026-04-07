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

    List<ProductLocalization> findAllByProductId(UUID productId);

    @Query("""
        select
            product.id,
            product.price,
            localization.name,
            contributor.name,
            coalesce(avg(review.grade), 0.0),
            count(distinct review.id),
            media.id
        from ProductLocalization localization
        join localization.product product
        join Contributor contributor on contributor.id = product.author.id
        left join Review review on review.product.id = product.id
        left join Media media
            on media.entityId = product.id
            and media.category = com.literally.backend.enums.MediaCategoryEnum.PRODUCT_COVER
        where localization.language = :language
          and contributor.category = com.literally.backend.enums.ContributorCategoryEnum.AUTHOR
        group by
            product.id,
            product.price,
            localization.name,
            contributor.name,
            media.id
    """)
    List<Object[]> findCatalogByLanguage(@Param("language") LanguageEnum language);

//    @Query("""
//    select new com.literally.backend.dtos.ProductCatalogDTO(
//        product.id,
//        product.price,
//        localization.name,
//        contributor.name,
//        coalesce(avg(review.grade), 0.0),
//        count(distinct review.id),
//        case when exists (
//            select 1 from favorites f
//            where f.product.id = product.id
//            and f.user.id = :userId
//        ) then true else false end,
//        (
//            select m.id from Media m
//            where m.entityId = product.id
//            and m.category = com.literally.backend.enums.MediaCategoryEnum.PRODUCT_COVER
//        )
//    )
//    from ProductLocalization localization
//    join localization.product product
//    join Contributor contributor on contributor.id = product.author.id
//    left join Review review on review.product.id = product.id
//    where localization.language = :language
//      and contributor.category = com.literally.backend.enums.ContributorCategoryEnum.AUTHOR
//    group by
//        product.id,
//        product.price,
//        localization.name,
//        contributor.name
//""")
//    List<ProductCatalogDTO> findCatalogByLanguageAndUser(
//            @Param("language") LanguageEnum language,
//            @Param("userId") UUID userId
//    );
}
