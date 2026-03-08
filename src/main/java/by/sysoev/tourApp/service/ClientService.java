package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.PaymentsStatsDTO;
import by.sysoev.tourApp.repository.impl.ClientServiceRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientServiceRepositoryImpl clientServiceRepository;

    public List<PaymentsStatsDTO> getClientsPaymentsStats(){
        return clientServiceRepository.getClintPaymentsStats();
    }}
