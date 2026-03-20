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
        eliminarPropietario(11);
        leerPropietarios();
        cerrarConexion();
    }

    // ==================== ABRIR CONEXION DB ====================

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

    // ==================== CERRAR CONEXION DB ====================

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

    // ==================== LEER TUPLAS ====================

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

    // ==================== CREAR TUPLAS ====================

    public static void crearPropietario(String nombre, String telefono, String direccion, String email) {
        String sql = "INSERT INTO Propietario (nombre, telefono, direccion, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.setString(3, direccion);
            ps.setString(4, email);
            ps.executeUpdate();
            System.out.println("✓ Propietario '" + nombre + "' creado correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear propietario: " + e.getMessage());
        }
    }

    public static void crearPaciente(String nombre, String especie, String raza, int edad, double peso, int idPropietario) {
        String sql = "INSERT INTO Paciente (nombre, especie, raza, edad, peso, idPropietario) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, especie);
            ps.setString(3, raza);
            ps.setInt(4, edad);
            ps.setDouble(5, peso);
            ps.setInt(6, idPropietario);
            ps.executeUpdate();
            System.out.println("✓ Paciente '" + nombre + "' creado correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear paciente: " + e.getMessage());
        }
    }

    public static void crearVeterinario(String nombre, String especialidad, String telefono) {
        String sql = "INSERT INTO Veterinario (nombre, especialidad, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, especialidad);
            ps.setString(3, telefono);
            ps.executeUpdate();
            System.out.println("✓ Veterinario '" + nombre + "' creado correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear veterinario: " + e.getMessage());
        }
    }

    public static void crearCita(int idPaciente, int idVeterinario, String fechaHora, String tipo) {
        String sql = "INSERT INTO Cita (idPaciente, idVeterinario, fechaHora, tipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.setInt(2, idVeterinario);
            ps.setString(3, fechaHora);
            ps.setString(4, tipo);
            ps.executeUpdate();
            System.out.println("✓ Cita creada correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear cita: " + e.getMessage());
        }
    }

    public static void crearHistorial(int idPaciente, String fecha, String motivo, String tratamiento) {
        String sql = "INSERT INTO Historial (idPaciente, fecha, motivo, tratamiento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.setString(2, fecha);
            ps.setString(3, motivo);
            ps.setString(4, tratamiento);
            ps.executeUpdate();
            System.out.println("✓ Historial creado correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear historial: " + e.getMessage());
        }
    }

    public static void crearVacuna(int idPaciente, String nombre, String fechaAplicacion) {
        String sql = "INSERT INTO Vacuna (idPaciente, nombre, fechaAplicacion) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            ps.setString(2, nombre);
            ps.setString(3, fechaAplicacion);
            ps.executeUpdate();
            System.out.println("✓ Vacuna '" + nombre + "' creada correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear vacuna: " + e.getMessage());
        }
    }

    public static void crearFactura(int idCita, String fecha, double importe, String concepto) {
        String sql = "INSERT INTO Factura (idCita, fecha, importe, concepto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            ps.setString(2, fecha);
            ps.setDouble(3, importe);
            ps.setString(4, concepto);
            ps.executeUpdate();
            System.out.println("✓ Factura creada correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear factura: " + e.getMessage());
        }
    }

    public static void crearUsuario(String username, String password, String rol) {
        String sql = "INSERT INTO Usuario (username, password, rol) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, rol);
            ps.executeUpdate();
            System.out.println("✓ Usuario '" + username + "' creado correctamente.");
        } catch (SQLException e) {
            System.err.println("✗ Error al crear usuario: " + e.getMessage());
        }
    }


// ==================== ELIMINAR TUPLAS ====================

    public static void eliminarPropietario(int idPropietario) {
        String sql = "DELETE FROM Propietario WHERE idPropietario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPropietario);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Propietario con id " + idPropietario + " eliminado.");
            else           System.out.println("⚠ No se encontró propietario con id " + idPropietario + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar propietario: " + e.getMessage());
        }
    }

    public static void eliminarPaciente(int idPaciente) {
        String sql = "DELETE FROM Paciente WHERE idPaciente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Paciente con id " + idPaciente + " eliminado.");
            else           System.out.println("⚠ No se encontró paciente con id " + idPaciente + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar paciente: " + e.getMessage());
        }
    }

    public static void eliminarVeterinario(int idVeterinario) {
        String sql = "DELETE FROM Veterinario WHERE idVeterinario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVeterinario);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Veterinario con id " + idVeterinario + " eliminado.");
            else           System.out.println("⚠ No se encontró veterinario con id " + idVeterinario + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar veterinario: " + e.getMessage());
        }
    }

    public static void eliminarCita(int idCita) {
        String sql = "DELETE FROM Cita WHERE idCita = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Cita con id " + idCita + " eliminada.");
            else           System.out.println("⚠ No se encontró cita con id " + idCita + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar cita: " + e.getMessage());
        }
    }

    public static void eliminarHistorial(int idHistorial) {
        String sql = "DELETE FROM Historial WHERE idHistorial = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHistorial);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Historial con id " + idHistorial + " eliminado.");
            else           System.out.println("⚠ No se encontró historial con id " + idHistorial + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar historial: " + e.getMessage());
        }
    }

    public static void eliminarVacuna(int idVacuna) {
        String sql = "DELETE FROM Vacuna WHERE idVacuna = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVacuna);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Vacuna con id " + idVacuna + " eliminada.");
            else           System.out.println("⚠ No se encontró vacuna con id " + idVacuna + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar vacuna: " + e.getMessage());
        }
    }

    public static void eliminarFactura(int idFactura) {
        String sql = "DELETE FROM Factura WHERE idFactura = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Factura con id " + idFactura + " eliminada.");
            else           System.out.println("⚠ No se encontró factura con id " + idFactura + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar factura: " + e.getMessage());
        }
    }

    public static void eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE idUsuario = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            int filas = ps.executeUpdate();
            if (filas > 0) System.out.println("✓ Usuario con id " + idUsuario + " eliminado.");
            else           System.out.println("⚠ No se encontró usuario con id " + idUsuario + ".");
        } catch (SQLException e) {
            System.err.println("✗ Error al eliminar usuario: " + e.getMessage());
        }
    }

}