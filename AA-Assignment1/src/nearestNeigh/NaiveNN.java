package nearestNeigh;

import java.util.ArrayList;
import java.util.List;
import nearestNeigh.Point;

/**
 * This class is required to be implemented.  Naive approach implementation.
 *
 * @author Jeffrey, Youhan for Startup-Code
 * @author Harold Zang for implemented
 */
public class NaiveNN implements NearestNeigh{
    private double largestDistance;
    private Point largestDistancePoint;
    private ArrayList<Point> naiveList, searchingResult;

    @Override
    public void buildIndex(List<Point> points) {
        // Do not use any structure, just list it.
        naiveList = (ArrayList<Point>)points;
    }

    @Override
    public List<Point> search(Point searchTerm, int k) {
        // To be implemented.
        double currentDistance;
        searchingResult = new ArrayList<Point>();
        largestDistance = 0.00;
        for (Point temp_point : naiveList) {
          if(!temp_point.cat.equals(searchTerm.cat)){
            continue;
          }
          currentDistance = temp_point.distTo(searchTerm);
          if(k > 0){
            searchingResult.add(temp_point);
            if(currentDistance > largestDistance){
              largestDistance = currentDistance;
              largestDistancePoint = temp_point;
            }
            k--;
          }else{
            if(currentDistance < largestDistance){
               searchingResult.remove(largestDistancePoint);
               largestDistancePoint = temp_point;
               largestDistance = currentDistance;
               //findout another largestDistance
               for (Point temp_result_point : searchingResult) {
                 double temp_currentDistance = temp_result_point.distTo(searchTerm);
                 if(temp_currentDistance > largestDistance){
                    largestDistance = temp_currentDistance;
                    largestDistancePoint = temp_result_point;
                 }
               }
               searchingResult.add(temp_point);
            }
          }
        }
        return searchingResult;
    }

    @Override
    public boolean addPoint(Point point) {
      // To be implemented.
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
        // To be implemented.
    	// For loop to find the point that we want to delete
        for (Point temp_point : naiveList) {
          if(temp_point.equals(point)){
        	  // Delete it
             return naiveList.remove(temp_point);
          }
        }
        return false;
    }

    @Override
    public boolean isPointIn(Point point) {
        // To be implemented.
        for (Point temp_point : naiveList) {
          if(temp_point.equals(point)){
             return true;
          }
        }
        return false;
    }

}