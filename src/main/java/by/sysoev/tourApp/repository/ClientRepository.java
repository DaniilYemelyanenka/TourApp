package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.PaymentsStatsDTO;

import java.util.List;

public interface ClientRepository {

    List<PaymentsStatsDTO> getClintPaymentsStats();
}
