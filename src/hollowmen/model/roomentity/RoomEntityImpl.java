package hollowmen.model.roomentity;

import hollowmen.model.Information;
import hollowmen.model.RoomEntity;
import hollowmen.model.Size;

public class RoomEntityImpl implements RoomEntity{

	private Information info;
	
	private Size size;
	
	private int id;
	
	protected RoomEntityImpl(Information info, Size size) {
		this.info = info;
		this.size = size;
	}

	public void setID(int id) {
		this.id = id;
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
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
		if (id != other.id)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}
	
	
}
