package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrintTest  {

	public static void showAgreement()throws FileNotFoundException {
	      Button btnPrint = new Button();
	     // ScrollPane sp = new ScrollPane();
	      //VBox box = new VBox();
	        
	      // 스크롤 바 설정 
	      ScrollBar scroll = new ScrollBar();
	      scroll.setMin(0);
	      scroll.setOrientation(Orientation.VERTICAL);
	      scroll.setLayoutX(680);
	      scroll.setLayoutY(10);
	      scroll.setPrefHeight(720);
	      scroll.setPrefWidth(20);
	     
	      // 이미지 가져오기
	      InputStream stream = new FileInputStream("src/img/Agreement.png");
	      Image image = new Image(stream);
	      ImageView imageView = new ImageView();
	     
	      // 이미지 세팅
	      imageView.setImage(image);
	      imageView.setX(10);
	      imageView.setY(10);
	      imageView.setFitWidth(680);
	      //imageView.setFitHeight(730);
	      imageView.setPreserveRatio(true);
	      
	      btnPrint.setLayoutX(305);
	      btnPrint.setLayoutY(740);
	      btnPrint.setPrefHeight(40);
	      btnPrint.setPrefWidth(90);
	      
	      // VBox 세팅 및 스트롤 설정 
	      VBox vBox = new VBox(5);
	      vBox.getChildren().addAll(imageView);
	      scroll.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
	         vBox.setLayoutY(-new_val.doubleValue());
	         
	      });
	     
	      
	      // stage
	      Stage stage = new Stage();
	      Group root = new Group();
	      root.getChildren().addAll(vBox, scroll); // 기존에 작성한 UI 가져옴 
	      Scene scene = new Scene(root, 700, 800);
	      stage.setTitle("Scroll Bar Example");
	      stage.setScene(scene);
		  stage.setResizable(false);
	      stage.show();
	}
}
