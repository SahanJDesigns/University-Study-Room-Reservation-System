public class StudyRoomUnavailableException extends Exception{
    String message = "StudyRoomUnavailableException";

    @Override
    public String getMessage() {
        return message;
    }
}
