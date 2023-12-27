package com.example.apienglish.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ansgrammar")
public class AnsGrammar {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnsGrammar;

    private String answer;
    private String name;
    private String explainTest;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Unit unit;
    

	public AnsGrammar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AnsGrammar(long idAnsGrammar, String answer, String name, String explainTest, Unit unit) {
		super();
		this.idAnsGrammar = idAnsGrammar;
		this.answer = answer;
		this.name = name;
		this.explainTest = explainTest;
		this.unit = unit;
	}

	public long getIdAnsGrammar() {
		return idAnsGrammar;
	}

	public void setIdAnsGrammar(long idAnsGrammar) {
		this.idAnsGrammar = idAnsGrammar;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplainTest() {
		return explainTest;
	}

	public void setExplainTest(String explainTest) {
		this.explainTest = explainTest;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}

