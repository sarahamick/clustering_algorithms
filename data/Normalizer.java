package data;

import java.util.ArrayList;

public class Normalizer {

    private ArrayList<Iris> data;
    private ArrayList<Iris> normalizedData;

    private float sepalLengthMean;
    private float sepalWidthMean;
    private float petalLengthMean;
    private float petalWidthMean;

    private float sepalLengthStdDev;
    private float sepalWidthStdDev;
    private float petalLengthStdDev;
    private float petalWidthStdDev;

    public Normalizer(ArrayList<Iris> data){
        this.data = data;
        normalizedData = new ArrayList<>();
    }

    public ArrayList<Iris> normalizeData() {

        calculateMeanScores();
        calculateStdDevScores();

        for(Iris iris : data){
            float sepalLengthZSCORE = (iris.getSepal_Length()-sepalLengthMean)/sepalLengthStdDev;
            float sepalWidthZSCORE = (iris.getSepal_Width()-sepalWidthMean)/sepalWidthStdDev;
            float petalLengthZSCORE = (iris.getPetal_Length()-petalLengthMean)/petalLengthStdDev;
            float petalWidthZSCORE = (iris.getPetal_Width()-petalWidthMean)/petalWidthStdDev;

            normalizedData.add(new Iris(sepalLengthZSCORE,sepalWidthZSCORE, petalLengthZSCORE, petalWidthZSCORE, iris.getIrisClass()));
        }

        return normalizedData;
    }

    private void calculateMeanScores() {
        float sepalLengthSum = 0;
        float sepalWidthSum = 0;
        float petalLengthSum = 0;
        float petalWidthSum = 0;

        for(Iris iris : data){
            sepalLengthSum += iris.getSepal_Length();
            sepalWidthSum += iris.getSepal_Width();
            petalLengthSum += iris.getPetal_Length();
            petalWidthSum += iris.getPetal_Width();
        }

        this.sepalLengthMean = (sepalLengthSum/data.size());
        this.sepalWidthMean = (sepalWidthSum/data.size());
        this.petalLengthMean = (petalLengthSum/data.size());
        this.petalWidthMean = (petalWidthSum/data.size());
    }

    private void calculateStdDevScores() {
        float sepalLengthSumMinusMean = 0;
        float sepalWidthSumMinusMean = 0;
        float petalLengthSumMinusMean = 0;
        float petalWidthSumMinusMean = 0;

        for(Iris iris : data){
            sepalLengthSumMinusMean += (Math.pow((double)(iris.getSepal_Length() - sepalLengthMean), 2));
            sepalWidthSumMinusMean += (Math.pow((double)(iris.getSepal_Width() - sepalWidthMean), 2));
            petalLengthSumMinusMean += (Math.pow((double)(iris.getPetal_Length() - petalLengthMean), 2));
            petalWidthSumMinusMean += (Math.pow((double)(iris.getPetal_Width() - petalWidthMean), 2));
        }

        float sepalLengthMeanMinusMean = (sepalLengthSumMinusMean/data.size());
        float sepalWidthMeanMeanMinusMean = (sepalWidthSumMinusMean/data.size());
        float petalLengthMeanMinusMean = (petalLengthSumMinusMean/data.size());
        float petalWidthMeanMinusMean = (petalWidthSumMinusMean/data.size());

        this.sepalLengthStdDev = (float) (Math.sqrt(sepalLengthMeanMinusMean));
        this.sepalWidthStdDev = (float) (Math.sqrt(sepalWidthMeanMeanMinusMean));
        this.petalLengthStdDev = (float) (Math.sqrt(petalLengthMeanMinusMean));
        this.petalWidthStdDev = (float) (Math.sqrt(petalWidthMeanMinusMean));
    }

}
