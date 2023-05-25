package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.CustomerBo;
import com.devstack.pos.bo.custom.impl.CustomerBoImpl;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.view.tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane context;
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtSalary;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colEmail;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colSalary;
    public TableColumn colOperate;
    public TableColumn colID;
    public TextField txtSearch;
    public JFXButton btnSaveCustomer;

    String searchText = "";
    CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);
    public void initialize(){
        setCustomerData(searchText);
        colID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) ->
        { if(newValue != null){
            setCustomerData(newValue);
        }
        });

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable1, oldValue1, newValue1) ->
        {if(newValue1 != null){
            setData(newValue1);
        }

        });

    }

    private void setData(CustomerTm customerTm) {
        txtEmail.setEditable(false);
        txtEmail.setText(customerTm.getEmail());
        txtName.setText(customerTm.getName());
        txtContact.setText(customerTm.getContact());
        txtSalary.setText(String.valueOf(customerTm.getSalary()));
        btnSaveCustomer.setText("Update Customer");

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void manageLoyalatiCardOnAction(ActionEvent actionEvent) {
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        btnSaveCustomer.setText("Save Customer");
        clearForm();
        txtEmail.setEditable(true);
    }

    public void saveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(btnSaveCustomer.getText().equals("Save Customer")){
            if(customerBo.saveCustomer(new CustomerDto(txtEmail.getText(),txtName.getText(),txtContact.getText(),Double.parseDouble(txtSalary.getText())))){
                new Alert(Alert.AlertType.INFORMATION,"Customer Saved!").show();
                clearForm();
                setCustomerData(searchText);
            }else{
                new Alert(Alert.AlertType.ERROR,"Try Again!").show();
            }
        }else {
            if(customerBo.updateCustomer(new CustomerDto( txtEmail.getText(),txtName.getText(),txtContact.getText(),Double.parseDouble(txtSalary.getText())))){
                new Alert(Alert.AlertType.INFORMATION,"Customer Updated!").show();
                clearForm();
                setCustomerData(searchText);
                txtEmail.setEditable(true);
                btnSaveCustomer.setText("Save Customer");
            }else{
                new Alert(Alert.AlertType.ERROR,"Try Again!").show();
            }
        }




    }

    private void setCustomerData(String searchText) {
        try {
            ObservableList<CustomerTm> oblist = FXCollections.observableArrayList();
            int count = 1;
            for (CustomerDto cus:searchText.length()==0?customerBo.findAllCustomers():customerBo.searchCustomer(searchText)
            ) {
                Button btn = new Button("Delete");
                CustomerTm tm = new CustomerTm(
                        count, cus.getEmail(),cus.getName(),cus.getContact(),cus.getSalary(),btn
                );
                count++;
                oblist.add(tm);

                btn.setOnAction((e) -> {
                    Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO).showAndWait();
                    if(buttonType.get().equals(ButtonType.YES)){
                        try {
                            if(customerBo.deleteCustomer(cus.getEmail())){
                                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                                setCustomerData(searchText);
                            }
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });


            }
            tblCustomer.setItems(oblist);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void clearForm() {
        txtEmail.clear();
        txtName.clear();
        txtContact.clear();
        txtSalary.clear();
    }

    public void searchHereOnAction(ActionEvent actionEvent) {
    }
    // ------------------------------------------------setUi Method ----------------------------------------------------
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

    // ------------------------------------------------setUi Method ----------------------------------------------------
}
