package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SeguroDao;
import dominio.Seguro;


public class SeguroDaoImpl implements SeguroDao{

	private static final String insert = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
	private static final String delete = "DELETE FROM seguros WHERE idSeguro = ?";
	private static final String readall = "SELECT * FROM seguros";
	private static final String readByTipo = "SELECT * FROM seguros WHERE idTipo = ?";
	
	
	public boolean insert(dominio.Seguro seguro) {
	
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(2, seguro.getDescripcion());
			statement.setInt(1, seguro.getIdTipo());
			statement.setDouble(3, seguro.getCostoContratacion());
			statement.setDouble(4, seguro.getCostoAsegurado());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
		
	}
	
	
	public boolean delete(dominio.Seguro seguro_a_eliminar) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(seguro_a_eliminar.getIdSeguro()));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	
	// ESTA ES LA CLASE QUE IMPLEMENTA LA INTERFAZ
	
// esta funcion no esta declarada en la interfaz agregala Emiii
	
	public Seguro getSeguro(ResultSet resultSet) throws SQLException {
	    int id = resultSet.getInt("idSeguro");
	    String descripcion = resultSet.getString("descripcion");
	    int idTipo = resultSet.getInt("idTipo");
	    double costoContratacion = resultSet.getDouble("costoContratacion");
	    double costoAsegurado = resultSet.getDouble("costoAsegurado");
	    Seguro seguro = new Seguro(id, descripcion, idTipo, costoContratacion, costoAsegurado);
	    System.out.println("Seguro creado: " + seguro);
	    return seguro;
	}
	
	
	public List<Seguro> readAll() {
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    ArrayList<Seguro> seguros = new ArrayList<>();
	    Conexion conexion = Conexion.getConexion();
	    
	    try {
	        Connection conn = conexion.getSQLConexion();
	        if (conn == null) {
	            throw new SQLException("No se pudo obtener la conexión a la base de datos.");
	        }
	        statement = conn.prepareStatement(readall);
	        resultSet = statement.executeQuery();
	        // ACA USO EL GET SEGURO DE ARRIBA
	        while(resultSet.next()) {
	            seguros.add(getSeguro(resultSet));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return seguros;
	}
	
	
// esta funcion no uso en la interfaz agregala EMIIII
public List<Seguro> readbByTipo(int idTipo) {
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Seguro> seguros = new ArrayList<Seguro>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByTipo);
			statement.setInt(1, idTipo);
			resultSet = statement.executeQuery();
			
			
			while(resultSet.next())
			{
				
			            Seguro seguro = new Seguro();
			            seguro.setIdSeguro(resultSet.getInt("idSeguro"));
			            seguro.setDescripcion(resultSet.getString("descripcion"));
			            seguro.setIdTipo(resultSet.getInt("idTipo"));
			            seguro.setCostoContratacion(resultSet.getDouble("costoContratacion"));
			            seguro.setCostoAsegurado(resultSet.getDouble("costoAsegurado"));
			            seguros.add(seguro);
			}
			
		}
			
			
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return seguros;
		
		
	}


@Override
public List<Seguro> readByTipo(int idTipo) {
	// TODO Auto-generated method stub
	return null;
}


}

	

