package com.excilys.training.mapper.resultSetModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultSetModelMapper<T> {
	public abstract T map(ResultSet resultSet) throws SQLException;
}
