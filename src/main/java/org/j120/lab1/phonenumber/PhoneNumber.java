package org.j120.lab1.phonenumber;

import java.util.Objects;

public class PhoneNumber {
    private int regionCode;
    private int localCode; //input without leading zeroes

    public PhoneNumber(int regionCode, int localCode) {
        setLocalCode(localCode);
        setRegionCode(regionCode);
    }

    public void setRegionCode(int regionCode) {
        if (isNegative(regionCode)) {
            throw new IllegalArgumentException("The region code cannot be a negative number");
        }
        if (getNumberOfDigits(regionCode) > 4) {
            throw new IllegalArgumentException("The region code cannot consist of more than 4 digits");
        }
        if (getNumberOfDigits(regionCode) == 4 && (getNumberOfDigits(localCode) == 7)) {
            throw new IllegalArgumentException("Phone number cannot consist of more than 10 digits");
        }
        this.regionCode = regionCode;
    }

    public void setLocalCode(int localCode) {
        if (isNegative(localCode)) {
            throw new IllegalArgumentException("The local code cannot be a negative number");
        }
        if (getNumberOfDigits(localCode) > 7) {
            throw new IllegalArgumentException("The local code cannot consist of more than 7 digits");
        }
        this.localCode = localCode;
    }

    @Override
    public String toString() {
        String region = String.format("(%03d)", regionCode);
        String local = String.format("%08d-%02d-%02d", localCode / 10000, (localCode / 100) % 100, localCode % 100);
        return region + local.substring(region.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PhoneNumber that = (PhoneNumber) o;
        return regionCode == that.regionCode && localCode == that.localCode && (hashCode() == that.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionCode, localCode);
    }

    private int getNumberOfDigits(int number) {
        return (int) Math.log10(number) + 1;
    }

    private boolean isNegative(int number) {
        return number < 0;
    }
}
