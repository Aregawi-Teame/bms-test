package com.membership.domain;

public enum DayType {
	MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
	private int valueOfTheDay;

	DayType(int valueOfTheDay){
		this.valueOfTheDay =valueOfTheDay;
	}

	public int valueOfTheDay() {
		return valueOfTheDay;
	}
}
