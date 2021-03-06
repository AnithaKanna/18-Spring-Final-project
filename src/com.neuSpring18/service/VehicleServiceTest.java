package com.neuSpring18.service;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Paging;
import com.neuSpring18.dto.Sorting;
import com.neuSpring18.dto.Vehicle;

public class VehicleServiceTest {
    public static void main(String[] args) {


        VehicleService vs = new VehicleServiceImple();
        for (Vehicle vehicle : vs.findVehiclesByDealer("gmps-curry").getVehicles()) {
            System.out.println(vehicle);
        }

        System.out.println("--------------");

        Filter f = new Filter();
        f.setMinPrice("20000");
        Sorting s = Sorting.ASCEND_PRICE;
        Paging p = new Paging();
        p.setPageNum(1);
        p.setPerPage(10);
        for (Vehicle vehicle : vs.findVehiclesByFilter("gmps-curry", f, s, p).getVehicles()) {
            System.out.println(vehicle);
        }


        String addString = "~gmps-curry~new~2018~Heihei~Equinox~AWD LS~SUV~28195.0~http://inventory-dmg.assets-cdk.com/3/3/3/14256931333x90.jpg";
        Vehicle v = Vehicle.generateVehicle(addString);
        String newID = vs.addVehicle("gmps-curry", v);
        System.out.println(newID);
        String editString = newID + "~gmps-curry~new~2018~Lalala~Equinox~AWD LS~SUV~28195.0~http://inventory-dmg.assets-cdk.com/3/3/3/14256931333x90.jpg";
        v = Vehicle.generateVehicle(editString);
        if (vs.editVehicle("gmps-curry", v)) {
            System.out.println("Edit success");
        }
        if (vs.removeVehicle("gmps-curry", newID)) {
            System.out.println("Delete success");
        }
    }
}
