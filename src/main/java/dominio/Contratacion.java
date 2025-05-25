package dominio;

public class Contratacion {

	private int idContratacion;
    private String nombreUsuario;
    private int idSeguro;
    private double costoContratacion;

    public Contratacion() {
    	
    }

    public Contratacion(int idContratacion, String nombreUsuario, int idSeguro, double costoContratacion) {
    	setIdContratacion(idContratacion);
    	setNombreUsuario(nombreUsuario);
    	setIdSeguro(idSeguro);
    	setCostoContratacion(costoContratacion);
    }

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public double getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(double costoContratacion) {
        this.costoContratacion = costoContratacion;
    }

    @Override
    public String toString() {
        return "idContratacion=" + idContratacion + ", nombreUsuario='" + nombreUsuario + ", idSeguro=" + idSeguro + ", costoContratacion=" + costoContratacion;
    }
}
