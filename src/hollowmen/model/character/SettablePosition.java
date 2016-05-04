package hollowmen.model.character;

import hollowmen.model.Position;

public class SettablePosition implements Position{
	
	private double x;
	
	private double y;
	
	public SettablePosition(){
		x = 0;
		y = 0;
	}
	
	public SettablePosition(final double x, final double y){
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	public void setPosition(final double x, final double y){
		this.x = x;
		this.y = y;
	}

}
