package org.j110.lab1.books;

public class Publisher {
    private String publisherName;
    private String city;

    public Publisher(String publisherName, String city) {
        setPublisherName(publisherName);
        setCity(city);
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getCity() {
        return city;
    }

    public void setPublisherName(String publisherName) {
        if (publisherName != null){
            this.publisherName = publisherName;
        }
        else throw new IllegalArgumentException("Publisher's name cannot be null");
    }

    public void setCity(String city) {
        if (city != null){
            this.city = city;
        }
        else throw new IllegalArgumentException("City cannot be null");
    }
}
