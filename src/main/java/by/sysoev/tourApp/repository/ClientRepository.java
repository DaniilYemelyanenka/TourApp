package by.sysoev.tourApp.repository;

import by.sysoev.tourApp.DTO.LastBookingUsersDTO;
import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.DTO.UpdateClientInfoDTO;

import java.util.List;

public interface ClientRepository {

    List<PaymentsStatsDTO> getClintPaymentsStats();

    List<LastBookingUsersDTO> getLastBookingUsers();

    void updateInfo(Long userID, UpdateClientInfoDTO dto);
}
