import java.util.ArrayList;

public class StudyRoomReservationSystem extends Thread {
    private ArrayList<StudyRoom> ERC; //Education resource center

    //Function to reserve a study room
    public synchronized StudyRoom reserveStudyRoom(int roomNumber) throws StudyRoomUnavailableException {
        StudyRoom studyRoom = null;
        for (StudyRoom room : ERC) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                studyRoom = room;
            }
        }
        if (studyRoom == null) {
            throw new StudyRoomUnavailableException();
        }
        return studyRoom;
    }

    public synchronized void (int roomNumber) {
        for (StudyRoom room : ERC) {
            if (room.getRoomNumber() == roomNumber) {
                room.setAvailable(true);
            }
        }
    }

    public void displayStudyRoomStatus() {
        // Calculate column widths
        int columnWidths = 2;
        // Print table
        System.out.println("Room\tCapacity\tAvailability");
        for (StudyRoom room : ERC) {
            System.out.print(room.getRoomNumber() + "\t" + room.getCapacity() + "\t" + room.isAvailable());
        }
        System.out.println();
    }
}
class StudyRoom{
    private boolean isAvailable;
    private int roomNumber;
    private int capacity;

    public StudyRoom(int roomNumber,int capacity){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.isAvailable = true;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean taken) {
        isAvailable = taken;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

}
