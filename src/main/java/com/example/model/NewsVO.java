package com.example.model;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NewsVO implements Serializable {

	private Integer id;

	private MultipartFile image;
	 @Size(min = 10, max =200, message = "Độ dài của tiêu đề  là 10 - 200 kí tự")
	private String titles;
	 @Size(min = 10, max =1000, message = "Độ dài của nội dung  là 10 - 1000 kí tự")
	private String content;
	 @AssertTrue(message="Bạn phải chọn trạng thái là check")
	private boolean status;

	private int type;

	public NewsVO() {
	}

	public NewsVO(Integer id,  MultipartFile image, String titles, String content, boolean status, int type) {
		this.id = id;
		this.image = image;
		this.titles = titles;
		this.content = content;
		this.status = status;
		this.type = type;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}