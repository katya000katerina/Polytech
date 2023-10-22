package org.j110.lab1.countries;

public class Country {
    private String countryName;
    private int countryArea; // country's area is measured in square kilometers
    private int totalPopulation;
    private String capitalName;
    private int capitalPopulation;
    private boolean isTotalPopulationKnown;
    private boolean isCapitalPopulationKnown;

    public Country(String countryName, int countryArea) { // a constructor for a city-state with an unknown population
        setCountryName(countryName);
        setCountryArea(countryArea);
    }

    public Country(String countryName, int countryArea, String capitalName) { // a constructor for a country with an unknown total and capital's population
        this(countryName, countryArea);
        setCapitalName(capitalName);
    }
    public Country(String countryName, int countryArea, int totalPopulation) { // a constructor for a city-state with a known population
        this(countryName, countryArea);
        setTotalPopulation(totalPopulation);
        isTotalPopulationKnown = true;
    }
    public Country(String countryName, int countryArea, int totalPopulation, String capitalName, int capitalPopulation) { // a constructor for a country with known total and capital's population
        this(countryName, countryArea, totalPopulation);
        setCapitalNameAndCapitalPopulation(capitalName, capitalPopulation);
        isCapitalPopulationKnown = true;
    }
    public String getCountryName() {
        return countryName;
    }

    public int getCountryArea() {
        return countryArea;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public int getCapitalPopulation() {
        return capitalPopulation;
    }

    public float getPopulationDensity(){
        return (float) totalPopulation / countryArea;
    }

    public void setCountryName(String countryName) {
        if (countryName == null){
            throw new IllegalArgumentException("Country's name cannot be null");
        }
        else {
            this.countryName = countryName;
        }
    }

    public void setCountryArea(int countryArea) {
        if (countryArea > 0){
            this.countryArea = countryArea;
        }
        else throw new IllegalArgumentException("Area must be greater than zero");
    }

    public void setTotalPopulation(int totalPopulation) {
        if (totalPopulation > 0){
            this.totalPopulation = totalPopulation;
        }
        else throw new IllegalArgumentException("Total population must be greater than zero");
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public void setCapitalPopulation(int capitalPopulation) {
        this.capitalPopulation = capitalPopulation;
    }

    public void setCapitalNameAndCapitalPopulation(String capitalName, int capitalPopulation){
        setCapitalName(capitalName);
        setCapitalPopulation(capitalPopulation);
    }
    public void print(){
        printCountryInformation();
        if (capitalName != null){
            printCapitalInformation();
        }
    }

    public static void printAll(Country[] countries){
        for (Country country : countries){
            country.print();
            System.out.println("\n-----------------");
        }
    }
    private void printCountryInformation(){
        System.out.printf("Country's name: %s\nCountry's area: %d sq km\nTotal population: %s",
                countryName, countryArea, isTotalPopulationKnown ? totalPopulation + " people" : "N/A");
    }

    private void printCapitalInformation(){
        System.out.printf("\nCapital's name: %s\nCapital's population: %s",
                capitalName, isCapitalPopulationKnown ? capitalPopulation + " people" : "N/A");
    }
}
