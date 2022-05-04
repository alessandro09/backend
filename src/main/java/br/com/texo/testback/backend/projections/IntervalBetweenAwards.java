package br.com.texo.testback.backend.projections;

public interface IntervalBetweenAwards {
	Integer getPreviousWin();
	
	Integer getFollowingWin();
	
	Integer getInterval();
	
	String getProducer();
}
