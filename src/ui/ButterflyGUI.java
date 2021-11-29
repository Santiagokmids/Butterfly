package ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import dataStructure.ListVertice;
import dataStructure.Vertice;

import java.awt.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Butterfly;

public class ButterflyGUI {

	@FXML
	private ImageView imgPhoto;

	@FXML
	private Label lblCountry;

	@FXML
	private TableView<?> tvFlight;

	@FXML
	private TableColumn<?, ?> tcCountry;

	@FXML
	private ComboBox<String> searchCountryOrigin;

	@FXML
	private ComboBox<String> searchCountryDestiny;

	@FXML
	private Label lblInformation;

	@FXML
	private Hyperlink linkInformation;

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
	
    @FXML
    private Label graphLabel;

	boolean typeGraph = false; //If is false = listGraph. If is true = matrixGraph
	boolean btnConfigVerify = true;
	boolean btnSearchVerify = true;
	boolean btnCostVerify = true;

	Butterfly butterfly;

	//Country photos

	Image colombiaImage = new Image("/images/colombia.jpg");
	Image espaniaImage = new Image("/images/españa.jpg");
	Image japonImage = new Image("/images/japon.jpg");
	Image eeuuImage = new Image("/images/eeuu.jpg");
	Image rusiaImage = new Image("/images/rusia.jpg");
	Image australiaImage = new Image("/images/australia.jpg");
	Image nigeriaImage = new Image("/images/nigeria.jpg");
	Image dubaiImage = new Image("/images/dubai.jpg");
	Image portugalImage = new Image("/images/portugal.jpg");
	Image madagascarImage = new Image("/images/madagascar.jpg"); 

	//Source of the information

	String link = "https://es.wikipedia.org/";

	//Information of the countries

	String colombiaInformation = "Es un país soberano situado en la región noroccidental de América del Sur, que se constituye en un estado unitario, social y democrático de derecho cuya forma de gobierno es presidencialista.";
	String espaniaInformation = "Es un país soberano transcontinental, miembro de la Unión Europea, constituido en Estado social y democrático de derecho, cuya forma de gobierno es la monarquía parlamentaria. ";
	String japonInformation = " Es un país insular de Asia Oriental ubicado en el noroeste del océano Pacífico. Limita con el mar de Japón al oeste y se extiende desde el mar de Ojotsk en el norte hasta el mar de China Oriental y Taiwán en el sur.";
	String eeuuInformation = "Es un país soberano constituido en una república federal constitucional compuesta por cincuenta estados y un distrito federal.";
	String rusiaInformation = "Es un país que se extiende sobre Europa del Este y Asia del Norte. Es el país más extenso del mundo, equivalente a la novena parte de la tierra firme del planeta,";
	String australiaInformation = "Es un país soberano de Oceanía, cuya forma de gobierno es la monarquía constitucional federal parlamentaria.";
	String nigeriaInformation = "Es un país de África occidental, que limita con Níger al norte, con Chad en el nordeste, con Camerún en el este y con Benín en el oeste.";
	String dubaiInformation = "Es uno de los siete emiratos que conforman los Emiratos Árabes Unidos, cuya capital es la ciudad homónima. Está situado en la costa del golfo Pérsico, en el desierto de Arabia";
	String portugalInformation = "Es un país transcontinental. La mayor parte de su territorio, con capital en Lisboa, está ubicado en el suroeste de Europa, en la península ibérica.";
	String madagascarInformation = "Es un país insular situado en el océano Índico, frente a la costa sureste del continente africano, al este de Mozambique.";

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
	public void handleClicks(ActionEvent event) throws IOException {

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
			searchTrip();
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

	public void searchTrip() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("searchTrip.fxml"));
		loader.setController(this);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		Stage stage = new Stage();

