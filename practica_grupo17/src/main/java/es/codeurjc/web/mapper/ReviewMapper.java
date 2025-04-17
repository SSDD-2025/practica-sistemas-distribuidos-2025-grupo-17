package es.codeurjc.web.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import es.codeurjc.web.dto.review.CreateReviewDTO;
import es.codeurjc.web.dto.review.ReviewBasicDTO;
import es.codeurjc.web.dto.review.ReviewDTO;
import es.codeurjc.web.entities.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO toDTO(Review review);

    List<ReviewDTO> toDTOs(Collection<Review> reviews);

    Review toDomain(ReviewDTO reviewDTO);

    List<Review> toDomain(List<ReviewBasicDTO> reviewDTO);

    List<ReviewDTO> toDTO(List<Review> all);

    Review toDomain(CreateReviewDTO review);

    CreateReviewDTO toCreateReviewRequest(Review review);

    ReviewBasicDTO toReviewBasicDTO(ReviewDTO review);

}