package org.example.model;

import org.example.Tarea;
import org.example.utils.MySqlConector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TareasDao {


    public List<Tarea> findAll() {
        List<Tarea> listaTareas = new ArrayList<>();

        return listaTareas;
    }

    public boolean insertarTarea(Tarea tarea) {
        boolean result = false;
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Task (title, description, priority, status, date) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setString(3, tarea.getPrioridad());
            stmt.setString(4, tarea.getEstatus());
            stmt.setString(5, tarea.getFecha());

            if (stmt.executeUpdate() == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar la tarea: " + e.getMessage());
        }

        return result;
    }

    //Nuevo metodo
    public boolean insertarTareaP(Tarea tarea) {
        boolean result = false;
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO Taskk (title, description, priority, status, date) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setString(3, tarea.getPrioridad());
            stmt.setString(4, tarea.getEstatus());
            stmt.setString(5, tarea.getFecha());

            if (stmt.executeUpdate() == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar la tarea: " + e.getMessage());
        }

        return result;
    }


    public boolean eliminarTarea(int id) {
        boolean result = false;
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM Task WHERE id_task = ?"
            );

            stmt.setInt(1, id);

            if (stmt.executeUpdate() == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar la tarea: " + e.getMessage());
        }

        return result;
    }

    public LinkedList<Tarea> obtenerTareas() {
        LinkedList<Tarea> tareas = new LinkedList<>();
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Task");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Tarea tarea = new Tarea();
                tarea.setId(res.getInt("id_task"));
                tarea.setTitulo(res.getString("title"));
                tarea.setDescripcion(res.getString("description"));
                tarea.setPrioridad(res.getString("priority"));
                tarea.setEstatus(res.getString("status"));
                tarea.setFecha(res.getString("date"));
                tareas.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la excepción para depuración
            System.out.println("Error al obtener tareas: " + e.getMessage());
        } finally {
            conector.closeConnection(connection); // Cerrar la conexion en caso de exception
        }

        //System.out.println("Número de tareas recuperadas: " + tareas.size());
        return tareas;
    }

    //metodo nuevo
    public LinkedList<Tarea> obtenerTareasP() {
        LinkedList<Tarea> tareas = new LinkedList<>();
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Taskk");
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Tarea tarea = new Tarea();
                tarea.setId(res.getInt("id_task"));
                tarea.setTitulo(res.getString("title"));
                tarea.setDescripcion(res.getString("description"));
                tarea.setPrioridad(res.getString("priority"));
                tarea.setEstatus(res.getString("status"));
                tarea.setFecha(res.getString("date"));
                tareas.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la excepción para depuración
            System.out.println("Error al obtener tareas: " + e.getMessage());
        } finally {
            conector.closeConnection(connection); // Cerrar la conexion en caso de exception
        }

       // System.out.println("Número de tareas recuperadas: " + tareas.size());

        return tareas;
    }


    public boolean modificarTarea(Tarea tarea){
        boolean result = false;
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();
        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE Task SET title = ?, description = ?, priority = ?, status = ?, date = ? " +
                            "WHERE id_task = ?"
            );
            stmt.setString(1, tarea.getTitulo());
            stmt.setString(2, tarea.getDescripcion());
            stmt.setString(3, tarea.getPrioridad());
            stmt.setString(4, tarea.getEstatus());
            stmt.setString(5, tarea.getFecha());
            stmt.setLong(6, tarea.getId());

            if (stmt.executeUpdate() == 1){
                result = true;
            }else{
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al modificar la tarea: " + e.getMessage());
        }
        return result;
    }

    public boolean marcarCompletado(Tarea tarea) {
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE Taskk SET status = ? WHERE id_task = ?");
            stmt.setString(1, tarea.getEstatus());
            stmt.setInt(2, tarea.getId());

            if (stmt.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la excepción para depuración
            System.out.println("No se puede marcar como completada la tarea: " + e.getMessage());
        } finally {
            conector.closeConnection(connection); // Cerrar la conexion en caso de exception
        }

        return false;
    }

    public boolean existeTareaConTitulo(String titulo) {
        MySqlConector conector = new MySqlConector();
        String sql = "SELECT *FROM taskk WHERE title = ?";
        try (
                Connection connection = conector.connect();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, titulo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Retorna true si hay al menos una tarea con el mismo título
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }

        return false; // En caso de error, retornar false
    }


    public LinkedList<Tarea> obtenerTareasProgramadas(String fecha){
        LinkedList<Tarea> tareasP = new LinkedList<>();
        MySqlConector conector = new MySqlConector();
        Connection connection = conector.connect();

        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM Taskk WHERE date = ?"
            );
            stmt.setString(1,fecha);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Tarea tarea = new Tarea();
                tarea.setId(res.getInt("id_task"));
                tarea.setTitulo(res.getString("title"));
                tarea.setDescripcion(res.getString("description"));
                tarea.setPrioridad(res.getString("priority"));
                tarea.setEstatus(res.getString("status"));
                tarea.setFecha(res.getString("date"));
                tareasP.add(tarea);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tareasP;
    }

    public boolean update(int id, Object object) {
        return false;
    }

    public Object findOne(int id) {
        return null;
    }


}
