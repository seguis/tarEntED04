/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.aplicacion;

import hotel.aplicacion.Utilidades;

/**
 *
 * @author Seguis
 */
public class Cliente {
    private static int contadorClientes = 0;
    private int codigo;
    private String nombre;
    private String dni;
    private String telefono;

    
    public Cliente(String nombre, String dni, String telefono) throws Exception {
        // Validación del DNI, si no es correcto no creará el objeto
        Utilidades.validarDNI(dni); 
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.codigo = obtenerNumeroCliente();
    }

        private static int obtenerNumeroCliente() {
        setContadorClientes(getContadorClientes() + 1);
        return getContadorClientes();
    }

    
    public String mostrarInformacion() {
        return "Código: " + getCodigo() + ", Nombre: " + getNombre() + ", DNI: " + getDni() + ", Teléfono: " + getTelefono();
    }

    /**
     * @return the contadorClientes
     */
    public static int getContadorClientes() {
        return contadorClientes;
    }

    /**
     * @param aContadorClientes the contadorClientes to set
     */
    public static void setContadorClientes(int aContadorClientes) {
        contadorClientes = aContadorClientes;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
