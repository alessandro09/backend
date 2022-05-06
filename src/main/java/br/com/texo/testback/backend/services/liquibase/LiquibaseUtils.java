package br.com.texo.testback.backend.services.liquibase;

import static java.util.Arrays.asList;

import java.util.Set;
import java.util.stream.Collectors;

public class LiquibaseUtils {
	
	private LiquibaseUtils() {  }
	
	public static Set<String> splitStringList(String value) {
		return asList(value.split("(?=and\\s+|,)"))
				.stream()
				.map(it -> {
					return it.replaceAll("and\s+|,\s+", "").trim();
				})
				.filter(it -> !it.isEmpty())
				.collect(Collectors.toSet());
	}
}
