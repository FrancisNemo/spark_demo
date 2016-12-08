a demo for spark daily job
1 github上面建立仓库sparkdemo
2 maven建立本地项目
mvn archetype:generate -DgroupId=com.mycompany.sparkdemo -DartifactId=sparkdemo -Dpackage=com.mycompany.sparkdemo -Dversion=1.0-SNAPSHOT

均选择默认配置，生成demo.

打包测试
mvn package

运行
java -cp target/sparkdemo-1.0-SNAPSHOT.jar com.mycompany.sparkdemo.App

3 git客户端建立本地仓库。git init
设置追踪远程仓库 git remote add origin <url>

Q1:mysql中已经建立的job没有定时执行?

A1:查看job是否开启了自动执行
  MySQL> show global variables like '%event_scheduler%';

  开启job自动执行
  set global event_scheduler = on;  
