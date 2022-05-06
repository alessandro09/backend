package br.com.texo.testback.backend.services.liquibase;

import static br.com.texo.testback.backend.services.liquibase.LiquibaseUtils.splitStringList;

public class Main {

	public static void main(String[] args) {
		var value = "Michael Patrick King, John Melfi, Sarah Jessica Parker and Darren Star";
		
		System.out.println(splitStringList(value));
	}

}
