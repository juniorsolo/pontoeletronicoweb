package com.ponto.eletronicoweb.enums;

/**
 * 
 * @author junior 22/028/2020
 *
 * Represent type of address
 */
public enum AddressTypeEnum {
	
	RUA("Rua"),
	AVENIDA("Avenida"),
	ESTRADA("Estrada"),
	ALAMEDA("Alameda"),
	AREA("Área"),
	ACESSO("Acesso"),
	RODOVIA("Rodovia"),
	OUTROS("Outros");

	private String name;

	AddressTypeEnum(String name) {
		this.name = name;
	}
	
	public String  getName() {
		return this.name;
	} 
	
}	

