package com.example.controller;

import com.example.model.*;
import com.example.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping(path = "/news", method = RequestMethod.GET)
	public String createNews(@ModelAttribute("newsModelAttribute") NewsVO newsVO, ModelMap modelMap,
			Pageable pageable) {
		if (httpSession.getAttribute(Constants.USER_LOGGED_KEY) != null) {
			Page<News> productPage = newsService.findAll(pageable);
			PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
			modelMap.addAttribute("lstnews", page.getContent());
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newVO", newsVO);
			modelMap.addAttribute("message", "");
			return "news";
		} else {
			modelMap.addAttribute("message", "Tài khoản không hợp lệ");
			return "login";
		}

	}

	/*
	 * public String getRealFile(HttpServletRequest request, String path) { return
	 * request.getSession().getServletContext().getRealPath(path); }
	 */
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/news/create",method = RequestMethod.POST)
	public String create( @Validated @ModelAttribute("newsVO") NewsVO newsVO,
			BindingResult bindingResult,Pageable pageable, Model modelMap) {
		if (bindingResult.hasErrors()) {
			Page<News> productPage = newsService.findAll(pageable);
			PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
			modelMap.addAttribute("lstnews", page.getContent());
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newVO", newsVO);
			modelMap.addAttribute("message", "Lưu tin tức thất bại");
			return "news";
		} else {
			News news = new News();
			BeanUtils.copyProperties(newsVO, news);
		
			LoginUser loginUser = new LoginUser();
			loginUser.setUserId(1);
			news.setLoginUser(loginUser);
			// 2. Upload image
			try {
				if(newsVO.getImage()!=null) {
					
					MultipartFile file = newsVO.getImage(); // lấy ra file ảnh up
					String fileName = file.getOriginalFilename(); // =>> lấy ra tên file: anh1.png
					news.setImage(fileName); // lưu lại tên ảnh cho product
					// lấy ra tên thư mục ứng với localhost:8080/images/ = /static/images
					String webappRoot = servletContext.getRealPath("/images/");
					// sẽ upload ảnh tới /static/images + tên file ảnh + đuôi ảnh
					// VD: /static/images/anh1.png
					String filename = webappRoot + fileName;
					String PATH_TO_PACKAGE = System.getProperty("user.dir");
					// Chuyển MultipartFile sang dạng Stream Java để lưu lại
					// vào đường dẫn /static/images/anh1.png = localhost:8080/images/
					byte[] bytes = file.getBytes();
					Path path = Paths.get(filename);
					Files.write(path, bytes);
				}
				
				// 3. save product
				boolean check = newsService.saveNews(news);

				// 4. Báo lưu ảnh thành công
				if (check == true) {

					Page<News> productPage = newsService.findAll(pageable);
					PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
					modelMap.addAttribute("lstnews", page.getContent());
					modelMap.addAttribute("page", page);
					modelMap.addAttribute("newVO", newsVO);
					modelMap.addAttribute("message", "Lưu tin tức thành công");
					return "/news";
				} else {
					Page<News> productPage = newsService.findAll(pageable);
					PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
					modelMap.addAttribute("lstnews", page.getContent());
					modelMap.addAttribute("page", page);
					modelMap.addAttribute("newVO", newsVO);
					modelMap.addAttribute("message", "Lưu tin tức thất bại");
					return "/news";
				}
			} catch (IOException e) {
				e.printStackTrace();
				Page<News> productPage = newsService.findAll(pageable);
				PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
				modelMap.addAttribute("lstnews", page.getContent());
				modelMap.addAttribute("page", page);
				modelMap.addAttribute("newVO", newsVO);
				modelMap.addAttribute("message", "Lưu tin tức thất bại");
				return "/news";
			}
		}
	}

	@RequestMapping(path = "/news/delete/{id}", method = RequestMethod.GET)
	public String deleteNews(@PathVariable("id") Integer id, ModelMap modelMap, Pageable pageable) {
		News temp = newsService.getNewsById(id);
		if (temp != null) {
			temp.setStatus(false);
			newsService.saveNews(temp);
		}
		Page<News> productPage = newsService.findAll(pageable);
		PageWrapper<News> page = new PageWrapper<>(productPage, "/lstnews");
		modelMap.addAttribute("lstnews", page.getContent());
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("newVO", new NewsVO());

		return "news";

	}
}
