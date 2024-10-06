package org.example.havayolu.dto;


public class CreateBiletDto {
    private int userId;
    private int koltukNo;
    private int ucusId;
    private boolean gidisdonus;

    public CreateBiletDto(int userId, int koltukNo, int ucusId,boolean gidisdonus) {
        this.userId = userId;
        this.koltukNo = koltukNo;
        this.ucusId = ucusId;
        this.gidisdonus=gidisdonus;
    }
    public boolean isGidisdonus() {
		return gidisdonus;
	}
	public void setGidisdonus(boolean gidisdonus) {
		this.gidisdonus = gidisdonus;
	}
	public CreateBiletDto() {
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
}
