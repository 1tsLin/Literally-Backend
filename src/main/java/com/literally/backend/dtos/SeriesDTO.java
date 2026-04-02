package com.literally.backend.dtos;

import com.literally.backend.entities.Contributor;
import com.literally.backend.enums.BookAudienceEnum;
import com.literally.backend.enums.BookFormatEnum;
import com.literally.backend.enums.BookGenreEnum;
import lombok.*;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeriesDTO {
    private UUID id;

    private List<String> alias = new ArrayList<>();
    private BookFormatEnum format;
    private BookAudienceEnum audience;
    private List<BookGenreEnum> genres = new ArrayList<>();

    private UUID authorId;
    private UUID editorId;
    private UUID illustratorId;
}
