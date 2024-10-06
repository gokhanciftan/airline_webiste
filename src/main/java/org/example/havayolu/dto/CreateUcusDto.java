package org.example.havayolu.dto;

import java.util.Date;

import org.example.havayolu.entity.enums.Sehir;

public class CreateUcusDto {
	
	private Date date;
    private Sehir kalkis;
    private Sehir inis;
    private int price;
	public CreateUcusDto(Date date, Sehir kalkis, Sehir inis, int price) {
		super();
		this.date = date;
		this.kalkis = kalkis;
		this.inis = inis;
		this.price = price;
	}
	public CreateUcusDto() {
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
		this.price = price;
	}
	
    

}
