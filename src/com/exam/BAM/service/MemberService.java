package com.exam.BAM.service;

import com.exam.BAM.dao.MemberDao;
import com.exam.BAM.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		this.memberDao = new MemberDao();
	}
	
	public void joinMember(String loginId, String loginPw, String name) {
		memberDao.joinMember(loginId, loginPw, name);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}
}