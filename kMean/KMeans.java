package kMean;
import java.util.*;

import data.*;


public class KMeans {

	private int k;
	private ArrayList<Iris> data;
	private Map<float[], KMeanCluster> clusters;

	public KMeans(ArrayList<Iris> data){
		clusters = new HashMap<>();
		Normalizer normalizer = new Normalizer(data);
		this.data = normalizer.normalizeData();
	}

	public List<KMeanCluster> KMeansPartition(int k) {
		this.k = k;

		clusterLevel1();
		updateMeans();
		recluster(1);

		//TODO: calculate difference matrix for data irises from each other
		//TODO: select random first k irises
		//TODO: partition remaining N-k irises into clusters using difference matrix
		//TODO: calculate new mean of clusters
		//TODO: calculate difference matrix for data irises from the new mean
		//TODO: repartition based on second difference matrix

		List<KMeanCluster> finalClusters = new ArrayList<>();
		for(KMeanCluster cluster : clusters.values()){
			finalClusters.add(cluster);
		}

		return finalClusters;
	}

	private void clusterLevel1() {
		List<float[]> kIrisesCoordinates = new ArrayList<>();
		Random random = new Random();
		for(int i = 1; i <= k; i++){
			Iris kIris = data.get(random.nextInt(data.size()));
			kIrisesCoordinates.add(getCoordinates(kIris));
		}

		for(Iris iris : data){
			float smallestDistance = Float.POSITIVE_INFINITY;
			float[] closestCoordinates = null;

			for(float[] coordinates : kIrisesCoordinates){
				float distance = getDistance(getCoordinates(iris), coordinates);
				if(distance<smallestDistance){
					smallestDistance = distance;
					closestCoordinates = coordinates;
				}

			}
			if(clusters.containsKey(closestCoordinates)){
				KMeanCluster cluster = clusters.get(closestCoordinates);
				cluster.addIris(iris);
				clusters.put(closestCoordinates, cluster);
			}
			else{
				KMeanCluster cluster = new KMeanCluster();
				cluster.addIris(iris);
				clusters.put(closestCoordinates, cluster);
			}
		}
	}

	private void recluster(int change) {

		if(change==0) return;

		KMeanCluster[] clusterValues = new KMeanCluster[clusters.values().size()];
		int counter = 0;
		for(KMeanCluster cluster : clusters.values()){
			clusterValues[counter] = cluster;
			counter++;
		}

		int changes = 0;
		Map<Iris, KMeanCluster> irisesToDelete = new HashMap<>();
		for(int i = 0; i< clusterValues.length; i++){
			KMeanCluster currentCluster = clusterValues[i];

			for(Iris iris : currentCluster.getClusterMembers()){
				float distanceFromCurrentCluster = getDistance(currentCluster.getMean(), getCoordinates(iris));

				for(int j = i+1; j < clusterValues.length; j++){
					KMeanCluster nextCluster = clusterValues[j];
					float distanceFromNextCluster = getDistance(nextCluster.getMean(), getCoordinates(iris));
					if(distanceFromNextCluster < distanceFromCurrentCluster){
						irisesToDelete.put(iris,currentCluster);
						nextCluster.addIris(iris);
						changes++;
					}
				}
			}
		}
		for(Iris iris : irisesToDelete.keySet()){
			KMeanCluster fromCluster = irisesToDelete.get(iris);
			fromCluster.removeIris(iris);
		}
		updateMeans();
		recluster(changes);
	}

	private void updateMeans(){
		for(KMeanCluster cluster : clusters.values()){
			cluster.calculateMean();

		}
	}

	private float[] getCoordinates(Iris iris){
		float[] coordinates= new float[4];

		coordinates[0] = iris.getSepal_Length();
		coordinates[1] = iris.getSepal_Width();
		coordinates[2] = iris.getPetal_Length();
		coordinates[3] = iris.getPetal_Width();

		return coordinates;
	}

	private float getDistance(float[] coordinate1, float[] coordinate2){
		float distance = (float) Math.sqrt(Math.abs(coordinate1[0] - coordinate2[0]) + Math.abs(coordinate1[1] - coordinate2[1]) +
				Math.abs(coordinate1[2] - coordinate2[2]) + Math.abs(coordinate1[3] - coordinate2[3]));
		return distance;
	}

}
