package com.an.sfs.common;

public class ExchangeDetail {
    private long id;
    // 20140307
    private String date;
    // 09:30
    private String time;
    private float price;
    private int amount;
    // B or S
    private String type;

    public ExchangeDetail() {
    }

    public boolean isBuyIn() {
        return type.equals("B");
    }

    /**
     * @param date
     * @param time
     * @param price
     * @param amount
     * @param type
     */
    public ExchangeDetail(String date, String time, float price, int amount, String type) {
        this.date = date;
        this.time = time;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExchangeDetail [id=" + id + ", " + (date != null ? "date=" + date + ", " : "")
                + (time != null ? "time=" + time + ", " : "") + "price=" + price + ", amount=" + amount + ", "
                + (type != null ? "type=" + type : "") + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
