package com.berru.app.atmjfx.controller;

import com.berru.app.atmjfx.dao.KdvDAO;
import com.berru.app.atmjfx.dto.KdvDTO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class KdvController {

    private final KdvDAO kdvDAO = new KdvDAO();

    @FXML private TableView<KdvDTO> kdvTable;
    @FXML private TableColumn<KdvDTO, Integer> idColumn;
    @FXML private TableColumn<KdvDTO, Double> amountColumn;
    @FXML private TableColumn<KdvDTO, Double> kdvRateColumn;
    @FXML private TableColumn<KdvDTO, Double> kdvAmountColumn;
    @FXML private TableColumn<KdvDTO, Double> totalAmountColumn;
    @FXML private TableColumn<KdvDTO, String> receiptColumn;
    @FXML private TableColumn<KdvDTO, LocalDate> dateColumn;
    @FXML private TableColumn<KdvDTO, String> descColumn;
    @FXML private TextField searchField;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        kdvRateColumn.setCellValueFactory(new PropertyValueFactory<>("kdvRate"));
        kdvAmountColumn.setCellValueFactory(new PropertyValueFactory<>("kdvAmount"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        receiptColumn.setCellValueFactory(new PropertyValueFactory<>("receiptNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

        searchField.textProperty().addListener((obs, oldVal, newVal) -> applyFilter());
        refreshTable();
    }

    public void refreshTable() {
        Optional<List<KdvDTO>> list = kdvDAO.list();
        list.ifPresent(data -> kdvTable.setItems(FXCollections.observableArrayList(data)));
    }

    private void applyFilter() {
        String keyword = searchField.getText().trim().toLowerCase();
        Optional<List<KdvDTO>> all = kdvDAO.list();
        List<KdvDTO> filtered = all.orElse(List.of()).stream()
                .filter(kdv -> kdv.getReceiptNumber().toLowerCase().contains(keyword))
                .toList();
        kdvTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    public void clearFilters() {
        searchField.clear();
        refreshTable();
    }

    @FXML
    public void addKdv(ActionEvent event) {
        KdvDTO newKdv = showKdvForm(null);
        if (newKdv != null && newKdv.isValid()) {
            kdvDAO.create(newKdv);
            refreshTable();
            showAlert("Success", "KDV record added.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void updateKdv(ActionEvent event) {
        KdvDTO selected = kdvTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Select a record to update.", Alert.AlertType.WARNING);
            return;
        }
        KdvDTO updated = showKdvForm(selected);
        if (updated != null && updated.isValid()) {
            kdvDAO.update(selected.getId(), updated);
            refreshTable();
            showAlert("Success", "KDV record updated.", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void deleteKdv(ActionEvent event) {
        KdvDTO selected = kdvTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Warning", "Select a record to delete.", Alert.AlertType.WARNING);
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Silmek istiyor musunuz?", ButtonType.OK, ButtonType.CANCEL);
        confirm.setHeaderText("Record: " + selected.getReceiptNumber());
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            kdvDAO.delete(selected.getId());
            refreshTable();
            showAlert("Deleted", "KDV record deleted.", Alert.AlertType.INFORMATION);
        }
    }

    private void showAlert(String title, String msg, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private KdvDTO showKdvForm(KdvDTO existing) {
        Dialog<KdvDTO> dialog = new Dialog<>();
        dialog.setTitle(existing == null ? "Add New KDV" : "Update KDV");

        TextField amountField = new TextField();
        TextField rateField = new TextField();
        TextField receiptField = new TextField();
        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField descField = new TextField();
        ComboBox<String> exportCombo = new ComboBox<>();
        exportCombo.getItems().addAll("TXT", "PDF", "EXCEL");
        exportCombo.setValue("TXT");

        if (existing != null) {
            amountField.setText(String.valueOf(existing.getAmount()));
            rateField.setText(String.valueOf(existing.getKdvRate()));
            receiptField.setText(existing.getReceiptNumber());
            datePicker.setValue(existing.getTransactionDate());
            descField.setText(existing.getDescription());
            exportCombo.setValue(existing.getExportFormat());
        }

        GridPane grid = new GridPane();
        grid.setHgap(10); grid.setVgap(10);
        grid.addRow(0, new Label("Amount:"), amountField);
        grid.addRow(1, new Label("KDV Rate (%):"), rateField);
        grid.addRow(2, new Label("Receipt No:"), receiptField);
        grid.addRow(3, new Label("Date:"), datePicker);
        grid.addRow(4, new Label("Description:"), descField);
        grid.addRow(5, new Label("Format:"), exportCombo);


        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(btn -> {
            if (btn == ButtonType.OK) {
                try {
                    return KdvDTO.builder()
                            .amount(Double.parseDouble(amountField.getText()))
                            .kdvRate(Double.parseDouble(rateField.getText()))
                            .receiptNumber(receiptField.getText())
                            .transactionDate(datePicker.getValue())
                            .description(descField.getText())
                            .exportFormat(exportCombo.getValue())
                            .build();
                } catch (Exception e) {
                    showAlert("Error", "Invalid input data!", Alert.AlertType.ERROR);
                }
            }
            return null;
        });

        Optional<KdvDTO> result = dialog.showAndWait();
        return result.orElse(null);
    }
}
