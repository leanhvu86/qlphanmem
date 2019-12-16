package com.nxm.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nxm.model.Employee;
import com.nxm.model.Pallet;
import com.nxm.model.PalletPosition;
import com.nxm.model.Product;
import com.nxm.model.Role;
import com.nxm.model.StockTotal;
import com.nxm.model.StockTotalDetail;
import com.nxm.model.User;
import com.nxm.repository.PalletPoisitionRepository;
import com.nxm.repository.PalletRepository;
import com.nxm.repository.ProductRepository;
import com.nxm.repository.RoleRepository;
import com.nxm.repository.StockTotalDetailRepository;
import com.nxm.repository.UserRepository;
import com.nxm.service.StockTotalService;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
//
	@Autowired
	private PalletRepository palletRepository;
//
	// @Autowired
	// private EmployeeRepositoty employeeRespository;
	@Autowired
	private PalletPoisitionRepository palletPoisitionRepository;
//
	@Autowired
	private StockTotalService stockTotalService;
//	
	@Autowired
	private StockTotalDetailRepository stockTotalDetailRepository;
//	
	@Autowired
	private ProductRepository productRepository;
//
	@Autowired

	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		/*
		 * if (roleRepository.findByName("ROLE_ADMIN") == null) {
		 * roleRepository.save(new Role("ROLE_ADMIN")); }
		 * 
		 * if (roleRepository.findByName("ROLE_MEMBER") == null) {
		 * roleRepository.save(new Role("ROLE_MEMBER")); }
		 * 
		 * // Admin account if (userRepository.findByEmail("admin") == null) { // thêm
		 * // employee vào trước bằng tay Set<Employee> employees = new
		 * //HashSet<Employee>(); Long id =Long.parseLong("1"); //Employee temp =
		 * //employeeRespository.findOne(id); //employees.add(temp); User admin = new
		 * User(); admin.setEmail("leanhvu86@gmail.com");
		 * admin.setPassword(passwordEncoder.encode("123456")); admin.setStatus(1);
		 * admin.setCreateDate("2019-12-01"); admin.setUpdateDate("2019-12-01"); //
		 * admin.setEmployees(employees); HashSet<Role> roles = new HashSet<>();
		 * roles.add(roleRepository.findByName("ROLE_ADMIN"));
		 * roles.add(roleRepository.findByName("ROLE_MEMBER")); admin.setRoles(roles);
		 * userRepository.save(admin); }
		 * 
		 * // // // Member account if
		 * (userRepository.findByEmail("nguyenxuanminh10@gmail.com") == null) { User
		 * user = new User(); user.setEmail("nguyenxuanminh10@gmail.com");
		 * user.setPassword(passwordEncoder.encode("123456")); HashSet<Role> roles = new
		 * HashSet<>(); roles.add(roleRepository.findByName("ROLE_MEMBER"));
		 * user.setRoles(roles); userRepository.save(user); }
		 */

		// Lưu pallet_poisition begin 2

		/*Long pallet_id = (long) 1;
		for (int i = 0; i < 45; i++) {
			Pallet palletTemp = palletRepository.findOne(pallet_id);
			if (palletTemp != null) {
				System.out.println(palletTemp);
				for (int hor = 0; hor < 4; hor++) {
					for (int ver = 0; ver < 3; ver++) {
						PalletPosition palletPositionTemp = new PalletPosition();
						palletPositionTemp.setPallet(palletTemp);
						if (hor == 0) {
							palletPositionTemp.setHorizontalAxis("A");
						} else if (hor == 1) {
							palletPositionTemp.setHorizontalAxis("B");
						} else if (hor == 2) {
							palletPositionTemp.setHorizontalAxis("C");
						} else if (hor == 3) {
							palletPositionTemp.setHorizontalAxis("D");
						}

						palletPositionTemp.setVerticalAxis(Integer.toString(ver + 1));
						palletPositionTemp.setEmptyPercent(0);
						palletPoisitionRepository.save(palletPositionTemp);
						System.out.println("Id pallet: " + palletPositionTemp.getPallet().getId() + "  hor: "
								+ palletPositionTemp.getHorizontalAxis() + " ver: "
								+ palletPositionTemp.getVerticalAxis() + " percent: "
								+ palletPositionTemp.getEmptyPercent());
					}

				}

			}
			pallet_id++;
		}*/

		// end 2

		/*StockTotal stockTotal = stockTotalService.findAvaiableRecord();
		StockTotalDetail stockTotalDetail = new StockTotalDetail();
		Product product = productRepository.findOne((long) 1);
		stockTotalDetail.setProduct(product);
		stockTotalDetail.setQuantity(100);
		stockTotalDetail.setAvaiableQuantity(100);
		String time1 = "2020-01-01"; // convert String to LocalDateTime // parse it
		// to a specified format
		stockTotalDetail.setExpiredDate("2020-05-20");
		stockTotalDetail.setPalletPosition(palletPoisitionRepository.findOne((long) 1));
		stockTotalDetail.setStockTotal(stockTotal);
		stockTotalDetail.setProductStatus(1);
		stockTotalDetail.setUserCreateId(1);
		stockTotalDetailRepository.save(stockTotalDetail);
*/
		
	}
}