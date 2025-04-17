package es.codeurjc.web.dto.review;

public record CreateReviewDTO(
                String title,
                String text,
                Long movieId,
                Long authorId) {
}
