package com.excilys.training.persistance;

import java.sql.Timestamp;
import java.util.Objects;

import com.excilys.training.model.Computer;

class SQLComputer {

    private Timestamp discontinued;
    private Long id;
    private Timestamp introduced;
    private Long companyId;
    private String name;

    static SQLComputer from(Computer computer) {
        final SQLComputer sqlComputer = new SQLComputer();
        sqlComputer.setId(computer.getId());
        sqlComputer.setName(computer.getName());
        if (Objects.nonNull(computer.getCompany().getId())) {
            sqlComputer.setCompanyId(computer.getCompany().getId());
        }
        if (Objects.nonNull(computer.getIntroducedDate())) {
            sqlComputer.setIntroduced(Timestamp.valueOf(computer.getIntroducedDate()));
        }
        if (Objects.nonNull(computer.getDiscontinuedDate())) {
            sqlComputer.setDiscontinued(Timestamp.valueOf(computer.getDiscontinuedDate()));
        }
        return sqlComputer;
    }

    public Timestamp getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Timestamp discontinued) {
        this.discontinued = discontinued;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getIntroduced() {
        return introduced;
    }

    public void setIntroduced(Timestamp introduced) {
        this.introduced = introduced;
    }


    public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}