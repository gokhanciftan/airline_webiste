package org.example.havayolu.entity;

import jakarta.persistence.*;
import org.example.havayolu.entity.enums.Sehir;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ucus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Sehir kalkıs;
    @Enumerated(EnumType.STRING)
    private Sehir inıs;
    private int price;
    @OneToMany(mappedBy = "ucus",cascade = CascadeType.ALL)
    List<Bilet> bilets = new ArrayList<>();
    private String gun;

    public Ucus(int id, Date date, Sehir kalkıs, Sehir inıs, List<Bilet> bilets) {
        this.id = id;
        this.date = date;
        this.kalkıs = kalkıs;
        this.inıs = inıs;
        this.bilets = bilets;
    }

    public Ucus(Date date, Sehir kalkıs, Sehir inıs, int price) {
        this.date = date;
        this.kalkıs = kalkıs;
        this.inıs = inıs;
        this.price = price;
    }
    

    public String getGun() {
		return gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

	public Ucus() {
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

    public Sehir getKalkıs() {
        return kalkıs;
    }

    public void setKalkıs(Sehir kalkıs) {
        this.kalkıs = kalkıs;
    }

    public Sehir getInıs() {
        return inıs;
    }

    public void setInıs(Sehir inıs) {
        this.inıs = inıs;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Bilet> getBilets() {
        return bilets;
    }

    public void setBilets(List<Bilet> bilets) {
        this.bilets = bilets;
    }

}
