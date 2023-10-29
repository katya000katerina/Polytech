package org.j110.lab2.files.filesmetadata;

public class MultimediaFileLength {
    private int lengthInSeconds;

    public MultimediaFileLength(int lengthInSeconds) {
        setLengthInSeconds(lengthInSeconds);
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(int lengthInSeconds) {
        if (lengthInSeconds < 1){
            throw new IllegalArgumentException("Length should be at least 1 second");
        }
        this.lengthInSeconds = lengthInSeconds;
    }

    @Override
    public String toString() {
        int hours = lengthInSeconds/3600;
        int minutes = lengthInSeconds/60 - 60*hours;
        int seconds = lengthInSeconds%60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
