package org.conan.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO2 {

	private Long bno;
	private String title;
	private String sub_title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;


	public BoardVO2() {
		// TODO Auto-generated constructor stub
	}

	public BoardVO2(Long bno, String title, String sub_title, String content, String writer, Date regdate, Date updateDate) {
		super();
		this.bno = bno;
		this.title = title;
		this.sub_title= sub_title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.updateDate = updateDate;
	}

}
