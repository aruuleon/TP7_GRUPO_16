package dominio;

public class Seguro {

	private int idSeguro;
    private String descripcion;
    private int idTipo;
    private double costoContratacion;
    private double costoAsegurado;

    public Seguro() {
    	
    }

    public Seguro(int idSeguro, String descripcion, int idTipo, double costoContratacion, double costoAsegurado) {
    	setIdSeguro(idSeguro);
    	setDescripcion(descripcion);
    	setIdTipo(idTipo);
    	setCostoContratacion(costoContratacion);
    	setCostoAsegurado(costoAsegurado);
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public double getCostoContratacion() {
        return costoContratacion;
    }

    public void setCostoContratacion(double costoContratacion) {
        this.costoContratacion = costoContratacion;
    }

    public double getCostoAsegurado() {
        return costoAsegurado;
    }

    public void setCostoAsegurado(double costoAsegurado) {
        this.costoAsegurado = costoAsegurado;
    }

    @Override
    public String toString() {
        return "idSeguro=" + idSeguro + ", descripcion='" + descripcion + ", idTipo=" + idTipo + ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado;
    }
}
