import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/board.fxml"));
        String css = Main.class.getResource("/view/Chart.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Scatter plot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
    	double[][] trainingData = DataSetup.loadData("resource/training-twoclass.csv");
        DataSetup.printData(trainingData);


        System.out.println("___________________________________________________");
        double[][] testingData = DataSetup.loadData("resource/testing-twoclass.csv");
        DataSetup.printData(testingData);


        System.out.println("======================================================");
        Perceptron perceptron = new Perceptron(0.01, 3, trainingData, testingData);
        perceptron.train();
        perceptron.predict();
    	
//        launch(args);
    }
}
