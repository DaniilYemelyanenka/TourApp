package by.sysoev.tourApp.repository.impl;

import by.sysoev.tourApp.entity.Hotel;
import by.sysoev.tourApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryImpl implements HotelRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Hotel> getAll() {

        String sql = "SELECT id,name,stars FROM hotels ";
        return jdbcTemplate.query(sql,(rs,rowNum) -> new Hotel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("stars")
        )) ;
    }
}
