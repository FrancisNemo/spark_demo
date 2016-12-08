/*ddl table job*/
create table job(id int, batchNo varchar(20)); 

/*procedure daily_job 插入作业批次，每分钟一次，共计1440*/
create procedure daily_job()
begin

end;


/*定时每天一次作业*/
create event `daily_job_event` on schedule every 1 day starts '2016-01-01 00:00:00' 
on completion not preserve enable 
do call daily_job;

