package mobility;

import mobility.Point;


/**
 * Defines Animals Locateability.
 * @author Gitit Dadon-212280911, Michal Tenenboim-326945763
 * @see Elephant,Turtle,Giraffe
 */
public interface ilocatable {
	/**
	 * returns Current Animal Location.
	 * @return
	 */
	Point getLocation();
	
	/**
	 * Updates Old Location to a new one.
	 * @param newLocation -of Type Point, New Animal's Location
	 * @return true, if newLocation coordinates are Legal.
	 */
	boolean setLocation(Point newLocation);

}
