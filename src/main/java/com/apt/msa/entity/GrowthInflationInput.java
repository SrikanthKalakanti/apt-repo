package com.apt.msa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="growth_inflation_input")
@Table(name="growth_inflation_input")
public class GrowthInflationInput implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long growthIflationId;
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "clientId")
	@JsonBackReference
	private Client client;*/
	
	@JsonProperty("clientId")
	private Long clientId;
	
	private String answer1;
	private String answer2;
	private String answer3;
	private Double numberOfYears;
	private String array1;
	private String array2;
	
	public GrowthInflationInput(){
		
	}
	
	public GrowthInflationInput(Long growthIflationId, Long clientId, String answer1, String answer2,
			String answer3, Double numberOfYears, String array1, String array2) {
		super();
		this.growthIflationId = growthIflationId;
		this.clientId = clientId;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.numberOfYears = numberOfYears;
		this.array1 = array1;
		this.array2 = array2;
	}
	public Long getGrowthIflationId() {
		return growthIflationId;
	}
	public void setGrowthIflationId(Long growthIflationId) {
		this.growthIflationId = growthIflationId;
	}
	
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public Double getNumberOfYears() {
		return numberOfYears;
	}
	public void setNumberOfYears(Double numberOfYears) {
		this.numberOfYears = numberOfYears;
	}
	public String getArray1() {
		return array1;
	}
	public void setArray1(String array1) {
		this.array1 = array1;
	}
	public String getArray2() {
		return array2;
	}
	public void setArray2(String array2) {
		this.array2 = array2;
	}

	@Override
	public String toString() {
		return "GrowthInflationInput [growthIflationId=" + growthIflationId + ", clientId=" + clientId
				+ ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + ", numberOfYears="
				+ numberOfYears + ", array1=" + array1 + ", array2=" + array2 + "]";
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
}
