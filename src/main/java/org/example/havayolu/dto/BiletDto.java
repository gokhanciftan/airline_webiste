package org.example.havayolu.dto;

import java.util.Date;

public class BiletDto {
    private int biletNo;
    private int userId;
    private int koltukNo;
    private int ucusId;
    private boolean reserved;
    private Date reservationTime;
    private double calcprice;

    public BiletDto(int biletNo, int userId, int koltukNo, int ucusId,boolean reserved,Date reservationTime,double calcprice) {
        this.biletNo = biletNo;
        this.userId = userId;
        this.koltukNo = koltukNo;
        this.ucusId = ucusId;
        this.reserved=reserved;
        this.reservationTime=reservationTime;
        this.calcprice=calcprice;
    }


	public int getBiletNo() {
        return biletNo;
    }

    public void setBiletNo(int biletNo) {
        this.biletNo = biletNo;
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
        this.ucusId = ucusId;}
	
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
		this.reservationTime = reservationTime;}
	
	public double getCalcprice() {
		return calcprice;
	}

	public void setCalcprice(int calcprice) {
		this.calcprice = calcprice;
	}
}