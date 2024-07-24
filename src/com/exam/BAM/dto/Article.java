package com.exam.BAM.dto;

public class Article {
	
	private int id;
	private String regDate;
	private String title;
	private String body;
	private String memberId;
	private int veiwCnt;

	public Article(int id, String regDate, String title, String body, String memberId, int veiwCnt) {

		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.veiwCnt = veiwCnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getVeiwCnt() {
		return veiwCnt;
	}

	public void setVeiwCnt(int veiwCnt) {
		this.veiwCnt = veiwCnt;
	}

	public void increaseViewCnt() {
		this.veiwCnt++;
	}
}