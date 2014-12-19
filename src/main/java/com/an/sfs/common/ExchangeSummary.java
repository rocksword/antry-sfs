package com.an.sfs.common;

public class ExchangeSummary {
    private String date;
    // Total exchange times
    private int totalTimes;
    // Total exchange money
    private int totalMoney;
    // Buy in money
    private int inMoney;
    // Buy out money
    private int outMoney;

    public ExchangeSummary(String date) {
        this.date = date;
    }

    public void incrementTimes() {
        this.totalTimes++;
    }

    /**
     * @param detail
     */
    public void incrementMoney(ExchangeDetail detail) {
        float sum = detail.getPrice() * detail.getAmount() / 100;
        this.totalMoney += sum;

        if (detail.isBuyIn()) {
            this.inMoney += sum;
        } else {
            this.outMoney += sum;
        }
    }

    @Override
    public String toString() {
        return date + ", " + (inMoney - outMoney) + ", " + totalMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public int getInMoney() {
        return inMoney;
    }

    public void setInMoney(int inMoney) {
        this.inMoney = inMoney;
    }

    public int getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(int outMoney) {
        this.outMoney = outMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
}
