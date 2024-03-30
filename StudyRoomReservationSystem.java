import java.util.ArrayList;
import java.util.Random;

class StudyRoomReservationSystem{
    private ArrayList<StudyRoom> ERC = new ArrayList<StudyRoom>(); //Education resource center
    public int taskCount = 0;

    //Function to reserve a study room
    public synchronized StudyRoom reserveStudyRoom(int roomNumber) throws StudyRoomUnavailableException {
        taskCount++;
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
        System.out.println("successfully reserved room "+ roomNumber );
        return studyRoom;
    }
    //Function for release a study room
    public synchronized void releaseStudyRoom(int roomNumber) {
        taskCount++;
        for (StudyRoom room : ERC) {
            if (room.getRoomNumber() == roomNumber) {
                room.setAvailable(true);
            }
        }
        System.out.println("successfully release room "+ roomNumber );
    }
    //Function for display the room status
    public void displayStudyRoomStatus() {
        System.out.println("Room\tCapacity\tAvailability");
        for (StudyRoom room : ERC) {
            System.out.println(room.getRoomNumber() + "\t\t\t" + room.getCapacity() + "\t\t\t" + room.isAvailable());
        }
    }

    public static void main(String arg[]){
        StudyRoomReservationSystem studyRoomReservationSystem = new StudyRoomReservationSystem();
        //Adding study rooms to education resource center
        for(int i=1;i<11;i++){
            studyRoomReservationSystem.ERC.add(new StudyRoom(i,50));
        }
        for(int i=0;i<1000;i++) {
            Random random = new Random();
            int roomNum = random.nextInt(10) + 1;
            Thread branch = new Task(roomNum,i) {


                public void run() {
                    try {
                        studyRoomReservationSystem.reserveStudyRoom(roomNum);
                    } catch (StudyRoomUnavailableException e) {
                            System.err.println(e.getMessage()+"by branch "+getTaskID()+"during access to room "+getRoomNumber());
                    }
                    studyRoomReservationSystem.releaseStudyRoom(roomNum);
                }
            };
            branch.start();
        }
        studyRoomReservationSystem.displayStudyRoomStatus();
        System.out.println("Task Count: "+studyRoomReservationSystem.taskCount);
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
