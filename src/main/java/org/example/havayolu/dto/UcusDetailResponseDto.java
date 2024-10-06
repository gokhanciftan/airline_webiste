package org.example.havayolu.dto;

public class UcusDetailResponseDto {
	String kalkis;
	String varis;
	String calcprice;
	
	public UcusDetailResponseDto(String kalkis, String varis, String calcprice) {
		this.kalkis = kalkis;
		this.varis = varis;
		this.calcprice = calcprice;
	}

	public String getKalkis() {
		return kalkis;
	}

	public void setKalkis(String kalkis) {
		this.kalkis = kalkis;
	}

	public String getVaris() {
		return varis;
	}

	public void setVaris(String varis) {
		this.varis = varis;
	}

	public String getCalcprice() {
		return calcprice;
	}

	public void setCalcprice(String calcprice) {
		this.calcprice = calcprice;
	}
	
}
