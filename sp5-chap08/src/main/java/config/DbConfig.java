package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() { //dataSource객체를 생성한다. 
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver"); // jdbc드라이버 클래스를 mysq 드라이버 클래스로 사용한다. 
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(1000* 60* 3); // 최소 유효시간을 3분으로 설정
		ds.setTimeBetweenEvictionRunsMillis(1000*10); // 10초주기 
		
		return ds;
	}

}
