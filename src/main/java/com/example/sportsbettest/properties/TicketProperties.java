package com.example.sportsbettest.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/*
 * bind ticket related configuration from application.yml file
 */
@Configuration
@ConfigurationProperties(prefix = "ticket")
public class TicketProperties {

	private Map<String, Integer> ageLimit;
	private Map<String, Double> price;
	private ChildrenDiscount childrenDiscount;
	private SeniorDiscount seniorDiscount;

	public Map<String, Integer> getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(Map<String, Integer> ageLimit) {
		this.ageLimit = ageLimit;
	}

	public Map<String, Double> getPrice() {
		return price;
	}

	public void setPrice(Map<String, Double> price) {
		this.price = price;
	}

	public ChildrenDiscount getChildrenDiscount() {
		return childrenDiscount;
	}

	public void setChildrenDiscount(ChildrenDiscount childrenDiscount) {
		this.childrenDiscount = childrenDiscount;
	}

	public SeniorDiscount getSeniorDiscount() {
		return seniorDiscount;
	}

	public void setSeniorDiscount(SeniorDiscount seniorDiscount) {
		this.seniorDiscount = seniorDiscount;
	}

	public static class ChildrenDiscount {
		private int threshold;
		private int percent;

		public int getThreshold() {
			return threshold;
		}

		public void setThreshold(int threshold) {
			this.threshold = threshold;
		}

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

	}

	public static class SeniorDiscount {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}

	}

}
