package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.UserBo;
import com.devstack.pos.dto.UserDto;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.util.PasswordManager;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;



public class SignUpFormController {

    public AnchorPane context;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    public void registerOnAction(ActionEvent actionEvent) {
        try {
            if(userBo.saveUser(new UserDto( txtEmail.getText(), PasswordManager.encryptPassword(txtPassword.getText()) ))){
                new Alert(Alert.AlertType.INFORMATION,"User Saved!").show();
                clearForm();
                setUi("LoginForm");
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again!").show();
            }
    }catch (SQLException| ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // ------------------------------------------------Form clearing Method ----------------------------------------------------
    private void clearForm() {
        txtEmail.clear();
        txtPassword.clear();
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    // ------------------------------------------------setUi Method ----------------------------------------------------
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();

    }
}
