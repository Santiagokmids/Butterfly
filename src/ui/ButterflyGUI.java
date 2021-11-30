package ui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dataStructure.ListVertice;
import dataStructure.Vertice;

import java.awt.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private ComboBox<String> cbOrigin;

	@FXML
	private ComboBox<String> cbDestination;

	@FXML
	private TextField tfCost;

	@FXML
	private Button btnAdd;

	@FXML
	private Button btnDelete;

	@FXML
	private Button btnModify;

	@FXML
	private ImageView imgPhoto;

	@FXML
	private ComboBox<String> countryIniMin;

	@FXML
	private ComboBox<String> countryFinalMin;

	@FXML
	private Label lblCountry;

	@FXML
	private TableView<Vertice<String, String, Integer>> tvFlight;

	@FXML
	private TableColumn<Vertice<String, String, Integer>, String> tcCountry;

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
	
	public ObservableList<Vertice<String,String,Integer>> vertice;
	
	public ArrayList<Line> lines = new ArrayList<Line>();

	boolean typeGraph = true; //If is false = listGraph. If is true = matrixGraph
	boolean btnConfigVerify = true;
	boolean btnSearchVerify = true;
	boolean btnCostVerify = true;
	boolean btnAddVerify = true;
	boolean btnDeleteVerify = true;
	boolean btnModifyVerify = true;

	Butterfly butterfly;

	// Country photos

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

	// Source of the information

	String link = "https://es.wikipedia.org/";

	// Information of the countries

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
		butterfly.createGraphs();
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

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("addFlight.fxml"));
				loader.setController(this);
				Parent root;

				root = loader.load();

				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);

				cbOrigin.getItems().clear();
				cbDestination.getItems().clear();

				cbOrigin.getItems().addAll("Colombia", "Japón", "Estados Unidos", "España", "Nigeria", "Australia", "Portugal",
						"Rusia", "Dubái", "Madagascar");
				cbDestination.getItems().addAll("Colombia", "Japón", "Estados Unidos", "España", "Nigeria", "Australia", "Portugal",
						"Rusia", "Dubái", "Madagascar");

				stage.setScene(scene);
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}

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
			loadMin();
			break;
		default:
			break;
		}
	}

	@FXML
	public void handleClicksTwo(ActionEvent event) {
		Button button = (Button) event.getSource();
		String optionString = button.getText();
		String styleString = "-fx-background-color: rgb(24,135,128);";
		DropShadow dropShadow = new DropShadow();

		changeAllButtonTwo();

		switch (optionString) {
		case "Agregar":
			btnAdd.setStyle(styleString);
			btnAdd.setEffect(dropShadow);
			btnAddVerify = false;

			addFlight();

			break;
		case "Eliminar":
			btnDelete.setStyle(styleString);
			btnDelete.setEffect(dropShadow);
			btnDeleteVerify = false;
			
			deleteFlight();
			
			break;
		case "Modificar":
			btnModify.setStyle(styleString);
			btnModify.setEffect(dropShadow);
			btnModifyVerify = false;
			
			modifyFlight();
			
			break;
		default:
			break;
		}
	}
	
	private void modifyFlight() {
		try {
			Integer newInteger = Integer.parseInt(tfCost.getText());
			
			if (newInteger > 0) {
				
				if (cbOrigin.getValue() == null || cbDestination.getValue() == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Error de vuelos");
					alert.setContentText("Debe seleccionar los dos paises.");
					alert.showAndWait();
				}else {
					if (!cbOrigin.getValue().equals(cbDestination.getValue())) {
						
						boolean verify = butterfly.searchFlight(cbOrigin.getValue(), cbDestination.getValue(), typeGraph);
						
						if (!verify) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setHeaderText("Error de vuelos");
							alert.setContentText("Los países seleccionados no poseen un vuelo activo");
							alert.showAndWait();
							
							cbOrigin.setValue(null);
							cbDestination.setValue(null);
							tfCost.setText("");
							
						}else {
							
							boolean deleted = butterfly.modify(cbOrigin.getValue(), cbDestination.getValue(), newInteger);
							
							if (deleted) {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Felicidades");
								alert.setHeaderText("Vuelo modificado");
								alert.setContentText("El vuelo entre los dos países fue modificado correctamente.");
								alert.showAndWait();
							}else {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("ERROR");
								alert.setHeaderText("Error de vuelos");
								alert.setContentText("Ocurrió un error inesperado al intentar modificar el vuelo.");
								alert.showAndWait();
							}
						}
					}else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setHeaderText("Error de vuelos");
						alert.setContentText("El vuelo no puede ir hacia el mismo país.");
						alert.showAndWait();
					}
				}
				
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error de formato");
				alert.setContentText("El costo debe ser un valor numérico positivo.");
				alert.showAndWait();
			}
			
		} catch (NumberFormatException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error de formato");
			alert.setContentText("El costo debe ser un valor numérico.");
			alert.showAndWait();
		}
	}

	private void deleteFlight() {
		try {
			Integer newInteger = Integer.parseInt(tfCost.getText());
			
			if (newInteger > 0) {
				
				if (cbOrigin.getValue() == null || cbDestination.getValue() == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Error de vuelos");
					alert.setContentText("Debe seleccionar los dos paises.");
					alert.showAndWait();
				}else {
					if (!cbOrigin.getValue().equals(cbDestination.getValue())) {
						
						boolean verify = butterfly.searchFlight(cbOrigin.getValue(), cbDestination.getValue(), newInteger, typeGraph);
						
						if (!verify) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setHeaderText("Error de vuelos");
							alert.setContentText("Los países seleccionados no poseen un vuelo activo");
							alert.showAndWait();
							
							cbOrigin.setValue(null);
							cbDestination.setValue(null);
							tfCost.setText("");
							
						}else {
							
							boolean deleted = butterfly.delete(cbOrigin.getValue(), cbDestination.getValue(), newInteger);
							
							if (deleted) {
								Alert alert = new Alert(AlertType.INFORMATION);
								addLines(cbOrigin.getValue(), cbDestination.getValue(), 1);
								alert.setTitle("Felicidades");
								alert.setHeaderText("Vuelo eliminado");
								alert.setContentText("El vuelo entre los dos países fue eliminado correctamente.");
								alert.showAndWait();
							}else {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("ERROR");
								alert.setHeaderText("Error de vuelos");
								alert.setContentText("Ocurrió un error inesperado al intentar eliminar el vuelo.");
								alert.showAndWait();
							}
						}
					}else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setHeaderText("Error de vuelos");
						alert.setContentText("El vuelo no puede ir hacia el mismo país.");
						alert.showAndWait();
					}
				}
				
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error de formato");
				alert.setContentText("El costo debe ser un valor numérico positivo.");
				alert.showAndWait();
			}
			
		} catch (NumberFormatException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error de formato");
			alert.setContentText("El costo debe ser un valor numérico.");
			alert.showAndWait();
		}
		
	}

	private void addFlight() {

		try {
			Integer newInteger = Integer.parseInt(tfCost.getText());

			if (newInteger > 0) {
				
				if (cbOrigin.getValue() == null || cbDestination.getValue() == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Error de vuelos");
					alert.setContentText("Debe seleccionar los dos paises.");
					alert.showAndWait();
				}else {
					if (!cbOrigin.getValue().equals(cbDestination.getValue())) {
						
						boolean verify = butterfly.searchFlight(cbOrigin.getValue(), cbDestination.getValue(), newInteger, typeGraph);
						
						if (verify) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setHeaderText("Error de vuelos");
							alert.setContentText("Los países seleccionados ya poseen un vuelo activo");
							alert.showAndWait();
							
							cbOrigin.setValue(null);
							cbDestination.setValue(null);
							tfCost.setText("");
						}else {
							
							boolean added = butterfly.add(cbOrigin.getValue(), cbDestination.getValue(), newInteger);
							
							if (added) {
								addLines(cbOrigin.getValue(), cbDestination.getValue(), 0);
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Felicidades");
								alert.setHeaderText("Vuelo agregado");
								alert.setContentText("El vuelo entre los dos países fue agregado correctamente.");
								alert.showAndWait();
								
							}else {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("ERROR");
								alert.setHeaderText("Error de vuelos");
								alert.setContentText("Ocurrió un error inesperado al intentar agregar el vuelo.");
								alert.showAndWait();
							}
						}
					}else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setHeaderText("Error de vuelos");
						alert.setContentText("El vuelo no puede ir hacia el mismo país.");
						alert.showAndWait();
					}
				}
				
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("Error de formato");
				alert.setContentText("El costo debe ser un valor numérico positivo.");
				alert.showAndWait();
			}

		} catch (NumberFormatException e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Error de formato");
			alert.setContentText("El costo debe ser un valor numérico.");
			alert.showAndWait();
		}

	}

	private void changeAllButtonTwo() {

		btnAddVerify = btnDeleteVerify = btnModifyVerify = true;

		changeStyle(btnAdd.getText());
		changeStyle(btnDelete.getText());
		changeStyle(btnModify.getText());
	}

	public void searchTrip() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("searchTrip.fxml"));
		loader.setController(this);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		Stage stage = new Stage();

		searchCountryOrigin.getItems().addAll("Colombia", "Japón", "Estados Unidos", "España", "Nigeria", "Australia","Portugal",
				"Rusia", "Dubái", "Madagascar");
		searchCountryDestiny.getItems().addAll("Colombia", "Japón", "Estados Unidos", "España", "Nigeria", "Australia","Portugal",
				"Rusia", "Dubái", "Madagascar");
		stage.setScene(scene);
		stage.showAndWait();
	}

	@FXML
	public void searchTrip(ActionEvent event) {
		String origin = searchCountryOrigin.getValue();
		String destiny = searchCountryDestiny.getValue();
		String country = "";

		if (typeGraph) {
			if (!typeGraph) {
				ArrayList<ListVertice<String, String, Integer>> countries = butterfly.bfsInList(origin);
				countries.remove(0);
				for (int i = 0; i < countries.size(); i++) {
					if (destiny.equalsIgnoreCase(countries.get(i).getValue())) {
						country = destiny;
					}
				}
			} else {
				ArrayList<Vertice<String, String, Integer>> countries = butterfly.bfsInMatrix(origin);
				countries.remove(0);
				for (int i = 0; i < countries.size(); i++) {
					if (destiny.equalsIgnoreCase(countries.get(i).getValue())) {
						country = destiny;
					}
				}
			}

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Búsqueda realizada");

			if (country.equals("")) {
				alert.setHeaderText("No encontrado");
				alert.setContentText("No existe un vuelo entre " + origin + " y " + destiny);
				alert.showAndWait();

			} else {
				alert.setHeaderText("Se ha encontrado");
				alert.setContentText("Sí existe un vuelo entre " + origin + " y " + destiny);
				alert.showAndWait();
			}
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
		case "Agregar":
			if (btnAddVerify) {
				btnAdd.setStyle(style);
				btnAdd.setEffect(null);
			}
			break;
		case "Eliminar":
			if (btnDeleteVerify) {
				btnDelete.setStyle(style);
				btnDelete.setEffect(null);
			}
			break;
		case "Modificar":
			if (btnModifyVerify) {
				btnModify.setStyle(style);
				btnModify.setEffect(null);
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
		case "Agregar":
			if (btnAddVerify) {
				btnAdd.setStyle(style);
				btnAdd.setEffect(dropShadow);
			}
			break;
		case "Eliminar":
			if (btnDeleteVerify) {
				btnDelete.setStyle(style);
				btnDelete.setEffect(dropShadow);
			}
			break;
		case "Modificar":
			if (btnModifyVerify) {
				btnModify.setStyle(style);
				btnModify.setEffect(dropShadow);
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
		
		inicializateTableView(lblCountry.getText());
		
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	public void inicializateTableView(String lbl) {
		
		vertice = FXCollections.observableArrayList(butterfly.bfsInMatrix(lbl));
		for (int i = 0; i < vertice.size(); i++) {
			if(vertice.get(i).getValue().equals(lbl)) {
				vertice.remove(i);
			}
		}
		tvFlight.setItems(vertice);
		tcCountry.setCellValueFactory(new PropertyValueFactory<Vertice<String, String, Integer>, String>("value"));
		
	}

	public void addLines(String initVertice, String finalVertice, int number) {

		switch (initVertice) {

		case "Estados Unidos":
			addLine(eeuuImg, finalVertice, number);
			break;
		case "España":
			addLine(espImg, finalVertice,number);
			break;
		case "Japón":
			addLine(japImg, finalVertice, number);
			break;
		case "Colombia":
			addLine(colImg, finalVertice, number);
			break;
		case "Nigeria":
			addLine(nigImg, finalVertice, number);
			break;
		case "Portugal":
			addLine(porImg, finalVertice, number);
			break;
		case "Rusia":
			addLine(rusImg, finalVertice, number);
			break;
		case "Dubái":
			addLine(dubImg, finalVertice, number);
			break;
		case "Madagascar":
			addLine(madImg, finalVertice, number);
			break;
		case "Australia":
			addLine(ausImg, finalVertice, number);
			break;
		default:
			break;
		}
	}

	public void addLine(ImageView image, String finalVertice, int number) {

		switch (finalVertice) {

		case "Estados Unidos":
			if(number == 0) {
				addLine(image, eeuuImg);
			}else {
				deleteLine(image, eeuuImg);
			}
			break;
		case "España":
			if(number == 0) {
				addLine(image, espImg);
			}else {
				deleteLine(image, espImg);
			}
			
			break;
		case "Japón":
			if(number == 0) {
				addLine(image, japImg);
			}else {
				deleteLine(image, japImg);
			}
			
			break;
		case "Colombia":
			if(number == 0) {
				addLine(image, colImg);
			}else {
				deleteLine(image, colImg);
			}
			
			break;
		case "Nigeria":
			if(number == 0) {
				addLine(image, nigImg);
			}else {
				deleteLine(image, nigImg);
			}
			
			break;
		case "Portugal":
			if(number == 0) {
				addLine(image, porImg);
			}else {
				deleteLine(image, porImg);
			}
			
			break;
		case "Rusia":
			if(number == 0) {
				addLine(image, rusImg);
			}else {
				deleteLine(image, rusImg);
			}
			
			break;
		case "Dubái":
			if(number == 0) {
				addLine(image, dubImg);
			}else {
				deleteLine(image, dubImg);
			}
				
			break;
		case "Madagascar":
			if(number == 0) {
				addLine(image, madImg);
			}else {
				deleteLine(image, madImg);
			}
			break;
		case "Australia":
			if(number == 0) {
				addLine(image, ausImg);
			}else {
				deleteLine(image, ausImg);
			}
			
			break;
		default:
			break;
		}
	}

	public void addLine(ImageView image, ImageView image2) {

		Line line = new Line(image.getLayoutX() + 35,image.getLayoutY() + 45,image2.getLayoutX() + 10,image2.getLayoutY()+40);
		line.setStrokeWidth(3);
		lines.add(line);
		mainPane.getChildren().add(line);
	}
	
	public void deleteLine(ImageView image, ImageView image2) {
		
		Line line = new Line(image.getLayoutX() + 35,image.getLayoutY() + 45,image2.getLayoutX() + 10,image2.getLayoutY()+40);
		line.setStrokeWidth(3);
		
		for (int i = 0; i < lines.size(); i++) {
			if(lines.get(i).getStartX() == line.getStartX() && lines.get(i).getEndY() == line.getEndY()) {
				lines.remove(i);
				mainPane.getChildren().remove(i+1);
			}
		}
	}

	@FXML
	public void closeApp(MouseEvent event) {
		System.exit(0);
	}

	public void loadMin() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("searchCost.fxml"));
		loader.setController(this);
		Parent root = loader.load();
		
		countryIniMin.getItems().addAll("Colombia", "Japón", "Estados Unidos", "España", "Nigeria", "Australia", "Portugal",
				"Rusia", "Dubái", "Madagascar");
		
		countryIniMin.setValue("Colombia");
		countryFinalMin.getItems().addAll("Colombia", "Japón", "Estados Unidos", "España", "Nigeria", "Australia","Portugal",
				"Rusia", "Dubái", "Madagascar", "Todos");
		countryFinalMin.setValue("Colombia");
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();

	}

	@FXML
	void buttonCostMini(ActionEvent event) {
		String ini = countryIniMin.getValue();
		String fin = countryFinalMin.getValue();
		String out = "";
		
		if (fin.equals("Todos")) {
			if (!typeGraph) {
				out = "Estos son los costos mínimos \n";
				ArrayList<Integer> inte = butterfly.dijkstraInList(ini);
				ArrayList<ListVertice<String, String, Integer>> list = butterfly.listGraph.getListVertice();
				for (int i = 0; i < list.size(); i++) {

					if (inte.get(i) != Integer.MAX_VALUE) {
						out += list.get(i).getValue() + " con costo: " + inte.get(i) + "\n";
					} else {
						out += "No se puede viajar desde " + ini + " hasta " + list.get(i).getValue();
					}
				}

			} else {
				out = "Estos son los costos mínimos \n";
				ArrayList<Integer> inte = butterfly.dijkstraInMatrix(ini);
				ArrayList<Vertice<String, String, Integer>> list = butterfly.matrixGraph.getVertice();
				for (int i = 0; i < list.size(); i++) {

					if (inte.get(i) != Integer.MAX_VALUE) {
						out += list.get(i).getValue() + " con costo: " + inte.get(i) + "\n";
					} else {
						out += "No se puede viajar desde " + ini + " hasta " + list.get(i).getValue();
					}
				}

			}

		} else {
			if (!typeGraph) {
				out += "Este es el costo mínimo \n";
				out += butterfly.dijkstraInList(ini, fin);

			} else {
				out += "Este es el costo mínimo \n";
				out += butterfly.dijkstraInMatrix(ini, fin);
			}
		}
		JOptionPane.showMessageDialog(null, out, "Costo", JOptionPane.WARNING_MESSAGE);
	}

}
