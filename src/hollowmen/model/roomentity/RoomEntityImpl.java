package hollowmen.model.roomentity;

import java.awt.Rectangle;

import org.jbox2d.dynamics.Body;

import hollowmen.model.Information;
import hollowmen.model.RoomEntity;

public abstract class RoomEntityImpl implements RoomEntity{

	private Information info;
	private Body body;
	
	protected RoomEntityImpl(Information info) {
		this.info = info;
		this.body = 
	}
	
	@Override
	public Information getInfo() {
		return info;
	}

	@Override
	public Body getBody() {
		return body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

}
