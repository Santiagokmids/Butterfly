package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
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

	@FXML
	private ImageView eeuuImg;

	@FXML
	private ImageView colImg;

	@FXML
	private ImageView rusImg;

	@FXML
	private ImageView espImg;

	@FXML
	private ImageView nigImg;

	@FXML
	private ImageView madImg;

	@FXML
	private ImageView porImg;

	@FXML
	private ImageView japImg;

	@FXML
	private ImageView dubImg;

	@FXML
	private ImageView ausImg;

	@FXML
	private Label countryLabel;

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

		Image ubi = new Image("/images/ubication.png");
		eeuuImg.setImage(ubi);
		espImg.setImage(ubi);
		japImg.setImage(ubi);
		colImg.setImage(ubi);
		nigImg.setImage(ubi);
		porImg.setImage(ubi);
		madImg.setImage(ubi);
		rusImg.setImage(ubi);
		ausImg.setImage(ubi);
		dubImg.setImage(ubi);

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
	public void emptyLabel(MouseEvent event) {
		countryLabel.setText("");
	}

	@FXML
	public void showLabel(MouseEvent event) {
		ImageView image = (ImageView) event.getSource();
		String id = image.getId();

		switch (id) {
		case "eeuuImg":
			countryLabel.setText("Estados Unidos");
			break;
		case "espImg":
			countryLabel.setText("España");
			break;
		case "colImg":
			countryLabel.setText("Colombia");
			break;
		case "dubImg":
			countryLabel.setText("Dubai");
			break;
		case "ausImg":
			countryLabel.setText("Australia");
			break;
		case "japImg":
			countryLabel.setText("Japón");
			break;
		case "nigImg":
			countryLabel.setText("Nigeria");
			break;
		case "madImg":
			countryLabel.setText("Madagascar");
			break;
		case "porImg":
			countryLabel.setText("Portugal");
			break;
		case "rusImg":
			countryLabel.setText("Rusia");
			break;

		default:
			break;
		}
	}

	public void addLines(String initVertice, String finalVertice) {
		
		switch (initVertice) {
		
		case "Estados Unidos":
			addLine(eeuuImg, finalVertice);
			break;
		case "España":
			addLine(espImg, finalVertice);
			break;
		case "Japón":
			addLine(japImg, finalVertice);
			break;
		case "Colombia":
			addLine(colImg, finalVertice);
			break;
		case "Nigeria":
			addLine(nigImg, finalVertice);
			break;
		case "Portugal":
			addLine(porImg, finalVertice);
			break;
		case "Rusia":
			addLine(rusImg, finalVertice);
			break;
		case "Dubai":
			addLine(dubImg, finalVertice);
			break;
		case "Madagascar":
			addLine(madImg, finalVertice);
			break;
		case "Australia":
			addLine(ausImg, finalVertice);
			break;
		default:
			break;
		}
	}
	
	public void addLine(ImageView image, String finalVertice) {
		
		switch (finalVertice) {
		
		case "Estados Unidos":
			addLine(image, eeuuImg);
			break;
		case "España":
			addLine(image, espImg);
			break;
		case "Japón":
			addLine(image, japImg);
			break;
		case "Colombia":
			addLine(image, colImg);
			break;
		case "Nigeria":
			addLine(image, nigImg);
			break;
		case "Portugal":
			addLine(image, porImg);
			break;
		case "Rusia":
			addLine(image, rusImg);
			break;
		case "Dubai":
			addLine(image, dubImg);
			break;
		case "Madagascar":
			addLine(image, madImg);
			break;
		case "Australia":
			addLine(image, ausImg);
			break;
		default:
			break;
		}
	}
	
	public void addLine(ImageView image, ImageView image2) {
		
		Line line = new Line(image.getLayoutX() + 35,image.getLayoutY() + 45,image2.getLayoutX() + 10,image2.getLayoutY()+40);
		line.setStrokeWidth(3);
		mainPane.getChildren().add(line);
	}

	@FXML
	public void closeApp(MouseEvent event) {
		System.exit(0);
	}
}
