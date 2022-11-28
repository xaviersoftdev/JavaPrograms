package dealershipCRUD;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;


public class DealershipView extends BorderPane {
	private TableView<Dealership> tblProduct;
	private TextField txtId;
	private TextField txtMileage;
	private TextField txtMpg;
	private TextField txtCost;
	private TextField txtSalesPrice;
	private TextField txtSold;
	private TextField txtProfit;
	private Button btnAddNew;
	private Button btnDelete;
	private Button btnUpdate;

	public DealershipView() {

		tblProduct = new TableView<Dealership>();
		ScrollPane pane = new ScrollPane(tblProduct);
		pane.setMaxHeight(280);

		btnAddNew = new Button("Add New");
		btnDelete = new Button("Delete");
		btnUpdate = new Button("Update");

		HBox hBoxMiddle = new HBox(btnAddNew, btnDelete, btnUpdate);
		hBoxMiddle.setAlignment(Pos.TOP_CENTER);
		hBoxMiddle.getStyleClass().add("hbox");

		GridPane gridPaneBottom = new GridPane();
		gridPaneBottom.getStyleClass().add("pane");
		gridPaneBottom.add(new Label("Id:"), 0, 0);
		gridPaneBottom.add(new Label("Mileage:"), 2, 0);
		gridPaneBottom.add(new Label("Mpg:"), 2, 1);
		gridPaneBottom.add(new Label("Cost:"), 4, 0);
		gridPaneBottom.add(new Label("Sales Price:"), 4, 1);
		gridPaneBottom.add(new Label("Sold:"), 6, 0);
		gridPaneBottom.add(new Label("Profit:"), 6, 1);

		txtId = new TextField();
		//txtId.setEditable(false);
		//txtId.setVisible(false);
		txtMileage = new TextField();
		txtMpg = new TextField();
		txtMpg.setAlignment(Pos.CENTER_RIGHT);
		txtCost = new TextField();
		txtCost.setAlignment(Pos.CENTER_RIGHT);
		txtCost.setPrefWidth(80);
		txtCost.setMaxWidth(80);
		txtSalesPrice = new TextField();
		txtSalesPrice.setPrefWidth(80);
		txtSalesPrice.setMaxWidth(80);
		txtSold = new TextField();
		txtProfit = new TextField();

		gridPaneBottom.add(txtId, 1, 0);
		gridPaneBottom.add(txtMileage, 3, 0);
		gridPaneBottom.add(txtMpg, 3, 1);
		gridPaneBottom.add(txtCost, 5, 0);
		gridPaneBottom.add(txtSalesPrice, 5, 1);
		gridPaneBottom.add(txtSold, 7, 0);
		gridPaneBottom.add(txtProfit, 7, 1);
		gridPaneBottom.setMinHeight(200);

		setTop(pane);
		setCenter(hBoxMiddle);
		setBottom(gridPaneBottom);
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public Button getBtnUpdate() {
		return btnUpdate;
	}

	public Button getBtnAddNew() {
		return btnAddNew;
	}

	public TableView<Dealership> getTblProduct() {
		return tblProduct;
	}

	public TextField getTxtId() {
		return txtId;
	}

	public TextField getTxtMileage() {
		return txtMileage;
	}

	public TextField getTxtMpg() {
		return txtMpg;
	}
	
	public TextField getTxtCost() {
		return txtCost;
	}

	public TextField getTxtSalesPrice() {
		return txtSalesPrice;
	}

	public TextField getTxtSold() {
		return txtSold;
	}
	
	public TextField getTxtProfit() {
		return txtProfit;
	}

	public void FormatTable() {

		tblProduct.setEditable(true);

		TableColumn<Dealership, String> idCol = new TableColumn<>("ID");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<Dealership, String>("id"));
		idCol.setCellFactory(TextFieldTableCell.forTableColumn());
		idCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, String>>() {
			@Override
			public void handle(CellEditEvent<Dealership, String> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setId(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		TableColumn<Dealership, Integer> mileageCol = new TableColumn<>("Mileage");
		mileageCol.setMinWidth(100);
		mileageCol.setCellValueFactory(new PropertyValueFactory<Dealership, Integer>("mileage"));
		mileageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		mileageCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, Integer>>() {
			@Override
			public void handle(CellEditEvent<Dealership, Integer> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMileage(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		TableColumn<Dealership, Integer> mpgCol = new TableColumn<>("MPG");
		mpgCol.setMinWidth(100);
		mpgCol.setCellValueFactory(new PropertyValueFactory<Dealership, Integer>("mpg"));
		mpgCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		mpgCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, Integer>>() {
			@Override
			public void handle(CellEditEvent<Dealership, Integer> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setMpg(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		TableColumn<Dealership, Double> costCol = new TableColumn<>("Cost");
		costCol.setMinWidth(100);
		costCol.setCellValueFactory(new PropertyValueFactory<Dealership, Double>("cost"));
		costCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		costCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, Double>>() {
			@Override
			public void handle(CellEditEvent<Dealership, Double> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setCost(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		TableColumn<Dealership, Double> salesPriceCol = new TableColumn<>("Sales Price");
		salesPriceCol.setMinWidth(100);
		salesPriceCol.setCellValueFactory(new PropertyValueFactory<Dealership, Double>("salesPrice"));
		salesPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		salesPriceCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, Double>>() {
			@Override
			public void handle(CellEditEvent<Dealership, Double> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSalesPrice(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		TableColumn<Dealership, Boolean> soldCol = new TableColumn<>("Sold");
		soldCol.setMinWidth(100);
		soldCol.setCellValueFactory(new PropertyValueFactory<Dealership, Boolean>("sold"));
		soldCol.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
		
		soldCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, Boolean>>() {
			@Override
			public void handle(CellEditEvent<Dealership, Boolean> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSold(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		TableColumn<Dealership, Double> profitCol = new TableColumn<>("Profit");
		profitCol.setMinWidth(100);
		profitCol.setCellValueFactory(new PropertyValueFactory<Dealership, Double>("profit"));
		profitCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		profitCol.setOnEditCommit(new EventHandler<CellEditEvent<Dealership, Double>>() {
			@Override
			public void handle(CellEditEvent<Dealership, Double> t) {
				((Dealership) t.getTableView().getItems().get(t.getTablePosition().getRow())).setProfit(t.getNewValue());
				Dealership d = getTblProduct().getSelectionModel().getSelectedItem();
				DealershipDAO model = new DealershipDAO();
				model.edit(d);
			}
		});
		
		tblProduct.getColumns().add(0, idCol);
		tblProduct.getColumns().add(1, mileageCol);
		tblProduct.getColumns().add(2, mpgCol);
		tblProduct.getColumns().add(3, costCol);
		tblProduct.getColumns().add(4, salesPriceCol);
		tblProduct.getColumns().add(5, soldCol);
		tblProduct.getColumns().add(6, profitCol);
			
	}

}