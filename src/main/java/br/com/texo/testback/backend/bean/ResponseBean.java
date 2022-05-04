package br.com.texo.testback.backend.bean;

import java.util.List;

public class ResponseBean {
	private List<IntervalBetweenAwardsBean> min;
	
	private List<IntervalBetweenAwardsBean> max;

	public ResponseBean() { /** Empty constructor */ }
	
	public ResponseBean(List<IntervalBetweenAwardsBean> min, List<IntervalBetweenAwardsBean> max) {
		this.min = min;
		this.max = max;
	}
	
	public List<IntervalBetweenAwardsBean> getMin() { return min; }
	
	public List<IntervalBetweenAwardsBean> getMax() { return max; }
}
