package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "route_mapping")
public class RouteMapping {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "route_mapping")
    private String routeMapping;

    @Column(name = "description")
    private String description;

    @Column(name = "vote")
    private String vote;

    @NotNull
    @Column(name = "lst_station")
    private Integer lstStation;

    @NotNull
    @Column(name = "route_id")
    private Integer routeId;

    @NotNull
    @Column(name = "map_route_id")
    private Integer mapRouteId;

    public RouteMapping() {
        super();
    }

    public RouteMapping(String routeMapping, String description, String vote, Integer lstStation, Integer routeId, Integer mapRouteId) {
        this.routeMapping = routeMapping;
        this.description = description;
        this.vote = vote;
        this.lstStation = lstStation;
        this.routeId = routeId;
        this.mapRouteId = mapRouteId;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getMapRouteId() {
        return mapRouteId;
    }

    public void setMapRouteId(Integer mapRouteId) {
        this.mapRouteId = mapRouteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteMapping() {
        return routeMapping;
    }

    public void setRouteMapping(String routeMapping) {
        this.routeMapping = routeMapping;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Integer getLstStation() {
        return lstStation;
    }

    public void setLstStation(Integer lstStation) {
        this.lstStation = lstStation;
    }
}
