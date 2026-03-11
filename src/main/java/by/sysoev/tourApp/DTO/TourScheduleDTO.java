package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class TourScheduleDTO {
    private Long id;
    private Date stratDate;
    private Date endDate;
    private Integer capacity;
    private Double price;
}
