package hollowmen.model.dungeon;

import hollowmen.model.Information;
import hollowmen.model.RoomEntity;
import hollowmen.model.Size;

public class RoomEntityImpl implements RoomEntity{

	private Information info;
	
	private Size size;
	
	private int id;
	
	
	@Override
	public Information getInfo() {
		return info;
	}

	@Override
	public Size getSize() {
		return size;
	}

	@Override
	public int getID() {
		return id;
	}

}
