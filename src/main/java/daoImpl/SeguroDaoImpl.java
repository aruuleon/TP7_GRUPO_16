package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import dao.SeguroDao;
import dominio.Seguro;


public class SeguroDaoImpl implements SeguroDao{

	private static final String insert = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
	private static final String delete = "DELETE FROM seguros WHERE idSeguro = ?";
	private static final String readall = "SELECT * FROM seguros";
	private static final String readByTipo = "SELECT * FROM seguros WHERE idTipo = ?";
	
	
	public boolean insert(Seguro seguro) throws SQLException {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet generatedKeys = null;
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        conn.setAutoCommit(false);
	        
	        String sql = insert;
	        
	        pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, seguro.getDescripcion());
	        pstmt.setInt(2, seguro.getIdTipo());
	        pstmt.setDouble(3, seguro.getCostoContratacion());
	        pstmt.setDouble(4, seguro.getCostoAsegurado());
	        
	        int affectedRows = pstmt.executeUpdate();
	        
	        if (affectedRows == 0) {
	            throw new SQLException("No se pudo guardar el seguro");
	        }
	        
	        generatedKeys = pstmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            seguro.setIdSeguro(generatedKeys.getInt(1));
	        } else {
	            throw new SQLException("No se obtuvo el ID generado");
	        }
	        
	        conn.commit();
	        return true;
	        
	    } catch (SQLException e) {
	        if (conn != null) conn.rollback();
	        throw e;
	    } finally {
	        if (generatedKeys != null) generatedKeys.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }
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
	
	public int obtenerProximoIdDisponible() {
	    Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = Conexion.getConexion().getSQLConexion();
	        stmt = (Statement) conn.createStatement();
	        
	        rs = stmt.executeQuery("SHOW TABLE STATUS LIKE 'seguros'");
	        
	        if (rs.next()) {
	            return rs.getInt("Auto_increment");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error" + e.getMessage());
	        e.printStackTrace();
	    } finally {
	    	
	    }
	    return 0; 
	}
	

}

	

