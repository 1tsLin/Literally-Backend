package com.literally.backend.repositories;

import com.literally.backend.dtos.ProductCatalogDTO;
import com.literally.backend.enums.BookGenreEnum;
import com.literally.backend.enums.LanguageEnum;
import com.literally.backend.filters.CatalogFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductCatalogRepository {

    private final EntityManager em;

    public List<ProductCatalogDTO> getCatalog(LanguageEnum language, CatalogFilter filters) {
        StringBuilder jpql = new StringBuilder("""
            select new com.literally.backend.dtos.ProductCatalogDTO(
                product.id,
                product.price,
                localization.name,
                contributor.name,
                coalesce(avg(review.grade), 0.0),
                count(distinct review.id),
                false,
                media.id
            )
            from ProductLocalization localization
            join localization.product product
            join Contributor contributor on contributor.id = product.author.id
            left join Contributor illustrator on illustrator.id = product.illustrator.id
            left join Contributor editor on editor.id = product.editor.id
            left join Review review on review.product.id = product.id
            left join Media media
                on media.entityId = product.id
                and media.category = com.literally.backend.enums.MediaCategoryEnum.PRODUCT_COVER
            where localization.language = :language
              and contributor.category = com.literally.backend.enums.ContributorCategoryEnum.AUTHOR
        """);

        Map<String, Object> params = new HashMap<>();
        params.put("language", language);

        if (filters.getTitle() != null) {
            jpql.append(" and lower(localization.name) like lower(concat('%', :title, '%'))");
            params.put("title", filters.getTitle());
        }
        if (filters.getPrice() != null) {
            jpql.append(" and product.price <= :maxPrice");
            params.put("maxPrice", filters.getPrice());
        }
        if (filters.getAuthorId() != null) {
            jpql.append(" and product.author.id = :authorId");
            params.put("authorId", filters.getAuthorId());
        }
        if (filters.getIllustratorId() != null) {
            jpql.append(" and product.illustrator.id = :illustratorId");
            params.put("illustratorId", filters.getIllustratorId());
        }
        if (filters.getEditorId() != null) {
            jpql.append(" and product.editor.id = :editorId");
            params.put("editorId", filters.getEditorId());
        }
        if (filters.getSeriesId() != null) {
            jpql.append(" and product.series.id = :seriesId");
            params.put("seriesId", filters.getSeriesId());
        }
        if (filters.getFormats() != null && filters.getFormats().length > 0) {
            jpql.append(" and product.format in :formats");
            params.put("formats", List.of(filters.getFormats()));
        }
        if (filters.getGenres() != null && filters.getGenres().length > 0) {
            jpql.append(" and product.genres in :genres");
            params.put("genres", Arrays.asList(filters.getGenres()));
        }
        if (filters.getAudiences() != null && filters.getAudiences().length > 0) {
            jpql.append(" and product.audience in :audiences");
            params.put("audiences", List.of(filters.getAudiences()));
        }

        jpql.append("""
             group by
                product.id,
                product.price,
                localization.name,
                contributor.name,
                media.id
        """);

        TypedQuery<ProductCatalogDTO> query = em.createQuery(jpql.toString(), ProductCatalogDTO.class);
        params.forEach(query::setParameter);

        return query.getResultList();
    }
}