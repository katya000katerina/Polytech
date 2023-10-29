package org.j110.lab2.files.filesmetadata;

public class ImageSize{
    private int width;
    private int height;

    public ImageSize(int width, int height) {
        setWidthAndHeight(width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 1){
            throw new IllegalArgumentException("Width should be 1 or greater than 1 pixel");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 1){
            throw new IllegalArgumentException("Height should be 1 or greater than 1 pixel");
        }
        this.height = height;
    }

    public void setWidthAndHeight(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    @Override
    public String toString() {
        return String.format("%dx%d",width, height);
    }
}
