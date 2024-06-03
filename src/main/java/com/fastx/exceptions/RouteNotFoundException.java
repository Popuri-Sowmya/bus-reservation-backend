package com.fastx.exceptions;

public class RouteNotFoundException extends Exception{
	
	public RouteNotFoundException() {
		
	}
	
	public RouteNotFoundException(String message) {
		super(message);
	}
}
