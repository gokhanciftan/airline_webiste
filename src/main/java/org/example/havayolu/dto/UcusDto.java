package org.example.havayolu.dto;

import org.example.havayolu.entity.enums.Sehir;

import java.util.Date;

public class UcusDto {
    private int id;
    private Date date;
    private Sehir kalkis;
    private Sehir inis;
    private int price;

    public UcusDto() {
    }

    public UcusDto(int id, Date date, Sehir kalkis, Sehir inis, int price) {
        this.id = id;
        this.date = date;
        this.kalkis = kalkis;
        this.inis = inis;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Sehir getKalkis() {
        return kalkis;
    }

    public void setKalkis(Sehir kalkis) {
        this.kalkis = kalkis;
    }

    public Sehir getInis() {
        return inis;
    }

    public void setInis(Sehir inis) {
        this.inis = inis;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price =price;}
}