package hollowmen.model.dungeon;


import hollowmen.model.Floor;
import hollowmen.model.Room;

public class FloorImpl implements Floor{

	
	private int floorNumber;
	
	private Room currentRoom;
	
	
	private FloorImpl() {};
	
    private static class Holder{
        private static final FloorImpl INSTANCE = new FloorImpl();
    }
     
    public static FloorImpl getInstance(){
        return Holder.INSTANCE;
    }
	
	@Override
	public int getFloorNumber() {
		return this.floorNumber;
	}

	@Override
	public Room getCurrentRoom() {
		return this.currentRoom;
	}

	public void changeRoom(int newRoomNumber) {
		if(newRoomNumber < 0) {
			this.currentRoom = this.currentRoom.getParentRoom();
		} else {
			this.currentRoom = this.currentRoom.getChildRoom(newRoomNumber);
		}
	}

	public void inizialize(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	
}
