package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to be implemented.  Naive approach implementation.
 *
 * @author Jeffrey, Youhan for start-up code
 * @author Harold Zang for implement
 */
public class NaiveNN implements NearestNeigh{
    private double largestDistance;
    private Point largestDistancePoint;
    private ArrayList<Point> naiveList, searchingResult;

    @Override
    public void buildIndex(List<Point> points) {
        // Because it does not have any structure, just list.
        naiveList = (ArrayList<Point>)points;
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        double currentDistance;
       // New List for store the search Result
        searchingResult = new ArrayList<Point>();
        largestDistance = 0.00;
        // for(declaration : expression)
        for (Point tempPoint : naiveList) {
        	// Find the same category
          if(!tempPoint.cat.equals(searchTerm.cat)){
            continue;
          }
          //Computes the distance between two Points
          currentDistance = tempPoint.distTo(searchTerm);
          if(k > 0){
          	//add to search result
            searchingResult.add(tempPoint);
            if(currentDistance > largestDistance){
              largestDistance = currentDistance;
              largestDistancePoint = tempPoint;
            }
            k--;
          }else{
            if(currentDistance < largestDistance){
               searchingResult.remove(largestDistancePoint);
               largestDistancePoint = tempPoint;
              largestDistance = currentDistance;
               //find out another largestDistance
               for (Point tempResultPoint : searchingResult) {
                 double tempCurrentDistance = tempResultPoint.distTo(searchTerm);
                 if(tempCurrentDistance > largestDistance){
                    largestDistance = tempCurrentDistance;
                    largestDistancePoint = tempResultPoint;
                 }
               }
               searchingResult.add(tempPoint);
            }
          }
        }
        return searchingResult;
    }

    @Override
    public boolean addPoint(Point point) {
        // Check the current point is exist or not
      if(!this.isPointIn(point)){
    	  // If not exist, add it.
        naiveList.add(point);
        return true;
      }
        return false;
    }

    @Override
    public boolean deletePoint(Point point) {
    	// For loop to find the point that we want to delete
        for (Point tempPoint : naiveList) {
          if(tempPoint.equals(point)){
        	  // Delete it
             return naiveList.remove(tempPoint);
          }
        }
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
    	// For loop to find the point that we want
        for (Point tempPoint : naiveList) {
          if(tempPoint.equals(point)){
             return true;
          }
        }
        return false;
    }

}