/**
 * Created by Dave on 5/11/17.
 */
public class Main {
    public static void main(String[] args) {
        double[][] trainingData = DataSetup.loadData("training-twoclass.csv");
        DataSetup.printData(trainingData);


        System.out.println("___________________________________________________");
        double[][] testingData = DataSetup.loadData("testing-twoclass.csv");
        DataSetup.printData(testingData);


        System.out.println("======================================================");
        Perceptron perceptron = new Perceptron(0.01, 3, trainingData, testingData);
        perceptron.train();
        perceptron.predict();
    }
}
