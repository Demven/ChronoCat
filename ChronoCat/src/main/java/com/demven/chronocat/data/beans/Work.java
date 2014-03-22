package com.demven.chronocat.data.beans;

/**
 * Class that describes some work (act, doing)
 * @author Dmitry Salnikov (Demven)
 * @since 17.03.2014
 */
public class Work {

    private Long id;
    private Category category;
    private String description;
    private String timeStart;
    private String timeEnd;
    private int dateDay;
    private int dateMonth;
    private int dateYear;

    public Work(Long id, Category category, String description, String timeStart, String timeEnd, int dateDay,
                int dateMonth, int dateYear) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dateDay = dateDay;
        this.dateMonth = dateMonth;
        this.dateYear = dateYear;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getTimeStart() {
        return timeStart;
    }
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }



    public String getTimeEnd() {
        return timeEnd;
    }
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }



    public int getDateDay() {
        return dateDay;
    }
    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }


    public int getDateMonth() {
        return dateMonth;
    }
    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }


    public int getDateYear() {
        return dateYear;
    }
    public void setDateYear(int dateYear) {
        this.dateYear = dateYear;
    }
}
