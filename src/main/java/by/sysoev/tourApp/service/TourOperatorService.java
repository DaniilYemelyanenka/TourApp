package by.sysoev.tourApp.service;

import by.sysoev.tourApp.DTO.TourOperatorStatsDTO;
import by.sysoev.tourApp.DTO.UpdateTourOperatorDTO;
import by.sysoev.tourApp.DTO.UpdateUserDTO;
import by.sysoev.tourApp.entity.User;
import by.sysoev.tourApp.repository.impl.TourOperatorRepositoryImpl;
import by.sysoev.tourApp.repository.impl.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourOperatorService {

    private final TourOperatorRepositoryImpl tourOperatorRepository;
    private final UserRepositoryImpl userRepository;

    public List<TourOperatorStatsDTO> getTourOperatorsStats(){
        return tourOperatorRepository.getTourOperatorsStats();
    }

    public void updateTourOperatorInfo(String email, UpdateTourOperatorDTO updateTourOperatorDTO){
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with this id not found"));
        tourOperatorRepository.updateTourOperator(user.getId(),updateTourOperatorDTO);
    }
}
