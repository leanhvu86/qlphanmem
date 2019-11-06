package com.example.model;

public class RouteMappingVO {
    private Integer routeStart;
    private Integer stationStop1;
    private Integer routeMiddle;
    private Integer stationStop2;
    private Integer routeEnd;

    public RouteMappingVO() {
    }

    public RouteMappingVO(Integer routeStart, Integer stationStop1, Integer routeMiddle, Integer stationStop2, Integer routeEnd) {
        this.routeStart = routeStart;
        this.stationStop1 = stationStop1;
        this.routeMiddle = routeMiddle;
        this.stationStop2 = stationStop2;
        this.routeEnd = routeEnd;
    }

    public Integer getRouteStart() {
        return routeStart;
    }

    public void setRouteStart(Integer routeStart) {
        this.routeStart = routeStart;
    }

    public Integer getStationStop1() {
        return stationStop1;
    }

    public void setStationStop1(Integer stationStop1) {
        this.stationStop1 = stationStop1;
    }

    public Integer getRouteMiddle() {
        return routeMiddle;
    }

    public void setRouteMiddle(Integer routeMiddle) {
        this.routeMiddle = routeMiddle;
    }

    public Integer getStationStop2() {
        return stationStop2;
    }

    public void setStationStop2(Integer stationStop2) {
        this.stationStop2 = stationStop2;
    }

    public Integer getRouteEnd() {
        return routeEnd;
    }

    public void setRouteEnd(Integer routeEnd) {
        this.routeEnd = routeEnd;
    }
}
