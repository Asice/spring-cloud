**一.分布式服务注册中心中心**<br/>
    1.maven install 主目录pom.xml<br/>
    2.maven install server-center里面pom.xml，打出jar：server-center-0.0.1-SNAPSHOT.jar<br/>
    3.启动server-center peer1，java -jvm启动参数 -jar <path>/server-center-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1 #读取配置文件application-peer1.yml<br/>
    3.启动server-center peer2，java -jvm启动参数 -jar <path>/server-center-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2 #读取配置文件application-peer2.yml<br/>
