package com.ponto.eletronicoweb.enums;

/**
 * 
 * @author junior 09/09/2020
 *
 */
public enum TypeRegistryEnum {
	
	E("Eletrônico"),
	M("Manual");
	
	private String descryption;
	
	TypeRegistryEnum(String descryption) {
		this.descryption = descryption;
	}
	
	public String getDescryption() {
		return this.descryption;
	}
}
