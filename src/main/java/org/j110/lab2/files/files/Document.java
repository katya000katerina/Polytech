package org.j110.lab2.files.files;

public class Document extends File{
    private int numberOfPages;

    public Document(String name, int sizeInBytes, String fileFormat, int numberOfPages) {
        super(name, sizeInBytes, fileFormat);
        setNumberOfPages(numberOfPages);
    }
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages < 1){
            throw new IllegalArgumentException("Document should consist of at least one page");
        }
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String fileDetailsToString() {
        return String.format("%s, %d pages", super.fileDetailsToString(), numberOfPages);
    }
}
