package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "passenger")
public class Passenger {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Mã thẻ căn cước không được để trống")
	@Pattern(regexp = "^[0-9]{10,13}$",message="Mã thẻ là kiểu số dương với độ dài từ 10 -13")
//	@Size(min = 10, max = 13, message = "Độ dài của mã thẻ là 10 - 13 kí tự")
	@Column(name = "id_card")
	private String id_card;

	@NotNull
	@Size(min = 5,max=100, message = "Độ dài tên 5 - 100 kí tự")
	@Pattern(regexp = "^((?![$&+,:;=\\?@#|/'<>.^*()%!-0123456789]).)*$",message="Họ tên không chứa kí tự số và kí tự đặc biệt")
	@Column(name = "name")
	private String name;

	@NotNull
	@Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/]\\d{4}",message="Ngày sinh hợp lệ theo định dạng dd/mm/yyyy")
	@Column(name = "birthday")
	private String birthday;

	@NotNull
	@Size(min=10,max = 200, message = "Độ dài địa chỉ 10- 200 kí tự")
	@Pattern(regexp = "^((?![$&+:;=\\?@#|/'<>.^*()%!]).)*$",message="Địa chỉ không chứa kí tự đặc biệt")
	@Column(name = "address")
	private String address;

	@NotNull(message = "Email không được để trống")
	@Email(message = "Email không đúng định dạng")
	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private int status;

	@Column(name = "student")
	private boolean student;

	@Column(name = "school")
	private String school;

	public Passenger(Integer id, String id_card, String name, String birthday, String address, String email, int status,
			boolean student, String school) {
		super();
		this.id = id;
		this.id_card = id_card;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
		this.status = status;
		this.student = student;
		this.school = school;
	}

	public Passenger() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean getStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	
}
