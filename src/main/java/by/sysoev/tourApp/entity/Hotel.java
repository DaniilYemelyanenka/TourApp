package by.sysoev.tourApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hotel {
    private long id;
    private String name;
    private Integer stars;
}
