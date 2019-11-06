package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@NotNull
	@Column(name = "titles")
	@Size(min = 10, max = 200, message = "Độ dài của tiêu đề  là 10 - 200 kí tự")
	private String titles;

	@NotNull
	@Size(min = 10, max = 1000, message = "Độ dài của nội dung  là 10 - 1000 kí tự")
	@Column(name = "content")
	private String content;

	@NotNull
//    @Size(min = 1, max =200, message = "Độ dài của file ảnh là 1 - 200 kí tự")
	@Column(name = "image")
	private String image;

	@NotNull
	@Column(name = "status")
	private boolean status;

	@NotNull
	@Column(name = "type")
	private int type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private LoginUser loginUser;

	public News() {
		super();
	}

	public News(String titles, String content, String image, boolean status, int type) {
		this.titles = titles;
		this.content = content;
		this.image = image;
		this.status = status;
		this.type = type;
	}

	public News(String titles, String content, String image, boolean status, int type, LoginUser loginUser) {
		this.titles = titles;
		this.content = content;
		this.image = image;
		this.status = status;
		this.type = type;
		this.loginUser = loginUser;
	}

	public boolean isStatus() {
		return status;
	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
