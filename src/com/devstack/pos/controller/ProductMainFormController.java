package com.devstack.pos.controller;

import com.beust.ah.A;
import com.devstack.pos.bo.ProductBo;
import com.devstack.pos.bo.custom.impl.ProductBoImpl;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.view.tm.ProductTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class ProductMainFormController {
    public AnchorPane context;
    public JFXTextField txtProductCode;
    public TextField txtDescription;
    public TextField txtSearchProduct;
    public TableView<ProductTm> tblProduct;
    public TableColumn colID;
    public TableColumn colDescription;
    public TableColumn colShowMore;
    public TableColumn colDelete;
    public TextField txtSelectedProdDescription;
    public TextField txtSelectedProdID;
    public JFXButton btnNwwBatch;

    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colShowMore.setCellValueFactory(new PropertyValueFactory<>("showMore"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));


        try {
            loadAllProduct();
            txtProductCode.setText(String.valueOf(new ProductBoImpl().productIDGenerator()) );
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        tblProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
         setData(newValue));
    }

    private void setData(ProductTm newValue) {
        txtSelectedProdID.setText(String.valueOf(newValue.getCode()));
        txtSelectedProdDescription.setText(newValue.getDescription());
        btnNwwBatch.setDisable(false);

    }

    private void loadAllProduct() throws SQLException, ClassNotFoundException {
        ObservableList<ProductTm> oblist = FXCollections.observableArrayList();
        for (ProductDto p: new ProductBoImpl().findAllProducts()
             ) {
            Button showMore = new Button("Show more");
            Button delete = new Button("Delete");
            ProductTm tm = new ProductTm(
                    p.getCode(),p.getDescription(),showMore,delete
            );
            oblist.add(tm);

        }
        tblProduct.setItems(oblist);
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void newProductOnAction(ActionEvent actionEvent) {
    }

    public void saveProductOnAction(ActionEvent actionEvent) {
        try {
            if(new  ProductBoImpl().saveProduct(new ProductDto( Integer.parseInt(txtProductCode.getText()),txtDescription.getText()))){
                new Alert(Alert.AlertType.INFORMATION,"Product Saved!").show();
                loadAllProduct();
                clearForm();
            }
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private void clearForm() {
        try {
            txtProductCode.setText(String.valueOf(new ProductBoImpl().productIDGenerator()));
            txtDescription.clear();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
    }

    // ------------------------------------------------setUi Method ----------------------------------------------------
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }

    public void newBatchOnAction(ActionEvent actionEvent) throws IOException {


       if(!txtSelectedProdID.getText().isEmpty()){
           Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/NewBatchForm.fxml"));
           Parent parent = fxmlLoader.load();
           NewBatchFormController controller = fxmlLoader.getController();
            controller.setProductCode(Integer.parseInt(txtSelectedProdID.getText()),txtSelectedProdDescription.getText(),stage);
            stage.setScene(new Scene(parent));
            stage.show();
            stage.centerOnScreen();

        }else {
            new Alert(Alert.AlertType.WARNING,"Please select a valid one").show();
        }

    }

    // ------------------------------------------------setUi Method ----------------------------------------------------
}
