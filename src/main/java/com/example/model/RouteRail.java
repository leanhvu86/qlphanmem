package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "routeRail")
public class RouteRail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Integer routeId;

    @NotNull
    @Column(name = "name_route")
    private String nameRoute;

    @NotNull
    @Column(name = "lst_station")
    private String lstStation;

    @NotNull
    @Column(name = "create_date")
    private String createDate;

    @NotNull
    @Column(name = "update_date")
    private String updateDate;

    @NotNull
    @Column(name = "status")
    private int status;

    @NotNull
    @Column(name = "station_route")
    private String stationRoute;
    public RouteRail() {
        super();
    }

    public RouteRail(String nameRoute, String lstStation, String createDate, String updateDate, int status) {
        this.nameRoute = nameRoute;
        this.lstStation = lstStation;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public String getLstStation() {
        return lstStation;
    }

    public void setLstStation(String lstStation) {
        this.lstStation = lstStation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStationRoute() {
        return stationRoute;
    }

    public void setStationRoute(String stationRoute) {
        this.stationRoute = stationRoute;
    }
}
