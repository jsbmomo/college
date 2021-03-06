package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FingerPrint {
	
	@FXML private Button searchInfor;
	@FXML private Button exitPage;
	@FXML private TextField inputDate;
	@FXML private Label statusInfor;
	
	public void SearchInfor(ActionEvent evnet) {
		try {
			System.out.println("search information");
			
			String fingerData = inputDate.getText();
			ServerConnect server = new ServerConnect();
			String sendString = "Finger/"  + fingerData + "\0";
			boolean judge;
			
			// 서버에 입력값을 보냄
			judge = server.sendData(sendString);
			// 현재 환자의 지문데이터를 저장
			PassString.setFingerData(fingerData);  
			
			if(judge) System.out.println("지문데이터를 서버에 데이터 전송하였습니다");
			else System.out.println("지문데이터 전송에 문제가 발생하였습니다.");
		
			// 서버로부터 데이터를 받게 될때(응답) 
			// 받은 데이터를 분할하여 서버가 접근을 허가했는지 판단
			String receiveData = server.receiveData();
			
			// 서버로 부터 데이터를 받고, 해당 데이터가 맞는지 비교.
			String[] userData = receiveData.split("/");
			
			boolean dataAccess = false;
			if(userData[0].equals("exist")) {
				dataAccess = true;
				
				System.out.println("Exist Patient Data");
				
				// 서버로부터 받은 값을 화면을 출력할 객체에 전달
				PassString.setReceiveData(userData[1]);  
				
				
				Parent fingerPage = FXMLLoader.load(getClass().getResource("/application/PatientView.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(fingerPage));
				stage.setTitle("Patient Register");
				stage.show();
			}
			else {
				dataAccess = false;
				
				new AlertPage("등록되지 않은 환자입니다.", 1, null);
			}
			
			System.out.println(dataAccess);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exitButton(ActionEvent evnet) {
		try {
			Stage stage = (Stage) exitPage.getScene().getWindow();
			stage.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
