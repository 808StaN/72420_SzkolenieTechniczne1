package com.example.lab4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private TableView<Osoba> tabela;

    @FXML
    private TableColumn<Osoba, Integer> colId;

    @FXML
    private TableColumn<Osoba, String> colImie;

    @FXML
    private TableColumn<Osoba, String> colNazwisko;

    @FXML
    private TextField tfImie;

    @FXML
    private TextField tfNazwisko;

    private final ObservableList<Osoba> osoby = FXCollections.observableArrayList();
    private int nextId = 1;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        colNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        tabela.setItems(osoby);

        // Zadanie 1: para przykladowych wpisow.
        osoby.add(new Osoba(nextId++, "Anna", "Nowak", "anna.nowak@example.com"));
        osoby.add(new Osoba(nextId++, "Jan", "Kowalski", "jan.kowalski@example.com"));

        // Zadanie 4: pojedynczy wybor i uzupelnianie pol tekstowych.
        TableView.TableViewSelectionModel<Osoba> selectionModel = tabela.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        ObservableList<Osoba> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener((ListChangeListener<Osoba>) change -> {
            if (!change.getList().isEmpty()) {
                Osoba os = change.getList().get(0);
                tfImie.setText(os.getImie());
                tfNazwisko.setText(os.getNazwisko());
            }
        });
    }

    @FXML
    protected void onDodajClick() {
        String imie = tfImie.getText().trim();
        String nazwisko = tfNazwisko.getText().trim();

        if (imie.isEmpty() || nazwisko.isEmpty()) {
            return;
        }

        String email = (imie + "." + nazwisko + "@example.com").toLowerCase();
        osoby.add(new Osoba(nextId++, imie, nazwisko, email));
        tfImie.clear();
        tfNazwisko.clear();
    }
}