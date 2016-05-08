package hollowmen.model.character;

import hollowmen.model.Point2D;

public class SettablePoint2D implements Point2D{

	private double x;
	
	private double y;
	
	public SettablePoint2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public SettablePoint2D(Point2D point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
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
	
	
	public void setPoint(Point2D newPoint) {
		this.x = newPoint.getX();
		this.y = newPoint.getY();
	}
	
	public void setPoint(final double newX, final double newY) {
		this.x = newX;
		this.y = newY;
	}
	
	
	
	
	

}
