package dao;

import java.sql.SQLException;
import java.util.List;

import dominio.Seguro;



public interface SeguroDao {

	public boolean insert(Seguro seguro) throws SQLException;
	public boolean delete(Seguro seguro_a_eliminar);
	public List<Seguro> readAll();
	public List<Seguro> readByTipo(int idTipo);
	
}
