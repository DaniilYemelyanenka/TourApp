package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TourReviewDTO {
    private String tourName;
    private Integer reviewsCount;
    private Double avgRating;
}
