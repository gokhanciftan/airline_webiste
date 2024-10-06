package org.example.havayolu.dto;

import java.util.Date;

public class ReservedBiletDto {

	private int userId;
	private int koltukNo;
	private int ucusId;
	private boolean reserved;
	private Date reservationTime;
	private double calcprice;
	private double price;
	private int biletId;

	public ReservedBiletDto(int biletId, int userId, int koltukNo, int ucusId, double calcprice, double price) {
		this.biletId=biletId;
		this.userId = userId;
		this.koltukNo = koltukNo;
		this.ucusId = ucusId;
		this.reserved = true;
		this.reservationTime = new Date(System.currentTimeMillis());
		this.calcprice = calcprice;
		this.price = price;
	}
	
	

	public int getBiletId() {
		return biletId;
	}



	public void setBiletId(int biletId) {
		this.biletId = biletId;
	}



	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getKoltukNo() {
		return koltukNo;
	}

	public void setKoltukNo(int koltukNo) {
		this.koltukNo = koltukNo;
	}

	public int getUcusId() {
		return ucusId;
	}

	public void setUcusId(int ucusId) {
		this.ucusId = ucusId;
	}

	public boolean reserved() {
		return reserved;
	}

	public void setReserved(boolean isReserved) {
		this.reserved = isReserved;
	}

	public Date getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

	public double getCalcprice() {
		return calcprice;
	}

	public void setCalcprice(double calcprice) {
		this.calcprice = calcprice;
	}

}
