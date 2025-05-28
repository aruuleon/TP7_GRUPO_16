package dao;

import java.util.ArrayList;
import dominio.TipoSeguro;

public interface ITipoSeguroDao {
    ArrayList<TipoSeguro> obtenerTodosLosTiposDeSeguro();
}