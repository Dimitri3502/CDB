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
	private LocalDateTime introduced;
	
	@Column(name = "discontinued")
	private LocalDateTime discontinued;
	
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
	private Company company;

	public Computer(Long id, String name, LocalDateTime introduced, LocalDateTime discontinued,
			Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
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

	public static class Builder {
		private Long id;
		private String name;
		private LocalDateTime introduced;
		private LocalDateTime discontinued;
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
			this.introduced = introduced;
			return this;
		}

		public Builder setDiscontinued(LocalDateTime discontinued) {
			this.discontinued = discontinued;
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
			computer.setIntroduced(this.introduced);
			computer.setDiscontinued(this.discontinued);
			computer.setCompany(this.company);
			return computer;
		}
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [id = " + getId() +", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(company, discontinued, introduced, name);
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
		return Objects.equals(company, other.company) && Objects.equals(discontinued, other.discontinued)
				&& Objects.equals(introduced, other.introduced) && Objects.equals(name, other.name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
