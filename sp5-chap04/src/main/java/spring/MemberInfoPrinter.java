package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member==null) {
			System.out.println("데이터가 없네요");
			return;
		}
		printer.print(member);
		System.out.println();
	}

	@Autowired
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}

	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
	
}
