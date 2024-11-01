/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.gestiones;

/**
 *
 * @author fuent
 */
public class UsuarioModel {
    private int id_persona;

    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    public UsuarioModel() {}
        public int getId_persona() {
            return id_persona;
        }

        public void setId_persona(int id_persona) {
            this.id_persona = id_persona;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
    
    }

