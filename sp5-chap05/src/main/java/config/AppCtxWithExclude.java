package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@ComponentScan(basePackages= {"spring"}, excludeFilters=@Filter(type=FilterType.REGEX, pattern = "spring\\..*Dao"))
public class AppCtxWithExclude {
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}

}
