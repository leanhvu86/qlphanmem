package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@Controller
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String getAllEmployees(Model model, Pageable pageable) {
		Page<Employee> productPage = employeeService.findAll(pageable);
		PageWrapper<Employee> page = new PageWrapper<>(productPage, "/employees");
		model.addAttribute("employees", page.getContent());
		model.addAttribute("page", page);
		return "admin";
	}

	@RequestMapping(value = "/editEmployee/{id}", method = RequestMethod.GET)
	public String getEmployeeById(@PathVariable("id") Integer id, ModelMap modelMap) {
		Employee employee = employeeService.getEmployeeById(id);
		modelMap.addAttribute("employee", employee);
		return "employee";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String addEmployeeById(ModelMap modelMap) {
		modelMap.addAttribute("employee", new Employee());
		return "employee";
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public String updateEmployee(ModelMap modelMap, @Validated @ModelAttribute("employee") Employee employee,
			BindingResult bindingResult, Model model, Pageable pageable) {
		if (bindingResult.hasErrors()) {
			return "employee";
		} else {
			employeeService.saveEmployeee(employee);
			modelMap.addAttribute("message", "Lưu nhân viên thành công");
			Page<Employee> productPage = employeeService.findAll(pageable);
			PageWrapper<Employee> page = new PageWrapper<>(productPage, "/employees");
			page.isFirstPage();
			model.addAttribute("employees", page.getContent());
			model.addAttribute("page", page);
			return "admin";
		}
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
	public String deleteEmployeeById(@PathVariable("id") Integer id, ModelMap model, Pageable pageable) {

		if (employeeService.deleteEmployee(id)) {
			model.addAttribute("message", "Xóa nhân viên thành công");
			Page<Employee> productPage = employeeService.findAll(pageable);
			PageWrapper<Employee> page = new PageWrapper<>(productPage, "/employees");
			page.isFirstPage();
			model.addAttribute("employees", page.getContent());
			model.addAttribute("page", page);
			return "admin";
		} else {
			model.addAttribute("message", "Xóa nhân viên thất bại");
			Page<Employee> productPage = employeeService.findAll(pageable);
			PageWrapper<Employee> page = new PageWrapper<>(productPage, "/employees");
			page.isFirstPage();
			model.addAttribute("employees", page.getContent());
			model.addAttribute("page", page);
			return "admin";
		}

	}

	@RequestMapping("/exportExcel")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {

		try {

			String fileName = "F:/download/excelFile.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Danh Sách Nhân Viên");

			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("No.");
			rowhead.createCell(1).setCellValue("Name");
			rowhead.createCell(2).setCellValue("Address");
			rowhead.createCell(3).setCellValue("Email");

			HSSFRow row = sheet.createRow((short) 1);
			row.createCell(0).setCellValue("1");
			row.createCell(1).setCellValue("Carlos");
			row.createCell(2).setCellValue("Costa Rica");
			row.createCell(3).setCellValue("myNameh@gmail.com");

			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated!");

			// Code to download
			File fileToDownload = new File(fileName);
			InputStream in = new FileInputStream(fileToDownload);

			// Gets MIME type of the file
			String mimeType = new MimetypesFileTypeMap().getContentType(fileName);

			if (mimeType == null) {
				// Set to binary type if MIME mapping not found
				mimeType = "application/octet-stream";
			}
			System.out.println("MIME type: " + mimeType);

			// Modifies response
			response.setContentType(mimeType);
			response.setContentLength((int) fileToDownload.length());

			// Forces download
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileToDownload.getName());
			response.setHeader(headerKey, headerValue);

			// obtains response's output stream
			OutputStream outStream = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = in.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			in.close();
			outStream.close();

			System.out.println("File downloaded at client successfully");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(Model model) {
       
        return "upload";
    }
}
