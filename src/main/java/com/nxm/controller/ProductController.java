package com.nxm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nxm.model.Brand;
import com.nxm.model.PalletPosition;
import com.nxm.model.Product;
import com.nxm.model.ProductType;
import com.nxm.model.StockTotalDetail;
import com.nxm.repository.BrandRepositoty;
import com.nxm.repository.PalletPoisitionRepository;
import com.nxm.repository.ProductRepository;
import com.nxm.repository.ProductTypeRepository;
import com.nxm.repository.StockTotalDetailRepository;
import com.nxm.service.BrandService;
import com.nxm.service.ProductService;
import com.nxm.service.ProductTypeService;
import com.nxm.service.StockTotalDetailService;

import com.nxm.model.Product;
import com.nxm.service.BrandService;

@Controller
public class ProductController {
	@Autowired
	private StockTotalDetailService stockTotalDetailService;
	
	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductTypeService proTypeRepository;
	
	@Autowired
	private StockTotalDetailRepository repository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepositoty brandService;

	@Autowired
	private PalletPoisitionRepository repo;
	
	@Autowired
	private ProductTypeRepository productTypeService;
//	@RequestMapping(value = "/stock", method = RequestMethod.POST)
//	public String createproduct(@ModelAttribute("productvo") @Valid ProductVo vo, BindingResult result,
//			final RedirectAttributes redirectAttributes, ModelMap map) {
//		System.out.println("đá");
//		if (result.hasErrors()) {
//			map.addAttribute("msg", "Chua nhap thong tin day du");
//			return null;
//		} else {
//			// Chuyển vo sang book
//			Product product = new Product();
//			BeanUtils.copyProperties(vo, product);
//			System.out.println("12121");
//			// 1. Đặt lại tên file cho book
//			MultipartFile file = vo.getImage();// lấy ra file ảnh up
//			String fileName = file.getOriginalFilename(); // lấy ra tên file
//			System.out.println("GFGFgf");
//			product.setImage(fileName); // lưu lại tên ảnh cho book
//		
//			// 2. Upload image
//			try {
//				// lấy ra tên thư mục
//				String webappRoot = context.getRealPath("/images/");
//				// upload ảnh tới /static/images + tên file ảnh + đuôi ảnh
//				String filename = webappRoot + fileName;
//
//				// Chuyển MultipathFile sang dạng Stream Java để lưu lại vào đg dẫn
//				// /static/images/anh1.png = localhost:8080/images/
//				byte[] bytes = file.getBytes();
//				Path path = Paths.get(filename);
//				Files.write(path, bytes);
//
//				// 3. Lưu
//				service.create(product);
//				redirectAttributes.addFlashAttribute("msg", "Đã thêm sản phẩm thành công!");
//			} catch (IOException e) {
//				e.printStackTrace();
//				redirectAttributes.addFlashAttribute("msg", "Thêm sản phẩm thật bại!");
//			}
//			return "redirect:/stock";
//		}
//	}


	@GetMapping("/moveposition/{idpallet}")
	public String movePoisition(@PathVariable("idpallet") String idpallet, Model model, HttpServletRequest response,Model model,Pageable pageable) {
		HttpSession session = response.getSession(true);
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		Long longStock = Long.parseLong(id);
		Long palletId = Long.parseLong(idpallet);
		StockTotalDetail stock = service.findOne(longStock);
		PalletPosition pallet = repo.findOne(palletId);
		stock.setPalletPosition(pallet);
		repository.save(stock);
		model.addAttribute("msg", "Đã chuyển vị trí thành công");
		String chotkho ="";
		chotkho = "Hoàn thành chốt kho. Vui lòng duyệt kiểm kê để kho tiếp tục hoạt động";
			model.addAttribute("chotkho", chotkho);
			model.addAttribute("brand", brandService.getAll());
			model.addAttribute("protype", proTypeRepository.getAll());
			model.addAttribute("product", new Product());
			model.addAttribute("productList", productRepository.findAll());
			model.addAttribute("stockTotalDetail", stockTotalDetailService.findAll(pageable));
			model.addAttribute("page", stockTotalDetailService.findAll(pageable));
			return "stock";

	}

}
