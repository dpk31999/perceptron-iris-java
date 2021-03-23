/**
 * Created by Dave on 5/11/17.
 */
public class Perceptron {

    double[] weights = {0,0,0,0}; // weights are initialized to 0
    double[][] trainData;
    double[][] testData;
    double learningRate;
    int iterations;

    Perceptron(double learningRate, int iterations, double[][] trainData, double[][] testData) {
        this.learningRate = learningRate;
        this.iterations = iterations;
        this.trainData = trainData;
        this.testData = testData;
    }

    /* Your Perceptron should be an object with methods to train, predict, etc */

    public void train() {
        System.out.println("Training Data.....");
        for (int i = 0; i < iterations; i++) {
            System.out.println("Starting iteration " + (i + 1));
            for (double[] row: trainData) {
                double label = row[row.length - 1];
                double predictedLabel = dotProduct(row);
                if (predictedLabel > label) {
                	// giam trong so neu label du doan > label real
                    decreaseWeightVector(row);
                }
                else if (predictedLabel < label) {
                	// tang trong so neu label du doan < label real
                    increaseWeightVector(row);
                }
            }
        }
    }

    public void predict() {
        /* Classify the testing data. Remember our algorithm hasn't
         * seen this data before. Hopefully, it "learned" the difference
         * between the two flowers */
        int numberOfCorrectPredictions = 0;
        
        int correctClass1 = 0;
        int correctClass2 = 0;

        for (double[] row : testData) {
        	String[] data = new String[] {"10","01"};
            double label = row[row.length - 1];
            double prediction = dotProduct(row);
            boolean correct = prediction == label;
            
            if (correct && prediction == 0) {
                numberOfCorrectPredictions++;
                correctClass1++;
                System.out.println("Predicted: " + data[(int) prediction] + "  | Truth: " + data[(int) label]);
            }
            else if (correct && prediction == 1) {
                numberOfCorrectPredictions++;
                correctClass2++;
                System.out.println("Predicted: " + data[(int) prediction] + "  | Truth: " + data[(int) label]);
            }
            else if (!correct && prediction == 1) {
                System.out.println("Predicted: " + data[(int) prediction] + "  | Truth: " + data[(int) label]);
            }
            else {
                System.out.println("Predicted: " + data[(int) prediction] + "  | Truth: " + data[(int) label]);
            }
        }
        calculateAccuracy(numberOfCorrectPredictions);

        confusionmatrix(correctClass1,correctClass2);
    }

    public void calculateAccuracy(int correct) {
        double percentCorrect = ((double) correct / testData.length) * 100;
        System.out.println("Accuracy " + percentCorrect + "%");

    }
    
    public void confusionmatrix(int correctClass1,int correctClass2)
    {
    	System.out.println("=== Confusion Matrix ===");
    	System.out.println("10	01	<-- classified as");
    	System.out.println(correctClass1 + "	" + (testData.length/2 - correctClass1) + "	|	10" );
    	System.out.println((testData.length/2 - correctClass2) + "	" + correctClass2 + "	|	01" );
    	System.out.println("=======End=======");
    }

    private void decreaseWeightVector(double[] dataPoint) {
        for (int i = 0; i < dataPoint.length - 1; i++) {
//        	if(i == 0 || i == 1)
//        	{
//        		continue;
//        	}
            weights[i] -= dataPoint[i] * learningRate;
        }
    }

    private void increaseWeightVector(double[] dataPoint) {
        for (int i = 0; i < dataPoint.length - 1; i++) {
//        	if(i == 0 || i == 1)
//        	{
//        		continue;
//        	}
            weights[i] += dataPoint[i] * learningRate;
        }
    }

    private double dotProduct(double[] dataPoint) {
        // you might want to write this method
        double result = 0;
        for (int i = 0; i < dataPoint.length - 1; i++) {
//        	if(i == 0 || i == 1)
//        	{
//        		continue;
//        	}
            result += dataPoint[i] * weights[i];
        }
        if (result > 0.0) {
            return 1.0;
        }
        else {
        	return 0.0;
        }
    }
}
