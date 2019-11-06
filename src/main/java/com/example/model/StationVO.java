package com.example.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StationVO {

    private Integer stationId;

    private String nameStation;

    private String addressStation;

    private String personCapacity;

    private String startTime;

    private String endTime;

    private Integer typeStation;

    private String createDate;

    private String nameRoute;

    private Integer status;

    private Integer roundId;

    private String updateDate;

    private String description;
    public StationVO() {
        super();
    }


    @Transient
    private MultipartFile image;


    public StationVO(String nameStation, String addressStation, String personCapacity, String startTime, String endTime, Integer typeStation, String createDate, String nameRoute, Integer status, Integer roundId, String updateDate, String description) {
        this.nameStation = nameStation;
        this.addressStation = addressStation;
        this.personCapacity = personCapacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.typeStation = typeStation;
        this.createDate = createDate;
        this.nameRoute = nameRoute;
        this.status = status;
        this.roundId = roundId;
        this.updateDate = updateDate;
        this.description = description;
    }

    public StationVO(String nameStation, String addressStation, String personCapacity, String startTime, String endTime, Integer typeStation, String createDate, String nameRoute, Integer status, Integer roundId, String updateDate, String description, MultipartFile image) {
        this.nameStation = nameStation;
        this.addressStation = addressStation;
        this.personCapacity = personCapacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.typeStation = typeStation;
        this.createDate = createDate;
        this.nameRoute = nameRoute;
        this.status = status;
        this.roundId = roundId;
        this.updateDate = updateDate;
        this.description = description;
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
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
