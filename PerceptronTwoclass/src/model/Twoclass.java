package model;
public class Twoclass {
    public static int featureNum = 4;
    public double[] features = new double[featureNum];
    public int type;

    public String toString(){
        String result = "";
        for(int i = 0; i < Twoclass.featureNum; i++){
            result += Double.toString(features[i]) + " ";
        }
        return result + type;
    }
}
