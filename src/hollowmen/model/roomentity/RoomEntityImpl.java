package hollowmen.model.roomentity;

import java.awt.Rectangle;

import hollowmen.model.Information;
import hollowmen.model.RoomEntity;

public class RoomEntityImpl implements RoomEntity{

	private Information info;
	private int ID;
	private Rectangle size;
	
	protected RoomEntityImpl(Information info, Rectangle size, int ID) {
		this.info = info;
		this.size = size;
		this.ID = ID;
	}
	
	@Override
	public Information getInfo() {
		return info;
	}

	@Override
	public Rectangle getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		RoomEntityImpl other = (RoomEntityImpl) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	
	
	
}
