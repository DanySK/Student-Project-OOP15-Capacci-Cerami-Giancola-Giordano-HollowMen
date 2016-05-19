package hollowmen.model.dungeon;

import hollowmen.model.Point2D;


/**
 * This class implements {@link Point2D} and has method to set his coordinates
 * @author pigio
 *
 */
public class SettablePoint2D implements Point2D{

	private double x;
	
	private double y;
	
	/**
	 * This will create this point at (0, 0)
	 */
	public SettablePoint2D() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * This will create the new point in the same (x, y) of the <b>point</b>
	 * @param point 
	 */
	public SettablePoint2D(Point2D point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	/**
	 * This will create the new point in the same (<b>x</b>, <b>y</b>)
	 * @param x
	 * @param y
	 */
	public SettablePoint2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}
	
	/**
	 * This method allow to move this point in the same (x, y) of the <b>newPoint</b>
	 * @param newPoint
	 */
	public void setPoint(Point2D newPoint) {
		this.x = newPoint.getX();
		this.y = newPoint.getY();
	}
	
	/**
	 * This method allow to move this point in the same (<b>newX</b>, <b>newY</b>)
	 * @param newX
	 * @param newY
	 */
	public void setPoint(final double newX, final double newY) {
		this.x = newX;
		this.y = newY;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SettablePoint2D other = (SettablePoint2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	

}
