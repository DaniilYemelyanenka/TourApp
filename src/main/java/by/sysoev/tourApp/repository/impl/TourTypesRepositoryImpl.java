package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.entity.TourType;
import by.sysoev.tourApp.repository.TourTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TourTypesRepositoryImpl implements TourTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<TourType> getAll() {
        String sql = "SELECT * FROM tour_types";
        return jdbcTemplate.query(sql,(rs,rowNum) -> new TourType(
                rs.getInt("id"),
                rs.getString("type")
        ));
    }
}
