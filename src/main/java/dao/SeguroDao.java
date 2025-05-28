package dao;

import java.util.List;

import dominio.Seguro;



public interface SeguroDao {

	public boolean insert(Seguro seguro);
	public boolean delete(Seguro seguro_a_eliminar);
	public List<Seguro> readAll();
	public List<Seguro> readByTipo(int idTipo);
	
}
