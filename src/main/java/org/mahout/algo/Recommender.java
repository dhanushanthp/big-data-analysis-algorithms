package org.mahout.algo;
import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Recommender {
   public static void main(String args[]){
      try{
         //Creating data model
         DataModel datamodel = new FileDataModel(new File("data/datad1")); //data
      
         //Creating UserSimilarity object.
         UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(datamodel);
      
         //Creating UserNeighbourHHood object.
         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(1.0, usersimilarity, datamodel);
      
         //Create UserRecomender
         UserBasedRecommender recommender = new GenericUserBasedRecommender(datamodel, userneighborhood, usersimilarity);
        
         List<RecommendedItem> recommendations = recommender.recommend(1, 3);
			
         for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
         }
      
      }catch(Exception e){
    	  e.printStackTrace();
      }
      
   }
  }