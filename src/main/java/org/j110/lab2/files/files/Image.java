package org.j110.lab2.files.files;

import org.j110.lab2.files.filesmetadata.ImageSize;

public class Image extends File{
    private ImageSize imageSize;

    public Image(String name, int sizeInBytes, String fileFormat, int width, int height) {
        super(name, sizeInBytes, fileFormat);
        imageSize = new ImageSize(width, height);
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    public void setImageSize(int width, int height) {
        imageSize.setWidthAndHeight(width, height);
    }

    @Override
    public String fileDetailsToString() {
        return String.format("%s, %s", super.fileDetailsToString(), imageSize);
    }
}
