package com.neuSpring18.ui.CustomerUI;


import com.neuSpring18.dto.*;
import com.neuSpring18.service.IVehicleService;
import com.neuSpring18.service.VehicleService;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author mm
 */
public class UIForTable extends JFrame {
    private JPanel result;
    private JScrollPane tableScrollPane;
    private JTable vehiclesAfterFilter;
    Object[][] vehicleRow;

    private String dealerID = someFunctionWeNeed.gotDealId();

    public UIForTable() {
        initComponents();
    }

    private ArrayList<Vehicle> getVehicleAterFile() {
        IVehicleService vehicleService = new VehicleService();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Filter f = new Filter();
        f.setMinPrice("20000");
        Sorting s = Sorting.ASCEND_PRICE;
        Paging p = new Paging();
        p.setPageNum(1);
        p.setPerPage(10);
        for (Vehicle vehicle : vehicleService.getVehiclesByFilter("gmps-curry", f, s, p)) {
            vehicles.add(vehicle);
        }

        return vehicles;
    }


    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        vehicleRow = getVehicleRow();
        createVehiclesTable();
        setButtonRender();
        setTabelLayout(result);
        pack();
    }

    public Object[][] getVehicleRow() {
        ArrayList<Vehicle> vehicleList = getVehicleAterFile();

        vehicleRow = new Object[vehicleList.size()][10];
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle vehicle = vehicleList.get(i);
            vehicleRow[i][0] = vehicle.getId();
            vehicleRow[i][1] = vehicle.getWebId();
            vehicleRow[i][2] = vehicle.getCategory();
            vehicleRow[i][3] = vehicle.getYear();
            vehicleRow[i][4] = vehicle.getMake();
            vehicleRow[i][5] = vehicle.getModel();
            vehicleRow[i][6] = vehicle.getBodyType();
            vehicleRow[i][7] = vehicle.getPrice();
            vehicleRow[i][8] = vehicle.getPhotoUrl();
            vehicleRow[i][9] = "Button";

        }
        return vehicleRow;
    }


    private void createVehiclesTable() {
        result = new JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        vehiclesAfterFilter = new javax.swing.JTable();

        vehiclesAfterFilter.setModel(new javax.swing.table.DefaultTableModel(
                vehicleRow,
                new String[]{
                        "id", "webId", "category", "year", "make", "model", "bodyType", "price", "photoUrl", "Detailed Description"
                }

        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, Category.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, URL.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        tableScrollPane.setViewportView(vehiclesAfterFilter);

    }

    private void setButtonRender() {

        vehiclesAfterFilter.getColumn("Detailed Description").setCellRenderer(new ButtonRenderer());
        vehiclesAfterFilter.getColumn("Detailed Description").setCellEditor(new ButtonEditor(new JTextField()));

    }

    private void setContentPaneLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(43, Short.MAX_VALUE)
                                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71))
        );
    }

    private GroupLayout setTabelLayout(JPanel result) {
        GroupLayout resultLayout = new GroupLayout(result);
        result.setLayout(resultLayout);
        setHorizontalAndVerticalGroup(resultLayout);
        setContentPaneLayout();
        return resultLayout;
    }

    private void setHorizontalAndVerticalGroup(GroupLayout resultLayout) {
        resultLayout.setHorizontalGroup(
                resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(resultLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                                .addGap(74, 74, 74))
        );
        resultLayout.setVerticalGroup(
                resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultLayout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
        );
    }

    public static void main(String args[]) {
        new UIForTable().setVisible(true);
    }

}



