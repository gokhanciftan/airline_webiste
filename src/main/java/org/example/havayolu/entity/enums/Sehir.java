package org.example.havayolu.entity.enums;

public enum Sehir {
    ANKARA("ankara"),
    ISTANBUL("istanbul"),
    IZMIR("izmir"),
    ANTALYA("antalya"),VAN("van"),TRABZON("trabzon"),CANAKKALE("canakkale"),GAZIANTEP("gaziantep");
	
	private String message;

	private Sehir(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
