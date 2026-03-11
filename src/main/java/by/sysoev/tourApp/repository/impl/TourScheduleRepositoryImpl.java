package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.DTO.TourScheduleDTO;
import by.sysoev.tourApp.repository.TourScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TourScheduleRepositoryImpl implements TourScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TourScheduleDTO> getTourSchedulesById(Long id) {
        String sql = "SELECT id,star_date,end_date,capacity,price FROM tour_schedule WHERE tour_id = ?";
        return jdbcTemplate.query(sql,(rs,rowNum) -> new TourScheduleDTO(
                rs.getLong("id"),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getInt("capacity"),
                rs.getDouble("price")
        ));
    }

    @Override
    public void updateTourSchedule(TourScheduleDTO dto) {
        String sql = """
               UPDATE tour_schedule SET start_date = ?, end_date = ?,capacity = ?,price = ? WHERE id = ?
               """;
        jdbcTemplate.update(sql,
                dto.getStratDate(),
                dto.getEndDate(),
                dto.getCapacity(),
                dto.getPrice(),
                dto.getId());
    }
}
