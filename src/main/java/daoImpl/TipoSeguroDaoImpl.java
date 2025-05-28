package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ITipoSeguroDao; // Importa la interfaz
import dominio.TipoSeguro;

public class TipoSeguroDaoImpl implements ITipoSeguroDao {

   

    public TipoSeguroDaoImpl() {
        
    }

    @Override
    public ArrayList<TipoSeguro> obtenerTodosLosTiposDeSeguro() {
        ArrayList<TipoSeguro> listaTipos = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();  // hay  que adaptar los metodos de la clase conexion para que se conecte bien 
            String sql = "SELECT idTipo, descripcion FROM tipoSeguros";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TipoSeguro tipo = new TipoSeguro();
                tipo.setIdTipo(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                listaTipos.add(tipo);
            }
        } catch (SQLException e) {
            System.err.println("Error de SQL ");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado ");
            e.printStackTrace();
        } finally {
           
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error ");
                e.printStackTrace();
            }
            Conexion.cerrarConexion(conn);   // adaptar metodos desde la clase conexion
        }
        return listaTipos;
    }
}