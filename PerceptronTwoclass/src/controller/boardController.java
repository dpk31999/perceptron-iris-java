package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import model.Board;
import model.Twoclass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import static javafx.scene.control.SelectionMode.SINGLE;

public class boardController {
    @FXML
    ListView lvMenu;
    @FXML
    Group groupBoard;
    @FXML
    ScatterChart scatterChart;

    public boardController() {
        loadTwoClassData();
    }

    @FXML
    private void initialize() {
        lvMenu.getSelectionModel().setSelectionMode(SINGLE);
        loadTwoClassToBoard(0, 1);

        List<Board> boardList = new ArrayList<>();
        for (int i = 0; i < Twoclass.featureNum-1; i++){
            for (int j = i+1; j < Twoclass.featureNum; j++){
                boardList.add(new Board(i, j));
            }
        }

        ObservableList<Board> boards = FXCollections.observableArrayList(boardList);
        lvMenu.setItems(boards);
        lvMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Board board = (Board) newValue;
                loadTwoClassToBoard(board.i, board.j);
            }
        });
    }

    ArrayList<Twoclass> twoclassSet = new ArrayList<>();
    
    public void loadData(String file)
    {
    	File twoclassFile = new File(file);
        if (twoclassFile.exists()) {
            System.out.println("Read file successfully!\n");
            Scanner sc = null;
            try {
                sc = new Scanner(twoclassFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().replaceAll("( +)", " ").trim().split(" ");
                if (line.length < Twoclass.featureNum) continue;
                Twoclass twoclass = new Twoclass();	
                for (int j = 0; j < Twoclass.featureNum; j++) {
                	twoclass.features[j] = Double.parseDouble(line[j]);
                }
                twoclass.type = 10 * Integer.parseInt(line[Twoclass.featureNum]);
                twoclass.type += Integer.parseInt(line[Twoclass.featureNum + 1]);
                
                twoclassSet.add(twoclass);
                System.out.println(twoclass.toString());
            }
        } else {
            System.out.println("Could not read file!\n");
        }
    }

    private void loadTwoClassData() {
        loadData("resource/twoclass.dat");
    }

    private void loadTwoClassToBoard(int firstF, int secondF) {
        XYChart.Series<Number, Number> iType1 = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> iType2 = new XYChart.Series<Number, Number>();
        iType1.setName("class 1");
        iType2.setName("class 2");

        twoclassSet.forEach(twoclass -> {
            switch (twoclass.type){
                case 10:
                	iType1.getData().add(new XYChart.Data<Number, Number>(twoclass.features[firstF],twoclass.features[secondF]));
                    break;
                case 1:
                	iType2.getData().add(new XYChart.Data<Number, Number>(twoclass.features[firstF],twoclass.features[secondF]));
                    break;
            }
        });
        if (!scatterChart.getData().isEmpty()){
            scatterChart.getData().remove(0, scatterChart.getData().size());
        }
        scatterChart.getData().addAll(iType1, iType2);

    }
}
