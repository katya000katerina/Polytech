package org.j110.lab2.files;

import org.j110.lab2.files.files.File;

import java.util.Arrays;

public class PrintFiles {
    static public void printAll(File[] files){
        if (files == null){
            throw new IllegalArgumentException("Null array cannot be printed");
        }
        String headingFileName = "File name";
        String headingSize = "Size";
        String headingDetails = "Details";
        int longestFileNameLength = Arrays.stream(files).mapToInt(f -> f.getName().length()).max().orElse(headingFileName.length());
        int numberOfDigitsInBiggestSize = getNumberOfDigitsInBiggestSize(files);
        int fileNameColumnWidth = getColumnWidth(headingFileName, longestFileNameLength);
        int sizeColumnWidth = getColumnWidth(headingSize, numberOfDigitsInBiggestSize);
        int longestDetailsDescriptionLength = Arrays.stream(files).mapToInt(f -> f.fileDetailsToString().length()).max().orElse(headingDetails.length() + 2); // +2 means that two extra spaces are allocated
        String upperAndLowerTableBorder = getTableBorder(fileNameColumnWidth + sizeColumnWidth + longestDetailsDescriptionLength + 2);
        String fileNameColumnBorder = getTableBorder(fileNameColumnWidth);
        String sizeColumnBorder = getTableBorder(sizeColumnWidth);
        ColumnHeaderPosition fileNameColumnHeaderPosition = new ColumnHeaderPosition(headingFileName, fileNameColumnWidth);
        ColumnHeaderPosition sizeColumnHeaderPosition = new ColumnHeaderPosition(headingSize, sizeColumnWidth);

        System.out.println(upperAndLowerTableBorder);
        printHeader(headingFileName, fileNameColumnHeaderPosition.numberOfSpacesToAllocateForColumnHeader, fileNameColumnHeaderPosition.isOneExtraSpaceNeeded);
        printHeader(headingSize, sizeColumnHeaderPosition.numberOfSpacesToAllocateForColumnHeader, sizeColumnHeaderPosition.isOneExtraSpaceNeeded);
        System.out.printf(" %s \n", headingDetails);
        System.out.println(fileNameColumnBorder + "|" + sizeColumnBorder + "|" + "---------");
        for (File file : files){
            System.out.printf("%-" + fileNameColumnWidth + "s|" + "%" + sizeColumnWidth + "d|" + "%s\n", file.getName(), file.getSizeInBytes(), file.fileDetailsToString());
        }
        System.out.println(upperAndLowerTableBorder);
    }
    static private void printHeader(String header, int numberOfSpaces, boolean extraSpace){
        if (numberOfSpaces != 0){
            String spaces = "%" + numberOfSpaces + "s";
            System.out.printf(spaces +"%s" + spaces + (extraSpace ? " " : "") + "|", " ", header, " ");
        }
        else {
            System.out.printf("%s|", header);
        }
    }
    static private int getNumberOfDigitsInBiggestSize(File[] files){
        int biggestSize = Arrays.stream(files).mapToInt(File::getSizeInBytes).max().orElse(0);
        return (int) Math.log10(biggestSize) + 1;
    }
    static private int getColumnWidth(String header, int attributeBiggestLength){
        return Math.max(header.length(), attributeBiggestLength);
    }
    static private String getTableBorder(int columnWidth){
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < columnWidth; i++){
            border.append("-");
        }
        return border.toString();
    }
    static private class ColumnHeaderPosition{
        int numberOfSpacesToAllocateForColumnHeader;
        boolean isOneExtraSpaceNeeded;

        ColumnHeaderPosition(String header, int columnWidth) {
            int headerLength = header.length();
            if (headerLength >= columnWidth){
                numberOfSpacesToAllocateForColumnHeader = 0;
            }
            else {
                numberOfSpacesToAllocateForColumnHeader = (columnWidth - headerLength)/2;
                isOneExtraSpaceNeeded = (columnWidth - headerLength)%2 == 1;
            }
        }
    }
}
