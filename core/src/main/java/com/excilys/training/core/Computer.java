package com.excilys.training.core;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author dimitri
 *
 */
@Entity
@Table(name="computer")
public class Computer  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "introduced")
	private LocalDateTime introducedDate;
	
	@Column(name = "discontinued")
	private LocalDateTime discontinuedDate;
	
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
	private Company company;

	public Computer(Long id, String name, LocalDateTime introducedDate, LocalDateTime discontinuedDate,
			Company company) {
		this.id = id;
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discontinuedDate;
		this.company = company;
	}

	public Computer() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDateTime introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDateTime getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDateTime discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public static class Builder {
		private Long id;
		private String name;
		private LocalDateTime introducedDate;
		private LocalDateTime discontinuedDate;
		private Company company;

		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setIntroduced(LocalDateTime introduced) {
			this.introducedDate = introduced;
			return this;
		}

		public Builder setDiscontinued(LocalDateTime discontinued) {
			this.discontinuedDate = discontinued;
			return this;
		}

		public Builder setCompany(Company company) {
			if (company == null) {
				this.company = new Company();
			} else {
				this.company = company;
			}
			return this;
		}

		public Computer build() {
			Computer computer = new Computer();
			computer.setId(this.id);
			computer.setName(this.name);
			computer.setIntroducedDate(this.introducedDate);
			computer.setDiscontinuedDate(this.discontinuedDate);
			computer.setCompany(this.company);
			return computer;
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id = " + getId() +", name=" + name + ", introducedDate=" + introducedDate + ", discontinuedDate="
				+ discontinuedDate + ", company=" + company + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, discontinuedDate, introducedDate, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		return Objects.equals(company, other.company) && Objects.equals(discontinuedDate, other.discontinuedDate)
				&& Objects.equals(introducedDate, other.introducedDate) && Objects.equals(name, other.name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
