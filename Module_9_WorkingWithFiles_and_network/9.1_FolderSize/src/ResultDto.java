public class ResultDto {

     final int FOLDER_NUMBER;
     final int FILES_NUMBER;
     final long FILES_SIZE;

    public ResultDto(int FOLDER_NUMBER, int FILES_NUMBER, long FILES_SIZE) {
        this.FOLDER_NUMBER = FOLDER_NUMBER;
        this.FILES_NUMBER = FILES_NUMBER;
        this.FILES_SIZE = FILES_SIZE;
    }
}

