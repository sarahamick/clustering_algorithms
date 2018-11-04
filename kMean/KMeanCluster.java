package kMean;
import java.util.ArrayList;
import java.util.List;

import data.Iris;
import data.IrisClass;

public class KMeanCluster {

	private List<Iris> clusterMembers;
	private float[] mean;

	/**
	 * @author: Sarah Amick
	 */
	public KMeanCluster() {
		this.clusterMembers = new ArrayList<>();
	}

	public void calculateMean() {
		float sepalLengthSum = 0;
		float sepalWidthSum = 0;
		float petalLengthSum = 0;
		float petalWidthSum = 0;

		for(Iris iris : clusterMembers){
			sepalLengthSum = sepalLengthSum + iris.getSepal_Length();
			sepalWidthSum = sepalWidthSum + iris.getSepal_Width();
			petalLengthSum = petalLengthSum + iris.getPetal_Length();
			petalWidthSum = petalWidthSum + iris.getPetal_Width();
		}

		float sepalLengthMean = (sepalLengthSum/clusterMembers.size());
		float sepalWidthMean = (sepalWidthSum/clusterMembers.size());
		float petalLengthMean = (petalLengthSum/clusterMembers.size());
		float petalWidthMean = (petalWidthSum/clusterMembers.size());

		float[] mean = new float[4];
		mean[0] = sepalLengthMean;
		mean[1] = sepalWidthMean;
		mean[2] = petalLengthMean;
		mean[3] = petalWidthMean;

		this.mean = mean;
	}

	public void addIris(Iris iris){
		clusterMembers.add(iris);
	}

	public void removeIris(Iris iris){
		clusterMembers.remove(iris);
	}

	@Override
	public String toString() {
		int setosaCount = 0;
		int versicolorCount = 0;
		int verginicaCount = 0;
		for(Iris iris : clusterMembers){
			if(iris.getIrisClass() == IrisClass.Iris_setosa) setosaCount++;
			if(iris.getIrisClass()== IrisClass.Iris_versicolor) versicolorCount++;
			if(iris.getIrisClass() == IrisClass.Iris_virginica) verginicaCount++;
		}

		double setosaPercentage = setosaCount/clusterMembers.size();
		double versicolorPercetage = versicolorCount/clusterMembers.size();
		double verginicaPercentage = verginicaCount/clusterMembers.size();

		String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
		toPrintString += "CLUSTER OF SIZE: " + clusterMembers.size() + System.getProperty("line.separator");
		toPrintString += "Setosa count: " + setosaCount + System.getProperty("line.separator");
		toPrintString += "Versicolor count: " + verginicaCount + System.getProperty("line.separator");
		toPrintString += "Virginica count: " + verginicaCount + System.getProperty("line.separator");


		for(Iris i : this.clusterMembers) {
			toPrintString += i.toString() + System.getProperty("line.separator");
		}
		toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

		return toPrintString;
	}

	public float[] getMean(){
		return mean;
	}

	public List<Iris> getClusterMembers() {
		return clusterMembers;
	}
}
