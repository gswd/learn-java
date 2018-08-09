package com.hm707.other;

public enum SizeEnum {
    SMALL("S","小号"),
    MEDIUM("M","中号"),
    LARGE("L","大号");
    
    private String abbr;
    private String title;

    SizeEnum(String abbr, String title){
        this.abbr = abbr;
        this.title = title;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getTitle() {
        return title;
    }
    
    public static SizeEnum fromAbbr(String abbr){
        for(SizeEnum size : SizeEnum.values()){
            if(size.getAbbr().equals(abbr)){
                return size;
            }
        }
        return null;
    }
}