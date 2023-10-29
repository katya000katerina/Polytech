package org.j110.lab2.files.files;

public class File {
    private String name;
    private int sizeInBytes;
    private String fileFormat;

    public File(String name, int sizeInBytes, String fileFormat) {
        setName(name);
        setSizeInBytes(sizeInBytes);
        setFileFormat(fileFormat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null){
            throw new IllegalArgumentException("File name cannot be null");
        }
        this.name = name;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(int sizeInBytes) {
        if (sizeInBytes < 0){
            throw new IllegalArgumentException("File size in bytes cannot be a negative number");
        }
        this.sizeInBytes = sizeInBytes;
    }
    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        if (fileFormat == null){
            throw new IllegalArgumentException("File format cannot be null");
        }
        this.fileFormat = fileFormat;
    }
    public String fileDetailsToString(){
        return fileFormat;
    }
}
