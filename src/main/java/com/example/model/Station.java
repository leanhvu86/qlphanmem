package com.example.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Integer stationId;

    @NotNull
    @Size(min = 1, max = 100, message = "Độ dài của tên ga là 1 - 100 kí tự")
    @Column(name = "name_station")
    private String nameStation;

    @NotNull
    @Size(min=10,max = 200, message = "Độ dài địa chỉ 10- 200 kí tự")
	@Pattern(regexp = "^((?![$&+:;=\\?@#|/'<>.^*()%!]).)*$",message="Địa chỉ không chứa kí tự đặc biệt")
    @Column(name = "address_station")
    private String addressStation;

    @NotNull
    @Pattern(regexp = "^[0-9]{1,5}$",message="Sức chứa của ga là kiểu số từ 1 - 5 kí tự")
    @Column(name = "person_capacity")
    private String personCapacity;

    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):?([0-5]\\d)$",message="Từ giờ không hợp lệ theo định dạng hh:mm")
    @Column(name = "start_time")
    private String startTime;

    @NotNull
    @Pattern(regexp = "^([01]\\d|2[0-3]):?([0-5]\\d)$",message="Đến giờ không hợp lệ theo định dạng hh:mm")
    @Column(name = "end_time")
    private String endTime;

    @NotNull
    @Min(1)
    @Column(name = "type_station")
    private Integer typeStation;

    @NotNull
    @Column(name = "create_date")
    private String createDate;

    @Column(name = "name_route")
    private String nameRoute;

    @NotNull
    @Min(0)
    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    @NotNull
    private RouteRail routeId;

    @Column(name = "update_date")
    private String updateDate;

    @Column(name = "description")
    private String description;
    public Station() {
        super();
    }


    public Station(String nameStation, String addressStation, String personCapacity, String startTime, String endTime, Integer typeStation, String createDate, String nameRoute, Integer status, RouteRail routeId, String updateDate, String description) {
        this.nameStation = nameStation;
        this.addressStation = addressStation;
        this.personCapacity = personCapacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.typeStation = typeStation;
        this.createDate = createDate;
        this.nameRoute = nameRoute;
        this.status = status;
        this.routeId = routeId;
        this.updateDate = updateDate;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTypeStation() {
        return typeStation;
    }

    public void setTypeStation(Integer typeStation) {
        this.typeStation = typeStation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RouteRail getRouteId() {
        return routeId;
    }

    public void setRouteId(RouteRail routeId) {
        this.routeId = routeId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public String getAddressStation() {
        return addressStation;
    }

    public void setAddressStation(String addressStation) {
        this.addressStation = addressStation;
    }

    public String getPersonCapacity() {
        return personCapacity;
    }

    public void setPersonCapacity(String personCapacity) {
        this.personCapacity = personCapacity;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
