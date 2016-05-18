package hollowmen.model.character;

import hollowmen.model.Point2D;
import hollowmen.model.Size;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class implements {@link Size} and has methods to set rigidly,
 * leaving his height and length unchanged, his position
 * @author pigio
 *
 */
public class SizeImpl implements Size{
	
	private SettablePoint2D topLeft;
	
	private SettablePoint2D downRight;
	
/**
 * This constructor create a {@code Size} using the <b>topLeft</b> and <b>downRight</b> parameters,
 * implicitly set his position
 * @param topLeft
 * @param downRight
 * @throws IllegalArgumentException If any coordinates of the arguments points is negative<br>  
 * OR If <b>topLeft</b>'s X is greater than <b>downRight</b>'s X<br>
 * OR If <b>topLeft</b>'s Y is lesser than <b>downRight</b>'s Y
 */
	public SizeImpl(Point2D topLeft, Point2D downRight) throws IllegalArgumentException{
		ExceptionThrower.<Pair<Point2D,Point2D>>checkIllegalArgument(new Pair<>(topLeft,downRight), 
				p -> p.getX().getX() - p.getY().getX() <= 0
				|| p.getX().getY() - p.getY().getY() <= 0
				|| p.getX().getX() < 0
				|| p.getX().getY() < 0
				|| p.getY() .getX() < 0
				|| p.getY() .getY() < 0);
		this.topLeft = new SettablePoint2D(topLeft);
		this.downRight = new SettablePoint2D(downRight);
	}
	
	/**
	 * This constructor create a {@code Size} using the single coordinates arguments as 
	 * topLeft (<b>topLeftX</b>, <b>topLeftY</b>) downRight(<b>downRightX</b>, <b>downRightY</b>),
	 * implicitly set his position
	 * @param topLeftX
	 * @param topLeftY
	 * @param downRightX
	 * @param downRightY
	 * @throws IllegalArgumentException If any coordinates of the arguments points is negative<br>  
	 * OR If <b>topLeftX</b> is greater than <b>downRightX</b><br>
	 * OR If <b>topLeftY</b> is lesser than <b>downRightY</b>
	 */
	public SizeImpl(final double topLeftX, final double topLeftY,
			final double downRightX, final double downRightY) throws IllegalArgumentException {
		this(new SettablePoint2D(topLeftX, topLeftY), new SettablePoint2D(downRightX, downRightY));
	}
	
	/**
	 * This constructor create a {@code Size} with <b>length</b> and <b>height</b> parameters,
	 * his position will be setted at topLeft(0, <b>height</b>) rightDown(<b>length</b>, 0)
	 * @param length
	 * @param height
	 * @throws IllegalArgumentException If arguments are negative or 0
	 */
	public SizeImpl(final double length, final double height) throws IllegalArgumentException{
		this(new SettablePoint2D(0, height), new SettablePoint2D(length, 0));
	}

	
	@Override
	public Point2D getTopLeft() {
		return this.topLeft;
	}

	@Override
	public Point2D getDownRight() {
		return this.downRight;
	}

	/**
	 * This method rigidly move this {@code SizeImpl}'s topLeft point to the <b>newTopLeft</b>
	 * coordinates
	 * @param newTopLeft
	 * @throws IllegalArgumentException If any 
	 */
	public void setPosition(Point2D newTopLeft) throws IllegalArgumentException{
		double xMove = newTopLeft.getX() - this.topLeft.getX();
		double yMove = newTopLeft.getY() - this.topLeft.getY();
		this.topLeft.setPoint(this.topLeft.getX() + xMove, this.topLeft.getY() + yMove);
		this.downRight.setPoint(this.downRight.getX() + xMove, this.downRight.getY() + yMove);
	}
	
	public void setPosition(final double newTopLeftX, final double newTopLeftY) {
		this.setPosition(new SettablePoint2D(newTopLeftX, newTopLeftY));
	}
	
}
