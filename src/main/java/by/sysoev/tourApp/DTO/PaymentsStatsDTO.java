package by.sysoev.tourApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentsStatsDTO {
    private String first_name;
    private String last_name;
    private String passport_number;
    private Long totalSpend;
}
