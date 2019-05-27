package com.excilys.training.core;

/**
 * @author dimitri
 *
 */

public class Company extends Model{
	
	

	public Company(Long id, String name) {
		super(id);
		Name = name;
	}

	public Company() {
		super();
	}


	private String Name;

	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Company [Name=" + Name + "]";
	}
	
	public static class Builder {
		private Long id;
		private String name;


		public Builder setId(Long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Company build() {
			Company company = new Company();
			company.setId(this.id);
			company.setName(this.name);
			return company;
		}	
	}  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}




}
