package com.devstack.pos.controller;

import com.devstack.pos.bo.BoFactory;
import com.devstack.pos.bo.custom.ProductDetailBo;
import com.devstack.pos.dto.ProductDetailDto;
import com.devstack.pos.enums.BoType;
import com.devstack.pos.util.QrDataGenerator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.codec.binary.Base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class NewBatchFormController {
    public AnchorPane context;
    public ImageView barcodeImage;
    public TextField txtQty;
    public TextField txtSellingPrice;
    public TextField txtShowPrice;
    public TextField txtBuyingPrice;
    public TextField txtProductID;
    public TextField txtProductDescription;
    public RadioButton rbtnYes;
    String uniqueData = null;
    BufferedImage bufferedImage = null;
    Stage stage = null;

    private ProductDetailBo productDetailBo = BoFactory.getInstance().getBo(BoType.PRODUCT_DETAILS);

    public void initialize() throws WriterException {
        setQRCode();
    }

    private void setQRCode() throws WriterException {
        uniqueData = QrDataGenerator.generate(25);
        //=======================Generating QR code==================================
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        bufferedImage = MatrixToImageWriter.toBufferedImage(qrCodeWriter.encode(uniqueData,
                BarcodeFormat.QR_CODE,160,160));
        //=======================Generating QR code==================================
        Image image = SwingFXUtils.toFXImage(bufferedImage,null);
        barcodeImage.setImage(image);

    }
    public void setProductCode(int code,String description, Stage stage){
        txtProductID.setText(String.valueOf(code));
        txtProductDescription.setText(description);
        this.stage = stage;
    }

    public void saveBatchOnAction(ActionEvent actionEvent) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(bufferedImage,"png",byteArrayOutputStream);
        byte[] arr = byteArrayOutputStream.toByteArray();
        ProductDetailDto productDetailDto = new ProductDetailDto(
                uniqueData, Base64.encodeBase64String(arr),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtSellingPrice.getText()),rbtnYes.isSelected()?true:false,
                Double.parseDouble(txtShowPrice.getText()), Integer.parseInt(txtProductID.getText()), Double.parseDouble(txtBuyingPrice.getText())
        );
        try {
            if(productDetailBo.saveProductDetails(productDetailDto)){
                new Alert(Alert.AlertType.INFORMATION,"Product Details Saved!").show();
                Thread.sleep(3000);
                this.stage.close();
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again!").show();


            }
        } catch (SQLException | ClassNotFoundException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
