public class Task extends Thread{
    private int roomNumber;
    private int taskID;

    public int getTaskID() {
        return taskID;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    Task(int roomNumber, int taskID){
        this.roomNumber = roomNumber;
        this.taskID = taskID;
    }
}
