package com.ponto.eletronicoweb.dto;

import java.time.LocalDateTime;

/**
 * 
 * @author junior 18/08/2020
 *   
 * The DTO send for report 
 */
public class RegistryDTO {
		
	private LocalDateTime day;
	private String dayFormated;
	private String dayWeek;
	private String register1;
	private String register2;
	private String register3;
	private String register4;
	private String register5;
	private String register6;
	private String daySum;
	
	
	public LocalDateTime getDay() {
		return day;
	}
	public void setDay(LocalDateTime day) {
		this.day = day;
	}	
	public String getDayFormated() {
		return dayFormated;
	}
	public void setDayFormated(String dayFormated) {
		this.dayFormated = dayFormated;
	}
	public String getDayWeek() {
		return dayWeek;
	}
	public void setDayWeek(String dayWeek) {
		this.dayWeek = dayWeek;
	}
	public String getRegister1() {
		return register1;
	}
	public void setRegister1(String register1) {
		this.register1 = register1;
	}
	public String getRegister2() {
		return register2;
	}
	public void setRegister2(String register2) {
		this.register2 = register2;
	}
	public String getRegister3() {
		return register3;
	}
	public void setRegister3(String register3) {
		this.register3 = register3;
	}
	public String getRegister4() {
		return register4;
	}
	public void setRegister4(String register4) {
		this.register4 = register4;
	}
	public String getRegister5() {
		return register5;
	}
	public void setRegister5(String register5) {
		this.register5 = register5;
	}
	public String getRegister6() {
		return register6;
	}
	public void setRegister6(String register6) {
		this.register6 = register6;
	}
	public String getDaySum() {
		return daySum;
	}
	public void setDaySum(String daySum) {
		this.daySum = daySum;
	}

	
}
