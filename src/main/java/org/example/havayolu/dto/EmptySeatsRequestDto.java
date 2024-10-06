package org.example.havayolu.dto;

import java.util.Date;

public class EmptySeatsRequestDto {
	private int ucusId;
    private Date date;

    // Getter ve setter metotları
    public int getUcusId() {
        return ucusId;
    }

    public void setUcusId(int ucusId) {
        this.ucusId = ucusId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
