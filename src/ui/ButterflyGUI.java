package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.Butterfly;

public class ButterflyGUI {
	
    @FXML
    private ImageView imgMap;
	
	@FXML
    private ImageView imgExit;

	@FXML
	private ImageView imgButtefly;

	@FXML
	private BorderPane mainPane;

	@FXML
	private Button btnConfig;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnCost;

	boolean btnConfigVerify = true;
	boolean btnSearchVerify = true;
	boolean btnCostVerify = true;

	Butterfly butterfly;

	public ButterflyGUI(Butterfly butterfly) {
		this.butterfly = butterfly;
	}

	@FXML
	public void loadApp() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("startApp.fxml"));
		loader.setController(this);
		Parent load = loader.load();
		mainPane.getChildren().clear();

		Image tittle = new Image("/images/Butterfly.png");
		imgButtefly.setImage(tittle);
		Image exit = new Image("/images/end.png");
		imgExit.setImage(exit);
		Image map = new Image("/images/map.png");
		imgMap.setImage(map);

		mainPane.setTop(load);

	}

	@FXML
	public void handleClicks(ActionEvent event) {

		Button button = (Button) event.getSource();
		String optionString = button.getText();
		String styleString = "-fx-background-color: rgb(24,135,128);";
		DropShadow dropShadow = new DropShadow();

		changeAllButton();

		switch (optionString) {
		case "Administrar vuelo":
			btnConfig.setStyle(styleString);
			btnConfig.setEffect(dropShadow);
			btnConfigVerify = false;
			break;
		case "Buscar Vuelo":
			btnSearch.setStyle(styleString);
			btnSearch.setEffect(dropShadow);
			btnSearchVerify = false;
			break;
		case "Costo Mínimo":
			btnCost.setStyle(styleString);
			btnCost.setEffect(dropShadow);
			btnCostVerify = false;
			break;
		default:
			break;
		}
	}

	private void changeAllButton() {
		btnConfigVerify = btnSearchVerify = btnCostVerify = true;

		changeStyle(btnConfig.getText());
		changeStyle(btnSearch.getText());
		changeStyle(btnCost.getText());
	}

	private void changeStyle(String button) {

		String style = "-fx-background-color: rgb(32,178,170);";

		switch (button) {
		case "Administrar vuelo":
			if (btnConfigVerify) {
				btnConfig.setStyle(style);
				btnConfig.setEffect(null);
			}			
			break;
		case "Buscar Vuelo":
			if (btnSearchVerify) {
				btnSearch.setStyle(style);
				btnSearch.setEffect(null);
			}
			break;
		case "Costo Mínimo":
			if (btnCostVerify) {
				btnCost.setStyle(style);
				btnCost.setEffect(null);
			}
			break;
		default:
			break;
		}
	}

	@FXML
	public void normalColor(MouseEvent event) {
		Button button = (Button) event.getSource();
		String s = button.getText();
		changeStyle(s);
	}

	@FXML
	public void color(MouseEvent event) {

		Button button = (Button) event.getSource();
		String s = button.getText();
		String style = "-fx-background-color: rgb(24,135,128);";
		DropShadow dropShadow = new DropShadow();

		switch (s) {
		case "Administrar vuelo":
			if (btnConfigVerify) {
				btnConfig.setStyle(style);
				btnConfig.setEffect(dropShadow);
			}	
			break;
		case "Buscar Vuelo":
			if (btnSearchVerify) {
				btnSearch.setStyle(style);
				btnSearch.setEffect(dropShadow);
			}
			break;
		case "Costo Mínimo":
			if (btnCostVerify) {
				btnCost.setStyle(style);
				btnCost.setEffect(dropShadow);
			}
			break;
		default:
			break;
		}
	}
	
	@FXML
    public void closeApp(MouseEvent event) {
		System.exit(0);
    }
}
