package spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;

	public MemberRegisterService(/*MemberDao memberDao*/) {
//		this.memberDao = memberDao;
		//@Autowired를 쓰면 놀랍게도 이게 됩니다
		//주입도 알아서 해주는 놀라운 스프링의 세계 
	}

	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), 
				LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
