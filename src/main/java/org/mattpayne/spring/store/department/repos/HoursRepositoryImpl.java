package org.mattpayne.spring.store.department.repos;

import org.mattpayne.spring.store.department.model.HoursReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

public class HoursRepositoryImpl implements HoursRepository {
    @Autowired
    private DataSource dataSource;

    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstructor() {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<HoursReportDTO> getHoursReport() {
        String query = "SELECT p.id, p.last_name, p.first_name, d.name, "
                + "sum(((datediff('ms', '1970-01-01', wl.stop_time) - datediff('ms', '1970-01-01', wl.start_time))/1000)/(60*60)) "
                + "as total_hours "
                + "from "
                + "WORK_LOG as wl, "
                + "people as p, "
                + "PEOPLE_WORK_IN_DEPARTMENTS as pwd, "
                + "department  as d "
                + "where pwd.department_id = d.id and "
                + "p.id = pwd.people_id and "
                + "wl.when_people_worked_id = p.id "
                + "group by p.id "
                + "order by total_hours desc; ";

        // https://automagical2012.wordpress.com/2015/06/24/spring-data-with-jpa-customise-repository/
        List<HoursReportDTO> listHoursReportDto = jdbcTemplate.query(query, new BeanPropertyRowMapper<HoursReportDTO>());
        return listHoursReportDto;
    }
}
