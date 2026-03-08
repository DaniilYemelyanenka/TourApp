package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.LastBookingUsersDTO;
import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.repository.impl.ClientRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositoryImpl clientRepository;

    public List<PaymentsStatsDTO> getClientsPaymentsStats(){
        return clientRepository.getClintPaymentsStats();
    }

    public List<LastBookingUsersDTO> getLastBookingUsers(){
        return clientRepository.getLastBookingUsers();
    }

}
