package com.nxm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nxm.model.PalletPosition;
import com.nxm.model.Product;
import com.nxm.model.ProductTradeVO;
import com.nxm.model.StockChange;
import com.nxm.model.StockTotal;
import com.nxm.model.StockTotalDetail;
import com.nxm.model.StockTotalDetailVO;
import com.nxm.model.StockTrade;
import com.nxm.model.StockTradeDetail;
import com.nxm.repository.BrandRepositoty;
import com.nxm.repository.PalletPoisitionRepository;
import com.nxm.repository.ProductRepository;
import com.nxm.repository.ProductTypeRepository;
import com.nxm.repository.StockTotalDetailRepository;
import com.nxm.repository.StockTradeDetailRepository;
import com.nxm.repository.StockTradeRepository;
import com.nxm.repository.UserRepository;
import com.nxm.service.BrandService;
import com.nxm.service.ProductService;
import com.nxm.service.ProductTypeService;
import com.nxm.service.StockTotalDetailService;
import com.nxm.utils.ReadingFromExcelSheet;

@Controller
public class TradeController {
	@Autowired
	private StockTotalDetailService stockTotalDetailService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private ProductTypeService productTypeService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private StockTotalDetailRepository repository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StockTradeRepository stockTradeRepository;

	@Autowired
	private StockTradeDetailRepository stockTradeDetailRepository;

	@Autowired
	private BrandRepositoty brandRepositoty;

	@Autowired
	private PalletPoisitionRepository repo;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@GetMapping("/trade")
	public String tradePageLoad(Model model, HttpServletRequest response, Pageable pageable) {

	
		model.addAttribute("brand", brandService.getAll());
		model.addAttribute("protype", productTypeService.getAll());
		model.addAttribute("productList", productRepository.findAll());
		model.addAttribute("stockTrade", stockTradeRepository.findAll(pageable));
		model.addAttribute("page", stockTradeRepository.findAll(pageable));
		return "trade";

	}

	@PostMapping("/createTrade")
	public String createTrade(Model model, HttpServletRequest response,
			@RequestParam(value = "createDate", required = false) String createDate,
			@RequestParam(value = "tradeDate", required = false) String tradeDate,
			@RequestParam(value = "typeOfTrade", required = false) String typeOfTrade, Pageable pageable) {
		String status = "";
		if (tradeDate != null && !tradeDate.equals("") && typeOfTrade != null && !typeOfTrade.equals("")) {

			StockTrade stockTrade = new StockTrade();
			stockTrade.setDelveryDate(tradeDate);
			stockTrade.setTypeOfTrade(Long.parseLong(typeOfTrade));
			stockTrade.setTradeDate(tradeDate);
			stockTrade.setUserId(1);
			stockTradeRepository.save(stockTrade);
			status = "Tạo đơn giao dịch thành công. Vui lòng nhập file excel để hoàn thành tạo đơn giao dịch kho.";
			model.addAttribute("status", status);
			model.addAttribute("brand", brandService.getAll());
			model.addAttribute("protype", productTypeService.getAll());
			model.addAttribute("productList", productRepository.findAll());
			model.addAttribute("stockTrade", stockTradeRepository.findAll(pageable));
			model.addAttribute("page", stockTradeRepository.findAll(pageable));
			return "trade";
		} else {
			status = "Tạo đơn giao dịch kho thất bại. Vui lòng liên hệ chăm sóc khách hàng!";
			model.addAttribute("status", status);
			model.addAttribute("brand", brandService.getAll());
			model.addAttribute("protype", productTypeService.getAll());
			model.addAttribute("productList", productRepository.findAll());
			model.addAttribute("stockTrade", stockTradeRepository.findAll(pageable));
			model.addAttribute("page", stockTradeRepository.findAll(pageable));
			return "trade";
		}
	}

