package org.paudev;

import java.sql.*;
import java.util.ArrayList;

record Propietario(int idPropietario, String nombre, String telefono, String direccion, String email) {}
record Paciente(int idPaciente, String nombre, String especie, String raza, int edad, double peso, int idPropietario) {}
record Veterinario(int idVeterinario, String nombre, String especialidad, String telefono) {}
record Cita(int idCita, int idPaciente, int idVeterinario, String fechaHora, String tipo){}
record Historial(int idHistorial, int idPaciente, String fecha, String motivo, String tratamiento) {}
record Vacuna(int idVacuna, int idPaciente, String nombre, String fechaAplicacion) {}
record Factura(int idFactura, int idCita, String fecha, double importe, String concepto) {}
record Usuario(int idUsuario, String username, String password, String rol) {}


public class Main {

    static Connection con = null;

    static ArrayList<Propietario> listaPropietarios = new ArrayList<>();
    static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    static ArrayList<Veterinario> listaVeterinarios = new ArrayList<>();
    static ArrayList<Cita> listaCitas = new ArrayList<>();
    static ArrayList<Historial> listaHistorial = new ArrayList<>();
    static ArrayList<Vacuna> listaVacunas = new ArrayList<>();
    static ArrayList<Factura> listaFacturas = new ArrayList<>();
    static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("SISTEMA DE CLÍNICA VETERINARIA INTEGRADO");
        conectarBD();
        leerPropietarios();
        leerPacientes();
        leerVeterinarios();
        leerCitas();
        leerHistorial();
        leerVacunas();
        leerFacturas();
        leerUsuarios();
        cerrarConexion();
    }

    public static void conectarBD() {
        String url = "jdbc:postgresql://ep-lively-sunset-aby2qoj7-pooler.eu-west-2.aws.neon.tech:5432/proyecto_alumno4?sslmode=require";
        String usuario = "neondb_owner";
        String password = "npg_3FCiZhx7VnBo";

        System.out.println("Conectando a la base de datos...");
        try {
            con = DriverManager.getConnection(url, usuario, password);
            if (con != null && !con.isClosed()) {
                System.out.println("Conexión exitosa\n");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void leerPropietarios() {
        String sql = "SELECT * FROM Propietario";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo PROPIETARIOS...");
                listaPropietarios.clear();
                while (rs.next()) {
                    Propietario p = new Propietario(
                            rs.getInt("idPropietario"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("email")
                    );
                    listaPropietarios.add(p);
                    System.out.println(p);
                }
                System.out.println("   ✓ " + listaPropietarios.size() + " propietarios cargados\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer propietarios: " + e.getMessage());
        }
    }

    public static void leerPacientes() {
        String sql = "SELECT * FROM Paciente";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo PACIENTES...");
                while (rs.next()) {
                    Paciente p = new Paciente(
                            rs.getInt("idPaciente"),
                            rs.getString("nombre"),
                            rs.getString("especie"),
                            rs.getString("raza"),
                            rs.getInt("edad"),
                            rs.getDouble("peso"),
                            rs.getInt("idPropietario")
                    );
                    listaPacientes.add(p);
                    System.out.println(p);
                }
                System.out.println("   ✓ " + listaPacientes.size() + " pacientes cargados\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer pacientes: " + e.getMessage());
        }
    }

    public static void leerVeterinarios() {
        String sql = "SELECT * FROM Veterinario";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo VETERINARIOS...");
                while (rs.next()) {
                    Veterinario v = new Veterinario(
                            rs.getInt("idVeterinario"),
                            rs.getString("nombre"),
                            rs.getString("especialidad"),
                            rs.getString("telefono")
                    );
                    listaVeterinarios.add(v);
                    System.out.println(v);
                }
                System.out.println("   ✓ " + listaVeterinarios.size() + " veterinarios cargados\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer veterinarios: " + e.getMessage());
        }
    }

    public static void leerCitas() {
        String sql = "SELECT * FROM Cita";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo CITAS...");
                while (rs.next()) {
                    Cita c = new Cita(
                            rs.getInt("idCita"),
                            rs.getInt("idPaciente"),
                            rs.getInt("idVeterinario"),
                            rs.getString("fechaHora"),
                            rs.getString("tipo")
                    );
                    listaCitas.add(c);
                    System.out.println(c);
                }
                System.out.println("   ✓ " + listaCitas.size() + " citas cargadas\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer citas: " + e.getMessage());
        }
    }

    public static void leerHistorial() {
        String sql = "SELECT * FROM Historial";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo HISTORIAL...");
                while (rs.next()) {
                    Historial h = new Historial(
                            rs.getInt("idHistorial"),
                            rs.getInt("idPaciente"),
                            rs.getString("fecha"),
                            rs.getString("motivo"),
                            rs.getString("tratamiento")
                    );
                    listaHistorial.add(h);
                    System.out.println(h);
                }
                System.out.println("   ✓ " + listaHistorial.size() + " registros de historial cargados\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer historial: " + e.getMessage());
        }
    }

    public static void leerVacunas() {
        String sql = "SELECT * FROM Vacuna";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo VACUNAS...");
                while (rs.next()) {
                    Vacuna v = new Vacuna(
                            rs.getInt("idVacuna"),
                            rs.getInt("idPaciente"),
                            rs.getString("nombre"),
                            rs.getString("fechaAplicacion")
                    );
                    listaVacunas.add(v);
                    System.out.println(v);
                }
                System.out.println("   ✓ " + listaVacunas.size() + " vacunas cargadas\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer vacunas: " + e.getMessage());
        }
    }

    public static void leerFacturas() {
        String sql = "SELECT * FROM Factura";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo FACTURAS...");
                while (rs.next()) {
                    Factura f = new Factura(
                            rs.getInt("idFactura"),
                            rs.getInt("idCita"),
                            rs.getString("fecha"),
                            rs.getDouble("importe"),
                            rs.getString("concepto")
                    );
                    listaFacturas.add(f);
                    System.out.println(f);
                }
                System.out.println("   ✓ " + listaFacturas.size() + " facturas cargadas\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer facturas: " + e.getMessage());
        }
    }

    public static void leerUsuarios() {
        String sql = "SELECT * FROM Usuario";
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                System.out.println("Leyendo USUARIOS...");
                while (rs.next()) {
                    Usuario u = new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("rol")
                    );
                    listaUsuarios.add(u);
                    System.out.println(u);
                }
                System.out.println("   ✓ " + listaUsuarios.size() + " usuarios cargados\n");
                rs.close();
                st.close();
            }
        } catch (Exception e) {
            System.err.println("✗ Error al leer usuarios: " + e.getMessage());
        }
    }

    public static void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("\nConexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}