package com.an.sfs.common;

/**
 * 
 * @author Anthony
 * 
 */
public class Exchange {
    public static final int MA1_DAY_NUM = 5;
    public static final int MA2_DAY_NUM = 10;
    public static final int MA3_DAY_NUM = 20;
    // Raw data
    // 2014/05/18
    private String date;
    // Open price
    private float open;
    // Max price
    private float max;
    // Min price
    private float min;
    // Close price
    private float close;
    // Exchange volume
    private float volume;
    // MA 5
    private float ma1;
    // MA 10
    private float ma2;
    // MA 20
    private float ma3;
    // MA 60
    private float ma4;
    // MA 120
    private float ma5;
    // MA 250
    private float ma6;

    public Exchange() {
    }

    public float getTotal1() {
        return ma1 * MA1_DAY_NUM;
    }

    public float getTotal2() {
        return ma1 * MA2_DAY_NUM;
    }

    public float getTotal3() {
        return ma1 * MA3_DAY_NUM;
    }

    /**
     * @param date
     * @param open
     * @param max
     * @param min
     * @param close
     * @param volume
     */
    public void set(String date, float open, float max, float min, float close, float volume) {
        this.date = date;
        this.open = open;
        this.max = max;
        this.min = min;
        this.close = close;
        this.volume = volume;
    }

    /**
     * @param ma1
     * @param ma2
     * @param ma3
     * @param ma4
     * @param ma5
     * @param ma6
     */
    public void set(float ma1, float ma2, float ma3, float ma4, float ma5, float ma6) {
        this.ma1 = ma1;
        this.ma2 = ma2;
        this.ma3 = ma3;
        this.ma4 = ma4;
        this.ma5 = ma5;
        this.ma6 = ma6;
    }

    @Override
    public String toString() {
        return "Exchange [" + (date != null ? "date=" + date + ", " : "") + "close=" + close + ", ma1=" + ma1
                + ", ma2=" + ma2 + ", ma3=" + ma3 + "]";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getMa1() {
        return ma1;
    }

    public void setMa1(float ma1) {
        this.ma1 = ma1;
    }

    public float getMa2() {
        return ma2;
    }

    public void setMa2(float ma2) {
        this.ma2 = ma2;
    }

    public float getMa3() {
        return ma3;
    }

    public void setMa3(float ma3) {
        this.ma3 = ma3;
    }

    public float getMa4() {
        return ma4;
    }

    public void setMa4(float ma4) {
        this.ma4 = ma4;
    }

    public float getMa5() {
        return ma5;
    }

    public void setMa5(float ma5) {
        this.ma5 = ma5;
    }

    public float getMa6() {
        return ma6;
    }

    public void setMa6(float ma6) {
        this.ma6 = ma6;
    }
}
