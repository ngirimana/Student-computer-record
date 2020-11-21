package com.example.sqllitecrudeoperation;

public class StudentData {
    String id,name,regNumber,serialNumber,image,phone,addTimeStamp,updateTimeStamp;

    public StudentData(String id, String name, String regNumber, String serialNumber, String image, String phone, String addTimeStamp, String updateTimeStamp) {
        this.id = id;
        this.name = name;
        this.regNumber = regNumber;
        this.serialNumber = serialNumber;
        this.image = image;
        this.phone = phone;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getImage() {
        return image;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }
}
