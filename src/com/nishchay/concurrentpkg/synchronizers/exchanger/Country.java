package com.nishchay.concurrentpkg.synchronizers.exchanger;

public class Country {

    private String countryName;
    private String language;
    private String ccy;

    public Country(String countryName, String language, String ccy) {
        this.countryName = countryName;
        this.language = language;
        this.ccy = ccy;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", language='" + language + '\'' +
                ", ccy='" + ccy + '\'' +
                '}';
    }

}
