package hollowmen.model.character;

import hollowmen.model.Point2D;
import hollowmen.model.Size;
import hollowmen.model.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

public class SettablePosition implements Size{
	
	private SettablePoint2D topLeft;
	
	private SettablePoint2D downRight;
	

	public SettablePosition(Point2D topLeft, Point2D downRight) throws IllegalArgumentException{
		ExceptionThrower.<Pair<Point2D,Point2D>>checkIllegalArgument(new Pair<>(topLeft,downRight), 
				p -> p.getX().getX() - p.getY().getX() <= 0 || p.getX().getY() - p.getY().getY() <= 0);
		this.topLeft = new SettablePoint2D(topLeft);
		this.downRight = new SettablePoint2D(downRight);
	}
	
	public SettablePosition(final double topLeftX, final double topLeftY,
			final double downRightX, final double downRightY) throws IllegalArgumentException {
		this(new SettablePoint2D(topLeftX, topLeftY), new SettablePoint2D(downRightX, downRightY));
	}
	@Override
	public Point2D getTopLeft() {
		return this.topLeft;
	}

	@Override
	public Point2D getDownRight() {
		return this.downRight;
	}

	
	public void setPosition(Point2D newTopLeft) {
		double xMove = newTopLeft.getX() - this.topLeft.getX();
		double yMove = newTopLeft.getY() - this.topLeft.getY();
		this.topLeft.setPoint(this.topLeft.getX() + xMove, this.topLeft.getY() + yMove);
		this.downRight.setPoint(this.downRight.getX() + xMove, this.downRight.getY() + yMove);
	}
	
	public void setPosition(final double newTopLeftX, final double newTopLeftY) {
		this.setPosition(new SettablePoint2D(newTopLeftX, newTopLeftY));
	}
	
}
