package org.example.havayolu.dto;

public class BiletResponseDto {
	
	private int biletNo;
    private int userId;
    private int koltukNo;
    private int ucusId;
    private double calcprice;
	public BiletResponseDto(int biletNo, int userId, int koltukNo, int ucusId, double calcprice) {
		super();
		this.biletNo = biletNo;
		this.userId = userId;
		this.koltukNo = koltukNo;
		this.ucusId = ucusId;
		this.calcprice = calcprice;
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
		this.ucusId = ucusId;
	}
	public double getCalcprice() {
		return calcprice;
	}
	public void setCalcprice(double calcprice) {
		this.calcprice = calcprice;
	}
    
    

}
