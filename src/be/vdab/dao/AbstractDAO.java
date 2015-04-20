package be.vdab.dao;

import javax.sql.DataSource;

abstract class AbstractDAO {
	public final static String JNDI_NAME = "jdbc/pizzaluigi";
	protected DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
