package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employe_id")
	private Integer employeeId;

	@NotNull
	@Column(name="name")
    @Size(min = 2, max = 100, message = "Độ dài của tên là 2 - 100 kí tự")
	@Pattern(regexp = "^((?![$&+,:;=\\?@#|/'<>.^*()%!-0123456789]).)*$",message="Họ tên không chứa kí tự số và kí tự đặc biệt")
	private String name;

	@NotNull
	@Column(name="last_name")
    @Size(min = 2, max = 100, message = "Độ dài của tên  là 2 - 100 kí tự")
	@Pattern(regexp = "^((?![$&+,:;=\\?@#|/'<>.^*()%!-0123456789]).)*$",message="Họ tên không chứa kí tự số và kí tự đặc biệt")
	private String lastName;

	@NotNull
	@Column(name="email")
	@Email(message="Email không đúng định dạng")
	private String email;

	@NotNull
	@Column(name="phone")
	@Pattern(regexp = "(09|01[2|6|8|9])+([0-9]{8})\\b",message="Số điện thoại không hợp lệ")
    @Size(min = 10, max = 15, message = "Độ dài của phone là 10 - 15 kí tự")
	private String phone;

	
	@Column(name="active")
	private boolean active;
	@Column(name="description")
	private String description;
	
	public Employee() {
		super();
	}
	
	public Employee(String name, String lastName, String email, String phone, boolean active) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.active = active;
	}
	
	public Integer getId() {
		return employeeId;
	}
	public void setId(Integer id) {
		this.employeeId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
