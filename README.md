# clustering_algorithms

Description: Implementation of the data mining K-Means and K-Medoids clustering algorithms (K-Medoids in progress)

Input: Algorithms run on an example dataset of flower tuples, described by 4 attributes: sepal-length,	sepal-width,
petal-length, petal-width. Each flower was classifed into one of 4 species. 

Output: the classification prediction for unseen flower tuples.

Model: K-means/mediods uses a distance function to cluster objects together based on attributes. The number of clusters
is a hyperparameter given to the algorithm. The algorithm then uses these clusters to predict the classification of unseen
tuples by calculating to which cluster it falls. 

To run: $ java Main