		searchCountryOrigin.getItems().addAll("Colombia","Japón","Estados Unidos","España","Nigeria","Australia","Rusia","Dubái","Madagascar");
		searchCountryDestiny.getItems().addAll("Colombia","Japón","Estados Unidos","España","Nigeria","Australia","Rusia","Dubái","Madagascar");
		stage.setScene(scene);
		stage.showAndWait();
	}

	@FXML
	public void searchTrip(ActionEvent event) {
		String origin = searchCountryOrigin.getValue();
		String destiny = searchCountryDestiny.getValue();
		String country = "";

		if(!typeGraph) {
			ArrayList<ListVertice<String, String, Integer>> countries = butterfly.dfsInList(origin);
			for (int i = 0; i < countries.size(); i++) {
				if(destiny.equalsIgnoreCase(countries.get(i).getValue())) {
					country = destiny;
				}
			}
		}else {
			ArrayList<Vertice<String, String, Integer>> countries = butterfly.dfsInMatrix(origin);
			for (int i = 0; i < countries.size(); i++) {
				if(destiny.equalsIgnoreCase(countries.get(i).getValue())) {
					country = destiny;
				}
			}
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Búsqueda realizada");

		if(country.equals("")) {
			alert.setHeaderText("No encontrado");
			alert.setContentText("No existe un vuelo entre "+origin+" y "+destiny);
			alert.showAndWait();

		}else {
			alert.setHeaderText("Se ha encontrado");
			alert.setContentText("Sí existe un vuelo entre "+origin+" y "+destiny);
			alert.showAndWait();
		}

	}

	@FXML
	public void changeGraph(ActionEvent event) {
		if(!typeGraph) {
			graphLabel.setText("Grafo representado por matriz de adjacencia");
			typeGraph = true;
			
		}else {
			graphLabel.setText("Grafo representado por lista de adjacencia");
			typeGraph = false;
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

	@FXML
	void btnInformation(MouseEvent event) throws IOException {
		ImageView image = (ImageView) event.getSource();
		String id = image.getId();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("informationPane.fxml"));
		loader.setController(this);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		linkInformation.setText("https://es.wikipedia.org/");
		linkInformation.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Desktop.getDesktop().browse(new URI("https://es.wikipedia.org/"));
				} catch (IOException | URISyntaxException e) {
				}
			}
		});

		switch (id) {
		case "eeuuImg":
			imgPhoto.setImage(eeuuImage);
			lblCountry.setText("Estados Unidos");
			lblInformation.setText(eeuuInformation);

			break;
		case "espImg":
			imgPhoto.setImage(espaniaImage);
			lblCountry.setText("España");
			lblInformation.setText(espaniaInformation);

			break;
		case "colImg":
			imgPhoto.setImage(colombiaImage);
			lblCountry.setText("Colombia");
			lblInformation.setText(colombiaInformation);

			break;
		case "dubImg":
			imgPhoto.setImage(dubaiImage);
			lblCountry.setText("Dubái");
			lblInformation.setText(dubaiInformation);

			break;
		case "ausImg":
			imgPhoto.setImage(australiaImage);
			lblCountry.setText("Australia");
			lblInformation.setText(australiaInformation);

			break;
		case "japImg":
			imgPhoto.setImage(japonImage);
			lblCountry.setText("Japón");
			lblInformation.setText(japonInformation);

			break;
		case "nigImg":
			imgPhoto.setImage(nigeriaImage);
			lblCountry.setText("Nigeria");
			lblInformation.setText(nigeriaInformation);

			break;
		case "madImg":
			imgPhoto.setImage(madagascarImage);
			lblCountry.setText("Madagascar");
			lblInformation.setText(madagascarInformation);

			break;
		case "porImg":
			imgPhoto.setImage(portugalImage);
			lblCountry.setText("Portugal");
			lblInformation.setText(portugalInformation);

			break;
		case "rusImg":
			imgPhoto.setImage(rusiaImage);
			lblCountry.setText("Rusia");
			lblInformation.setText(rusiaInformation);

			break;

		default:
			break;
		}

		stage.setScene(scene);
		stage.showAndWait();
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
