package hollowmen.model.dungeon;


import hollowmen.model.Floor;
import hollowmen.model.Room;

public class FloorSingleton implements Floor{

	private Room lobby;
	
	private int floorNumber = 0;
	
	private Room currentRoom;
	
	
	private FloorSingleton() {};
	
    private static class Holder{
        private static final FloorSingleton INSTANCE = new FloorSingleton();
    }
     
    public static FloorSingleton getInstance(){
        return Holder.INSTANCE;
    }
	
	@Override
	public int getFloorNumber() {
		return this.floorNumber;
	}

	@Override
	public Room getCurrentRoom() {
		return (this.floorNumber == 0)?this.lobby:this.currentRoom;
	}

	public void changeRoom(int newRoomNumber) {
		if(newRoomNumber < 0) {
			this.currentRoom = this.currentRoom.getParentRoom();
		} else {
			this.currentRoom = this.currentRoom.getChildRoom(newRoomNumber);
		}
	}

	public void setFloor(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	
}
