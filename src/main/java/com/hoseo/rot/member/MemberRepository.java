package com.hoseo.rot.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hoseo.rot.admin.Product;

@Mapper
public interface MemberRepository {

	
	public int addMember(Member m); //회원가입
	
	public int idCheck(String id); // 아이디 중복 체크
	
	public int emailCheck(String email); // 이메일 중복 체크

	public int nicknameCheck(String nickname); // 이메일 중복 체크
	
	public Member getMember(Member m); // 회원 불러오기
    
    public Member getUser(Member m); // 회원가입 완료한 회원정보 불러오기
    
    public Member getMypage(String id);
    
    public Member findId(Member m); // 아이디 찾기
    
	public List<Member> getMemberTest();
	
	public int updateUser(Member m);
	
	public int updateUserProfileImg(Member m);
	
    
}
