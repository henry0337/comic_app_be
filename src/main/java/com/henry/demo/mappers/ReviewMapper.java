package com.henry.demo.mappers;

import com.henry.demo.dto.ReviewDTO;
import com.henry.demo.dto.ReviewRequest;
import com.henry.demo.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "comicId", target = "comic.id")
    ReviewDTO modelToDTO(Review review);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "comic.id", target = "comicId")
    Review dtoToModel(ReviewDTO reviewDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Review requestToModel(ReviewRequest request);
}