	@GetMapping("/exportExcelTrade")
	public void downloadFile(HttpServletRequest httpServletRequest, HttpServletResponse response) {

		String fileName = "src/main/resources/excel/Bieu_mau_xuat_nhap_kho.xlsx";

		try {
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

	@PostMapping("/importExcelTrade")
	public String mapReapExcelDatatoDB(HttpServletRequest httpServletRequest, HttpServletResponse response,
			@RequestParam("file") MultipartFile reapExcelDataFile, Model model,
			@PageableDefault(size = 10) Pageable pageable) throws IOException, IllegalStateException {
		StockTrade stockTrade = new StockTrade();
		ReadingFromExcelSheet readingFromExcelSheet = new ReadingFromExcelSheet();
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		int count = 0;
		int countFail = 0;
		int productCount = 0;
		List<ProductTradeVO> lstFail = new ArrayList<>();
		List<StockTotalDetailVO> lstCompare = new ArrayList<>();
		int totalRow = worksheet.getPhysicalNumberOfRows();

		for (int i = 3; i < totalRow + 1; i++) {
			int checkData = 0;
			String msg = "";
			XSSFRow row = worksheet.getRow(i);
			ProductTradeVO productTradeVO = new ProductTradeVO();
			Product product = new Product();
			for (int cellIndex = 0; cellIndex < 8; cellIndex++) {

				String value = "";
				Cell cell = row.getCell(cellIndex);
				if (cell != null && cellIndex == 0) {

					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						try {
							productTradeVO.setProductId((int) Math.round(Double.parseDouble(value)));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							msg += "Mã sản phẩm không hợp lệ";
							checkData++;
							continue;
						}
						product = productService.findOne(productTradeVO.getProductId());
						if (product == null) {
							msg += "Mã sản phẩm không tồn tại trong hệ thống";
							checkData++;
							continue;
						}

					} else {
						msg += "Không để trống mã sản phẩm";
						checkData++;
						continue;
					}

				}
				if (cell != null && cellIndex == 1) {
					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						productTradeVO.setProductName(value);
					}

				}
				if (cell != null && cellIndex == 2) {
					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						productTradeVO.setBrandName(value);

					} else {
						msg += "Không để trống Nhãn hiệu";
						checkData++;
						continue;
					}

				}
				if (cell != null && cellIndex == 3) {
					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						try {
							productTradeVO.setTypeOfProduct(value);
						} catch (NumberFormatException e) {
							e.printStackTrace();
							msg += "Loại sản phẩm không hợp lệ";
							checkData++;
							continue;
						}

					} else {
						msg += "Không để trống loại sản phẩm";
						checkData++;
						continue;
					}

				}

				if (cell != null && cellIndex == 4) {
					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						try {
							productTradeVO.setQuantity((int) Math.round(Double.parseDouble(value)));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							msg += "Số lượng không hợp lệ";
							checkData++;
							continue;
						}
					} else {
						msg += "Không để trống số lượng";
						checkData++;
						continue;
					}
				}
				if (cell != null && cellIndex == 5) {
					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						productTradeVO.setExpiredDate(value);
					} else {
						msg += "Không để trống ngày hết hạn";
						checkData++;
					}
				}
				if (cell != null && cellIndex == 6) {
					value = readingFromExcelSheet.getCellValue(cell);
					if (!value.equals("")) {
						try {
							productTradeVO.setStockTradeId((long) Math.round(Double.parseDouble(value)));
						} catch (NumberFormatException e) {
							e.printStackTrace();
							msg += "Mã giao dịch kho không hợp lệ";
							checkData++;
							continue;
						}
					} else {
						msg += "Không để trống mã giao dịch kho kho";
						checkData++;
					}
				}
				if (!msg.equals("") && checkData != 6) {
					productTradeVO.setError(msg);
					if (!lstFail.contains(productTradeVO)) {
						lstFail.add(productTradeVO);
					}
					countFail++;
				}

			}
			if (msg.equals("")) {
				stockTrade = stockTradeRepository.findOne(productTradeVO.getStockTradeId());
				if (stockTrade != null) {
					List<StockTradeDetail> lstStockTradeDetail = stockTradeDetailRepository
							.findByStockTrade(stockTrade);
					if (lstStockTradeDetail != null) {
						for (StockTradeDetail stockTradeDetailOld : lstStockTradeDetail) {
							if (stockTradeDetailOld.getProduct().getId() == productTradeVO.getProductId()
									&& stockTradeDetailOld.getExpiredDate().equals(productTradeVO.getExpiredDate())) {
								long amount = 0;
								amount = productTradeVO.getQuantity() * product.getPrice();
								stockTradeDetailOld.setAmount(Long.toString(amount));
								stockTradeDetailOld.setProduct(product);
								stockTradeDetailOld.setQuantity(productTradeVO.getQuantity());
								stockTradeDetailOld.setUserUpdateId(1);
								stockTradeDetailRepository.save(stockTradeDetailOld);
							} else {
								StockTradeDetail stockTradeDetailNew = new StockTradeDetail();
								long amount = 0;
								amount = productTradeVO.getQuantity() * product.getPrice();
								stockTradeDetailNew.setStockTrade(stockTrade);
								stockTradeDetailNew.setAmount(Long.toString(amount));
								stockTradeDetailNew.setProduct(product);
								stockTradeDetailNew.setQuantity(productTradeVO.getQuantity());
								stockTradeDetailNew.setUserCreateId(1);
								stockTradeDetailNew.setExpiredDate(productTradeVO.getExpiredDate());
								stockTradeDetailRepository.save(stockTradeDetailNew);
							}
						}
					} else {
						StockTradeDetail stockTradeDetailNew = new StockTradeDetail();
						long amount = 0;
						amount = productTradeVO.getQuantity() * product.getPrice();
						stockTradeDetailNew.setStockTrade(stockTrade);
						stockTradeDetailNew.setAmount(Long.toString(amount));
						stockTradeDetailNew.setProduct(product);
						stockTradeDetailNew.setQuantity(productTradeVO.getQuantity());
						stockTradeDetailNew.setUserCreateId(1);
						stockTradeDetailNew.setExpiredDate(productTradeVO.getExpiredDate());
						stockTradeDetailRepository.save(stockTradeDetailNew);
					}
				} else {
					msg += "Mã giao dịch kho không hợp lệ";
					lstFail.add(productTradeVO);

				}
			}
			if (checkData == 6) {
				break;
			}
			productCount++;
		}

		String chotkho = "";
		if (lstFail.size() == 0) {

			chotkho = "Hoàn thành thông tin đơn giao dịch kho. Vui lòng liên hệ ban quản trị để xét duyệt";
			model.addAttribute("chotkho", chotkho);
			model.addAttribute("brand", brandService.getAll());
			model.addAttribute("protype", productTypeService.getAll());
			model.addAttribute("product", new Product());
			model.addAttribute("productList", productRepository.findAll());
			model.addAttribute("stockTrade", stockTradeRepository.findAll(pageable));
			model.addAttribute("page", stockTradeRepository.findAll(pageable));
			return "stock";
		} else {
			if (lstFail.size() != 0) {
				model.addAttribute("compare", "Chênh lệch: " + lstFail.size());
			}
			chotkho = "Chưa hoàn thành nhập thông tin đơn giao dịch kho. Vui lòng kiểm tra file Excel chốt kho";
			model.addAttribute("chotkho", chotkho);
			model.addAttribute("brand", brandService.getAll());
			model.addAttribute("protype", productTypeService.getAll());
			model.addAttribute("product", new Product());
			model.addAttribute("lstFail", lstFail);
			model.addAttribute("productList", productRepository.findAll());
			model.addAttribute("stockTrade", stockTradeRepository.findAll(pageable));
			model.addAttribute("page", stockTradeRepository.findAll(pageable));
			// exportFile(httpServletRequest, response, "Bieu_mau_chot_kho_loi", lstFail);
		}

		return "trade";
	}
}
