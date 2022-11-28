package dealershipCRUD;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DealershipFX extends Application {

	private DealershipDAO model;
	private DealershipView view;

	@Override
	public void start(Stage primaryStage) {
		model = new DealershipDAO();
		view = new DealershipView();

		view.FormatTable();
		view.getTblProduct().setItems(FXCollections.observableArrayList(model.findAll()));

		primaryStage.setTitle("Dealership");
		
		view.getTblProduct().setOnMouseClicked((MouseEvent event) -> {
		    if (event.getClickCount() > 0) {
			    if (view.getTblProduct().getSelectionModel().getSelectedItem() != null) {
			        Dealership d = view.getTblProduct().getSelectionModel().getSelectedItem();
			        view.getTxtId().setText(d.getId());
			        view.getTxtMileage().setText(String.valueOf(d.getMileage()));
			        view.getTxtMpg().setText(String.valueOf(d.getMpg()));
			        view.getTxtCost().setText(String.valueOf(d.getCost()));
			        view.getTxtSalesPrice().setText(String.valueOf(d.getSalesPrice()));
			        view.getTxtSold().setText(String.valueOf(d.getSold()));
			        view.getTxtProfit().setText(String.valueOf(d.getProfit()));
			    }
		    }
		});

		view.getBtnDelete().setOnAction(e -> {
			String id = view.getTxtId().getText();
			Dealership d = model.find(id);
			
			String selection = d.getId();
			Alert alert = new Alert(AlertType.WARNING, "Delete " + selection + " ?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				model.delete(d);
				view.getTblProduct().setItems(FXCollections.observableArrayList(model.findAll()));
				view.getTxtId().setText("");
				view.getTxtMileage().setText("");
				view.getTxtMpg().setText("");
				view.getTxtCost().setText("");
				view.getTxtSalesPrice().setText("");
				view.getTxtSold().setText("");
				view.getTxtProfit().setText("");
			}
		});
		view.getBtnUpdate().setOnAction(e -> {
			String id = view.getTxtId().getText();
			int mileage = Integer.parseInt(view.getTxtMileage().getText());
			int mpg = Integer.parseInt(view.getTxtMpg().getText());
			double cost = Double.parseDouble(view.getTxtCost().getText());
			double salesPrice = Double.parseDouble(view.getTxtSalesPrice().getText());
			boolean sold = Boolean.parseBoolean(view.getTxtSold().getText());
			double profit = Double.parseDouble(view.getTxtProfit().getText());
			Dealership d = new Dealership(id,mileage,mpg,cost,salesPrice,sold,profit);
			if (model.edit(d)) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Edit product successfull", ButtonType.OK);
				alert.showAndWait();
				view.getTblProduct().setItems(FXCollections.observableArrayList(model.findAll()));
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Edit product failed", ButtonType.OK);
				alert.showAndWait();
			}
			view.getTxtId().setText("");
			view.getTxtMileage().setText("");
			view.getTxtMpg().setText("");
			view.getTxtCost().setText("");
			view.getTxtSalesPrice().setText("");
			view.getTxtSold().setText("");
			view.getTxtProfit().setText("");
			
		});
		view.getBtnAddNew().setOnAction(e -> {
			String id = view.getTxtId().getText();
			int mileage = Integer.parseInt(view.getTxtMileage().getText());
			int mpg = Integer.parseInt(view.getTxtMpg().getText());
			double cost = Double.parseDouble(view.getTxtCost().getText());
			double salesPrice = Double.parseDouble(view.getTxtSalesPrice().getText());
			boolean sold = Boolean.parseBoolean(view.getTxtSold().getText());
			double profit = Double.parseDouble(view.getTxtProfit().getText());
			
			Dealership d = new Dealership(id,mileage,mpg,cost,salesPrice,sold, profit);
			if (model.create(d)) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Add product successfull", ButtonType.OK);
				alert.showAndWait();
				view.getTblProduct().setItems(FXCollections.observableArrayList(model.findAll()));
			} else {
				Alert alert = new Alert(AlertType.WARNING, "Add product failed", ButtonType.OK);
				alert.showAndWait();
			}
			
			view.getTxtId().setText("");
			view.getTxtMileage().setText("");
			view.getTxtMpg().setText("");
			view.getTxtCost().setText("");
			view.getTxtSalesPrice().setText("");
			view.getTxtSold().setText("");
			view.getTxtProfit().setText("");

		});

		Scene scene = new Scene(view, 650, 400, true);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Image myIcon = new Image(getClass().getResourceAsStream("waketech_icon.png"));
		primaryStage.getIcons().add(myIcon);
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);
	}
} 
