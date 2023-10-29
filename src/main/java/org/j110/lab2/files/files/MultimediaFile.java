package org.j110.lab2.files.files;

import org.j110.lab2.files.filesmetadata.MultimediaFileLength;

public class MultimediaFile extends File {
    private String description;
    private MultimediaFileLength multimediaFileLength;

    public MultimediaFile(String name, int sizeInBytes, String fileFormat, String description, int lengthInSeconds) {
        super(name, sizeInBytes, fileFormat);
        setDescription(description);
        multimediaFileLength = new MultimediaFileLength(lengthInSeconds);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null){
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    public MultimediaFileLength getMultimediaFileLength() {
        return multimediaFileLength;
    }

    public void setMultimediaFileLength(int lengthInSeconds) {
        multimediaFileLength.setLengthInSeconds(lengthInSeconds);
    }

    @Override
    public String fileDetailsToString() {
        return String.format("%s, %s, %s", super.fileDetailsToString(),
                description, multimediaFileLength);
    }
}
