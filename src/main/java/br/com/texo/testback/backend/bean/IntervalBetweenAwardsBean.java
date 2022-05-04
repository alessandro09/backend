package br.com.texo.testback.backend.bean;

import br.com.texo.testback.backend.projections.IntervalBetweenAwards;

public class IntervalBetweenAwardsBean {
	private Integer previousWin;
	
	private Integer followingWin;
	
	private Integer interval;
	
	private String producer;
	
	public IntervalBetweenAwardsBean() { /** Empty constructor */ }

	public IntervalBetweenAwardsBean(IntervalBetweenAwards intervalBetweenAwards) {
		previousWin = intervalBetweenAwards.getPreviousWin();
		followingWin = intervalBetweenAwards.getFollowingWin();
		interval = intervalBetweenAwards.getInterval();
		producer = intervalBetweenAwards.getProducer();
	}
	
	public Integer getPreviousWin() { return previousWin; }

	public void setPreviousWin(Integer previousWin) { this.previousWin = previousWin; }

	public Integer getFollowingWin() { return followingWin; }

	public void setFollowingWin(Integer followingWin) { this.followingWin = followingWin; }

	public Integer getInterval() { return interval; }

	public void setInterval(Integer interval) { this.interval = interval; }

	public String getProducer() { return producer; }

	public void setProducer(String producer) { this.producer = producer; }
}
