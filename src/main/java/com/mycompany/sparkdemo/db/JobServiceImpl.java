package com.mycompany.sparkdemo.db;

import com.mycompany.sparkdemo.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Franc on 2016/12/18.
 */
public class JobServiceImpl implements JobService {

    private JdbcTemplate jdbcTemplate;

    /*设置数据源DataSource到JdbcTemplate; 程序读取配置文件，加载的时候完成初始化*/
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Job> listJobs() {

        return this.jdbcTemplate.query("select * from job", new RowMapper<Job>() {
            @Override
            public Job mapRow(ResultSet resultSet, int i) throws SQLException {
                Job job =  new Job();
                job.setId(resultSet.getString("id"));
                job.setName(resultSet.getString("name"));
                job.setStatus(resultSet.getString("status"));
                job.setStart_time(resultSet.getString("starttime"));
                job.setEnd_time(resultSet.getString("endtime"));
                return job;
            }
        });
    }

    @Override
    public void save(Job job) {
        this.jdbcTemplate.update("insert into job(id,name,status,starttime,endtime) values(?,?,?,?,?)", job.getId(), job.getName(), job.getStatus(), job.getStart_time(), job.getEnd_time());
    }

    @Override
    public void update(Job job) {
        //TODO
        this.jdbcTemplate.execute("update job set name='test' where id=1" );
    }

    @Override
    public Job getJob() {
        //TODO
        return null;
    }
}
