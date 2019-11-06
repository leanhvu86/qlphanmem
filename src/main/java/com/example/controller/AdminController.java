package com.example.controller;

import com.example.model.*;
import com.example.repository.RouteMappingRepository;
import com.example.repository.StationRepository;
import com.example.service.*;
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
import org.thymeleaf.expression.Lists;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AdminController {


    private StationService stationService;

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    RouteRailService routeRailService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    RouteMappingService routeMappingService;

    @Autowired
    ServletContext servletContext;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(path = "/stations", method = RequestMethod.GET)
    public String getAllEmployees(Model model, Pageable pageable) {
        if (httpSession.getAttribute(Constants.USER_LOGGED_KEY) != null) {
            Page<Station> productPage = stationService.findAll(pageable);
            PageWrapper<Station> page = new PageWrapper<>(productPage, "/stations");
            model.addAttribute("stations", page.getContent());
            model.addAttribute("page", page);
            return "station";
        } else {
            model.addAttribute("message", "Tài khoản không hợp lệ");
            return "login";
        }
    }


    @RequestMapping(value = "/stations/{id}", method = RequestMethod.GET)
    public String getStationById(@PathVariable("id") Integer id, ModelMap modelMap) {
        Station station = stationService.getStationById(id);
        modelMap.addAttribute("message", "");
        modelMap.addAttribute("station", station);
        List<RouteRail> lstRouteRail = routeRailService.getAllRouteRail();
        modelMap.addAttribute("lstRouteRail", lstRouteRail);
        return "stationDetail";
    }

    @RequestMapping(value = "/stations/add", method = RequestMethod.GET)
    public String addStationById(ModelMap modelMap) {
        modelMap.addAttribute("message", "");
        modelMap.addAttribute("station", new Station());
        List<RouteRail> lstRouteRail = routeRailService.getAllRouteRail();
        modelMap.addAttribute("lstRouteRail", lstRouteRail);
        return "stationDetail";
    }

    @RequestMapping(value = "/update/station", method = RequestMethod.POST)
    public String updateEmployee(ModelMap modelMap, @Validated @ModelAttribute("station") Station station, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<RouteRail> lstRouteRail = routeRailService.getAllRouteRail();
            modelMap.addAttribute("lstRouteRail", lstRouteRail);
            return "stationDetail";
        } else {
            /*MultipartFile file = image;     // lấy ra file ảnh up
            String fileName = file.getOriginalFilename();  //=>> lấy ra tên file: anh1.png
            // 2. Upload image
            try {

                // lấy ra tên thư mục ứng với localhost:8080/images/  = /static/images

                String webappRoot = servletContext.getRealPath("/images/");
                //sẽ upload ảnh tới  /static/images + tên file ảnh + đuôi ảnh
                //VD:  /static/images/anh1.png
                String filename = webappRoot + fileName;
                String PATH_TO_PACKAGE = System.getProperty("user.dir");
                // Chuyển MultipartFile sang dạng Stream Java để lưu lại
                // vào đường dẫn /static/images/anh1.png = localhost:8080/images/
                byte[] bytes = file.getBytes();
                Path path = Paths.get(filename);
                Files.write(path, bytes);

                // 3. save product


            } catch (IOException e) {
                e.printStackTrace();}*/
            stationService.saveStation(station);
            modelMap.addAttribute("message", "Lưu ga tầu thành công");
            modelMap.addAttribute("station", station);
            List<RouteRail> lstRouteRail = routeRailService.getAllRouteRail();
            modelMap.addAttribute("lstRouteRail", lstRouteRail);
            return "stationDetail";
        }
    }

    @RequestMapping(path = "/passenger", method = RequestMethod.GET)
    public String getAllPassenger(Model model, Pageable pageable) {
        if (httpSession.getAttribute(Constants.USER_LOGGED_KEY) != null) {
            Page<Passenger> productPage = passengerService.findAll(pageable);
            PageWrapper<Passenger> page = new PageWrapper<>(productPage, "/passengers");
            model.addAttribute("passengers", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("message", "");
            return "passenger";
        } else {
            model.addAttribute("message", "Tài khoản không hợp lệ");
            return "login";
        }
    }

    @RequestMapping(value = "/passenger/accept/{id}", method = RequestMethod.GET)
    public String getPassengerById(@PathVariable("id") Integer id, ModelMap modelMap, Pageable pageable) {
        Passenger passenger = passengerService.getPassengerById(id);
        passenger.setStatus(1);
        if (passengerService.savePassenger(passenger)) {
            modelMap.addAttribute("message", "Duyệt thành công");
            Page<Passenger> productPage = passengerService.findAll(pageable);
            PageWrapper<Passenger> page = new PageWrapper<>(productPage, "/passengers");
            modelMap.addAttribute("passengers", page.getContent());
            modelMap.addAttribute("page", page);
            return "passenger";
        } else {
            modelMap.addAttribute("message", "Duyệt thất bại");
            return "passenger";
        }
    }

    @RequestMapping(value = "/passenger/refuse/{id}", method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("id") Integer id, ModelMap modelMap, Pageable pageable) {
        Passenger passenger = passengerService.getPassengerById(id);
        passenger.setStatus(2);
        if (passengerService.savePassenger(passenger)) {
            modelMap.addAttribute("message", "Từ chối thành công");
            Page<Passenger> productPage = passengerService.findAll(pageable);
            PageWrapper<Passenger> page = new PageWrapper<>(productPage, "/passengers");
            modelMap.addAttribute("passengers", page.getContent());
            modelMap.addAttribute("page", page);
            return "passenger";
        } else {
            modelMap.addAttribute("message", "Từ chối thất bại");
            Page<Passenger> productPage = passengerService.findAll(pageable);
            PageWrapper<Passenger> page = new PageWrapper<>(productPage, "/passengers");
            modelMap.addAttribute("passengers", page.getContent());
            modelMap.addAttribute("page", page);
            return "passenger";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)

    public String searchStation(@ModelAttribute("routeVO") RouteVO routeVO, HttpServletRequest httpServletRequest, ModelMap modelMap) {
        String arrivals = routeVO.getArrival();
        String departures = routeVO.getDepartment();


        String theWay = getTheWay(arrivals, departures);
        modelMap.addAttribute("theWay", theWay);

        modelMap.addAttribute("departures", departures);
        modelMap.addAttribute("arrivals", arrivals);
        modelMap.addAttribute("routeVO", new RouteVO());
        return "search";
    }

    public String getTheWay(String arrivals, String departments) {
        List<RouteMapping> lst = routeMappingService.getAllRouteMapping();
        List<RouteMappingVO> lstMapping = new ArrayList<>();
        Station arrival = stationService.findByName(arrivals);
        Station department = stationService.findByName(departments);
        List<String[]> lsttempRouteMapping = new ArrayList<>();
        List<RouteMapping> lstStopStation = new ArrayList<>();
        boolean checkAllCase = false;
        if (arrival != null && department != null) {
            for (RouteMapping routeMapping : lst) {
                String[] tempArray = new String[100];
                String delimiter = "-";

                /* given string will be split by the argument delimiter provided. */
                tempArray = routeMapping.getRouteMapping().split(delimiter);
                lsttempRouteMapping.add(tempArray);
                int check = 0;//kiểm tra theo tuyến mặc định

                for (int stationstop = 0; stationstop < tempArray.length; stationstop++) {
                    if (arrival.getRouteId().getRouteId() == Integer.parseInt(tempArray[stationstop])) check++;

                    if (department.getRouteId().getRouteId() == Integer.parseInt(tempArray[stationstop])) check++;

                }
                if (check == 2) {
                    lstStopStation.add(routeMapping);
                    checkAllCase = true;
                }
            }

            if (lstStopStation != null && lstStopStation.size() > 0 && checkAllCase == true) {
                Station stop = stationService.getStationById(lstStopStation.get(0).getLstStation());
                return "Mời bạn chọn chọn tuyến " + arrival.getRouteId().getRouteId() + " và dừng ở ga " + stop.getNameStation() + " sau đó chọn tuyến " + department.getRouteId().getRouteId() + " cuối cùng xuống ở ga " + department.getNameStation();

            }
        }

        List<RouteRail> lstRouteRail = routeRailService.getAllRouteRail();
        String[] tempArray = new String[100];
        List<RouteRail> lstRouteArrival = new ArrayList<>();
        List<RouteRail> lstRouteDepartment = new ArrayList<>();
        List<RouteRail> lstTemp = new ArrayList<>();
        if (checkAllCase == false && arrival != null && department != null) {
            for (RouteRail routeRail : lstRouteRail) {
                /* delimiter */
                String delimiter = "-";

                /* given string will be split by the argument delimiter provided. */
                tempArray = routeRail.getStationRoute().split(delimiter);
                int check = 0;
                for (int route = 0; route < tempArray.length; route++) {
                    if (tempArray[route].equalsIgnoreCase(String.valueOf(arrival.getStationId()))) {
                        lstRouteArrival.add(routeRail);
                        check++;
                    }
                    if (tempArray[route].equalsIgnoreCase(String.valueOf(department.getStationId()))) {
                        check++;
                        lstRouteDepartment.add(routeRail);
                    }
                }
                if (check == 2) {
                    lstTemp.add(routeRail);
                    checkAllCase = true;
                }
                if (lstTemp != null && lstTemp.size() > 0 && checkAllCase == true) {

                    return "Mời bạn chọn chọn tuyến " + lstTemp.get(0).getRouteId() + " và xuống ở ga " + department.getNameStation();
                }
            }
        }

        if (checkAllCase == false && arrival != null && department != null) {
            for (int i = 0; i < lst.size() - 1; i++) {
                RouteMapping routeMapping1 = lst.get(i);
                for (int j = 0; j < lst.size(); j++) {
                    RouteMapping routeMapping2 = lst.get(j);
                    if (routeMapping1.getMapRouteId() == routeMapping2.getRouteId() && routeMapping1.getRouteId() != routeMapping2.getMapRouteId()) {
                        RouteMappingVO temp = new RouteMappingVO();
                        temp.setRouteStart(routeMapping1.getRouteId());
                        temp.setStationStop1(routeMapping1.getLstStation());
                        temp.setRouteMiddle(routeMapping1.getMapRouteId());
                        temp.setStationStop2(routeMapping2.getLstStation());
                        temp.setRouteEnd(routeMapping2.getMapRouteId());
                        lstMapping.add(temp);
                    }
                }
            }
            for (int i = 0; i < lst.size() - 1; i++) {
                RouteMapping routeMapping1 = lst.get(i);
                for (int j = lst.size() - 1; j > 0; j--) {
                    RouteMapping routeMapping2 = lst.get(j);
                    if (routeMapping1.getRouteId() == routeMapping2.getMapRouteId() && routeMapping1.getMapRouteId() != routeMapping2.getRouteId()) {
                        RouteMappingVO temp = new RouteMappingVO();
                        temp.setRouteStart(routeMapping1.getRouteId());
                        temp.setStationStop1(routeMapping1.getLstStation());
                        temp.setRouteMiddle(routeMapping1.getMapRouteId());
                        temp.setStationStop2(routeMapping2.getLstStation());
                        temp.setRouteEnd(routeMapping2.getMapRouteId());
                        lstMapping.add(temp);
                    }
                }
            }
            int dem = 0;
            for (RouteMappingVO routeMappingVO : lstMapping) {
                for (int i = 0; i < lstRouteArrival.size(); i++) {
                    if (lstRouteArrival.get(i).getRouteId() == routeMappingVO.getRouteStart()) {
                        for (RouteRail routeRail : lstRouteDepartment) {
                            if (routeRail.getRouteId() == routeMappingVO.getRouteEnd()) {
                                dem++;
                                Station stop1 = stationService.getStationById(routeMappingVO.getStationStop1());
                                Station stop2 = stationService.getStationById(routeMappingVO.getStationStop2());
                                return "Bạn vui lòng chọn tuyến " + routeMappingVO.getRouteStart() + " sau đó xuống ở ga " + stop1.getNameStation()
                                        + " chọn tuyến thứ hai số " + routeMappingVO.getRouteMiddle() + " xuống ở ga " + stop2.getNameStation() + " tiếp tục đi tuyến " + routeMappingVO.getRouteEnd()
                                        + " và xuống ở ga " + department.getNameStation();

                            }
                        }
                    }
                }
            }
        }


//

        return "Không có tuyến nào để tới được";


    }

    public void getRouteMappingAuto() {
        /*List<Station> stations = stationService.getAll();*/
        List<RouteRail> lst = routeRailService.getAllRouteRail();
        String[] tempArray = new String[100];
        List<String[]> lstRoute = new ArrayList<>();
        for (RouteRail routeRail : lst) {
            /* delimiter */
            String delimiter = "-";

            /* given string will be split by the argument delimiter provided. */
            tempArray = routeRail.getStationRoute().split(delimiter);
            lstRoute.add(tempArray);
        }

        for (int i = 0; i < lstRoute.size() - 2; i++) {
            String[] tempArray1 = lstRoute.get(i);

            for (int j = 1; j < lstRoute.size() - 1; j++) {
                String[] tempArray2 = lstRoute.get(j);
                for (int route = 0; route < tempArray1.length; route++) {
                    for (int route1 = 0; route1 < tempArray2.length; route1++) {

                        if (Integer.parseInt(tempArray1[route]) == Integer.parseInt(tempArray2[route1]) && tempArray1 != tempArray2) {
                            RouteMapping routeMapping = new RouteMapping();
                            routeMapping.setRouteMapping((i + 1) + "-" + (j + 1));
                            routeMapping.setLstStation(Integer.parseInt(tempArray1[route]));
                            routeMappingService.save(routeMapping);
                        }
                    }
                }
            }
        }
    }
}
