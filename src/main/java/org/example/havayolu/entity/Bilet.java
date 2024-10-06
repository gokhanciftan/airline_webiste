package org.example.havayolu.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "users")
    private Users user;
    private int koltukNo;
    @ManyToOne
    @JoinColumn(name = "ucus")
    private Ucus ucus;
    private boolean reserved;
    private Date reservationTime;
    private double price;
    private double calcprice;
    private boolean gidisdonus;


	public Bilet( Users user, int koltukNo, Ucus ucus,boolean reserved,double calcprice) {
        this.user = user;
        this.koltukNo = koltukNo;
        this.ucus = ucus;
        this.reserved = reserved;
        this.reservationTime = new Date(System.currentTimeMillis());
        this.price = ucus.getPrice();
        this.calcprice=calcprice;
    }
	public Bilet(Users user, int koltukNo, Ucus ucus,double calcprice,boolean gidisdonus) {
        this.user = user;
        this.koltukNo = koltukNo;
        this.ucus = ucus;
        this.reserved = true;
        this.reservationTime = new Date(System.currentTimeMillis());
        this.price = ucus.getPrice();
        this.calcprice=calcprice;
        this.gidisdonus=gidisdonus;
    }

	public Bilet() {}


	public Bilet(Users user, int koltukNo, Ucus ucus,boolean gidisdonus) {
        this.user = user;
        this.koltukNo = koltukNo;
        this.ucus = ucus;
        this.price = ucus.getPrice();
        this.gidisdonus=gidisdonus;
       
    }

	public double getCalcprice() {
		return calcprice;
	}



	public void setCalcprice(double calcprice) {
		this.calcprice = calcprice;
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getKoltukNo() {
        return koltukNo;
    }

    public void setKoltukNo(int koltukNo) {
        this.koltukNo = koltukNo;
    }

    public Ucus getUcus() {
        return ucus;
    }

    public void setUcus(Ucus ucus) {
        this.ucus = ucus;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public boolean getReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	public Date getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}
	public boolean isGidisdonus() {
		return gidisdonus;
	}
	public void setGidisdonus(boolean gidisdonus) {
		this.gidisdonus = gidisdonus;
	}
	
}
