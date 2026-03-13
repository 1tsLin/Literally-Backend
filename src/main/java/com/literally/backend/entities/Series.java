package com.literally.backend.entities;

import com.literally.backend.enums.BookAudienceEnum;
import com.literally.backend.enums.BookFormatEnum;
import com.literally.backend.enums.BookGenreEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "series")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private List<String> alias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BookFormatEnum format;

    @Enumerated(EnumType.STRING)
    private BookAudienceEnum audience;

    @Enumerated(EnumType.STRING)
    private List<BookGenreEnum> genres = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Contributor author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private Contributor editor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "illustrator_id")
    private Contributor illustrator;

}
