package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.LastBookingUsersDTO;
import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.DTO.UpdateClientInfoDTO;
import by.sysoev.tourApp.entity.User;
import by.sysoev.tourApp.repository.impl.ClientRepositoryImpl;
import by.sysoev.tourApp.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositoryImpl clientRepository;

    private final UserRepositoryImpl userRepository;

    public List<PaymentsStatsDTO> getClientsPaymentsStats(){
        return clientRepository.getClintPaymentsStats();
    }

    public List<LastBookingUsersDTO> getLastBookingUsers(){
        return clientRepository.getLastBookingUsers();
    }

    public void updateClientInfo(String email, UpdateClientInfoDTO dto){
       User user = userRepository.getUserByEmail(email).get();
       clientRepository.updateInfo(user.getId(),dto);
    }
}
