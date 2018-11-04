import java.util.ArrayList;
import java.util.List;

import kMean.KMeanCluster;
import kMean.KMeans;
import kMedoid.KMedoid;
import kMedoid.KMedoidCluster;
import data.*;

public class Main {

	/**
	 * @author: Sarah Amick
	 */
	public static void main(String[] args) {
		//Load iris data
		ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();

		//Clustering using k-means
		KMeans kMeans = new KMeans(irisData);
		List<KMeanCluster> foundClusters_KMeans = kMeans.KMeansPartition(3);

		for(KMeanCluster cluster : foundClusters_KMeans){
			System.out.println(cluster);
		}
		//Clustering using k-medoids
		ArrayList<KMedoidCluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisData);
	}

}
