package com.devstack.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane context;

    public void customerManagmentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");
    }

    public void productManagmentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("ProductMainForm");
    }

    public void plactOrderOnAction(ActionEvent actionEvent) {
    }

    public void orderDetailsOnAction(ActionEvent actionEvent) {
    }

    public void incomeReportOnAction(ActionEvent actionEvent) {
    }
    // ------------------------------------------------setUi Method ----------------------------------------------------
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

    // ------------------------------------------------setUi Method ----------------------------------------------------
}
