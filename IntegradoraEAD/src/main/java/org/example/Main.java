package org.example;

import org.example.model.TareasDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    TareasDao tareasDao = new TareasDao();

    //Creacion de la Lista Linkedist
    LinkedList<Tarea> lista;
    Stack<Tarea> tareasP; //Tareas
    Stack<Tarea> tareasC; //Tareas completadas
    Queue<Tarea> tareasProgramadas; //Tareas programadas

    //Creacion De Colas y Pilas

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        main.gestionTareas();
    }

    public void gestionTareas() {
        int opcion;
        do {
            try {
                //Preguntar que cuando agregamos una tarea a donde se va a pendientes o programadas
                //programas un
                System.out.println("------ Menú------");
                System.out.println("Ingresa la opcion que necesites");
                System.out.println("1. Menu: Gestionar tareas");
                System.out.println("2. Menu: Tareas pendientes/programadas");
                System.out.println("3. Salir");
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                opcion = 0;
            }
            switch (opcion) {
                case 1:
                    int opc2;
                    do {
                        try {
                            System.out.println("------ Menú Gestion de tareas------");
                            System.out.println("Ingresa la opcion que prefieras");
                            System.out.println("1. Ver todas las tareas");
                            System.out.println("2. Agregar tarea");
                            System.out.println("3. Editar tarea");// hacer validacion
                            System.out.println("4. Eliminar tarea"); // hacer validacion
                            System.out.println("5. Salir");
                            opc2 = sc.nextInt();
                        } catch (InputMismatchException e) {
                            sc.nextLine();
                            opc2 = 0;
                        }
                        switch (opc2) {
                            case 1:
                                System.out.println("-----------------");
                                System.out.println("VER TAREAS"); // Listo
                                System.out.println("-----------------");
                                mostrarTareas();
                                break;
                            case 2:
                                System.out.println("-----------------");
                                System.out.println("AGREGAR TAREA DEL DIA"); // Listo
                                System.out.println("-----------------");
                                agregarTareas();
                                break;
                            case 3:
                                System.out.println("-----------------");
                                System.out.println("EDITAR TAREAS");
                                System.out.println("-----------------");
                                editarTarea();
                                break;
                            case 4:
                                System.out.println("-----------------");
                                System.out.println("ELIMINAR TAREAS");
                                System.out.println("-----------------");
                                EliminarTarea();
                                break;
                            case 5:
                                System.out.println("Saliendo de la gestión");
                                System.out.println("-----------------");
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                System.out.println("-----------------");
                        }
                    } while (opc2 != 5);
                    break;
                case 2:
                    int opc3;
                    do {
                        try {
                            System.out.println("------ Menú Tareas pendientes/programadas-----");
                            System.out.println("Ingresa la opcion que prefieras");
                            System.out.println("1. Tareas Pendientes");//pila
                            System.out.println("2. Tareas Completadas");
                            System.out.println("3. Tareas Programadas");//colas
                            System.out.println("4. Mostrar Tareas Programadas");
                            System.out.println("5. Jerarquía de Prioridades");
                            System.out.println("6. salir");
                            opc3 = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingresa un número entero válido.");
                            sc.nextLine();
                            opc3 = 0;
                        }
                        switch (opc3) {

                            case 1:
                                System.out.println("-----------------");
                                System.out.println("TAREAS PENDIENTES");
                                System.out.println("-----------------");
                                completarTareasPendientes();
                                break;
                            case 2:
                                System.out.println("-----------------");
                                System.out.println("TAREAS COMPLETADAS");
                                System.out.println("-----------------");
                                mostrarTareasCompletadas();
                                break;
                            case 3:
                                System.out.println("-----------------");
                                System.out.println("TAREAS PROGRAMADAS");
                                System.out.println("-----------------");
                                break;
                            case 4:
                                System.out.println("-----------------");
                                System.out.println("MOSTRAR TAREAS PROGRAMAR ");
                                System.out.println("-----------------");
                                break;
                            case 5:
                                // Saber si sera solo de las tareas programdas y de las tareas del dia
                                System.out.println("-----------------");
                                System.out.println("CAMBIAR PRIORIDAD DE TAREAS ");
                                System.out.println("-----------------");
                                break;
                            case 6:
                                System.out.println("Saliendo de la gestion");
                                System.out.println("-----------------");
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                System.out.println("-----------------");
                        }
                    } while (opc3 != 6);
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Inténtelo de nuevo.");
            }
        } while (opcion != 3);
    }

    private void agregarTareas() {

        sc.nextLine();
        System.out.println("Título: ");
        String titulo = sc.nextLine();

        System.out.println("Descripción: ");
        String descripcion = sc.nextLine();

        String prioridad = validarPrioridad();

        String fecha = obtenerFecha();

        String estatus = "Pendiente";

        String resultado = insertar(titulo, descripcion, prioridad, estatus, fecha);
        System.out.println(resultado);
    }

    //Validar Prioridad
    public String validarPrioridad() {
        String prioridad;
        do{
            System.out.println("Prioridad (Alta, Media ó Baja): ");
            prioridad = sc.nextLine().toLowerCase();
        }while(!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja"));
        return prioridad;
    }

    // Obtener la fecha para despues ser validada
    public String obtenerFecha() {
        String fecha;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        do {
            System.out.println("Fecha (yyyy-MM-dd): ");
            fecha = sc.nextLine();
        } while (!esFechaValida(fecha, dateFormat) || !esFechaValida2(fecha));
        return fecha;
    }

    //Validar el formato de fecha
    public static boolean esFechaValida(String fecha, SimpleDateFormat dateFormat) {
        try {
            Date date = dateFormat.parse(fecha);
            return true;
        } catch (ParseException e) {
            System.out.println("Error: Ingresa una fecha válida.");
            return false;
        }
    }

    //Validar cada campo de la fecha
    public static boolean esFechaValida2(String fecha) {
        String[] fechaPartes = fecha.split("-");
        try {
            int anio = Integer.parseInt(fechaPartes[0]);
            int mes = Integer.parseInt(fechaPartes[1]);
            int dia = Integer.parseInt(fechaPartes[2]);

            if (anio < 2023) {
                System.out.println("No puedes ingresar un año menor a 2023");
                return false;
            } else if (mes < 1 || mes > 12) {
                System.out.println("No puedes ingresar un mes menor a 1 o mayor a 12");
                return false;
            } else if (dia < 1 || dia > 31) {
                System.out.println("No puedes ingresar un día menor a 1 o mayor a 31");
                return false;
            } else {
                System.out.println("------------------");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Formato de fecha incorrecto. Ingresa la fecha en formato yyyy-MM-dd");
            return false;
        }
        return true;
    }


    // Método insertar
    public String insertar(String titulo, String descripcion, String prioridad, String estatus, String fecha) {
        Tarea nuevaTarea = new Tarea(titulo, descripcion, prioridad, estatus, fecha);
        tareasDao.insertarTarea(nuevaTarea);
        return "-----------------<\nTarea agregada correctamente\n-----------------";
    }

    public void mostrarTareas() {
        TareasDao dao = new TareasDao();
        LinkedList<Tarea> listaMostrar = dao.obtenerTareas();

        try {
            if (listaMostrar.isEmpty()) {
                System.out.println("No hay tareas para mostrar.");
                return;
            }

            for (Tarea tarea : listaMostrar) {
                System.out.println("No. " + (listaMostrar.indexOf(tarea) + 1));
                System.out.println(tarea.toString());
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar tareas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void editarTarea() {
        LinkedList<Tarea> listaEditar = tareasDao.obtenerTareas();
        mostrarTareas();
        System.out.println("Ingresa el indice de la tarea a editar:");
        try {
            int indice = sc.nextInt();

            if (indice >= 1 && indice <= listaEditar.size()) {
                Tarea tareaAEditar = listaEditar.get(indice - 1);
                sc.nextLine();

                System.out.println("Título anterior: " + tareaAEditar.getTitulo());
                System.out.print("Nuevo titulo: ");
                String nuevoTitulo = sc.nextLine();
                tareaAEditar.setTitulo(nuevoTitulo);

                System.out.println("Descripción anterior: " + tareaAEditar.getDescripcion());
                System.out.print("Nueva descripción: ");
                String nuevaDescripcion = sc.nextLine();
                tareaAEditar.setDescripcion(nuevaDescripcion);

                System.out.println("Prioridad anterior: " + tareaAEditar.getPrioridad());
                System.out.print("Nueva prioridad (Alta, Media ó Baja): ");
                String nuevaPrioridad = sc.nextLine();
                tareaAEditar.setPrioridad(nuevaPrioridad);

                System.out.println("Fecha anterior: " + tareaAEditar.getFecha());
                System.out.print("Nueva fecha (yyyy-mm-dd): ");
                String nuevaFecha = sc.nextLine();
                tareaAEditar.setFecha(nuevaFecha);

                tareaAEditar.setEstatus("Pendiente");

                if (tareasDao.modificarTarea(tareaAEditar)) {
                    listaEditar.set(indice - 1, tareaAEditar);

                    System.out.println("Tarea modificada correctamente");

                } else {
                    System.out.println("Error al modificar la tarea en la base de datos");
                }

            } else {
                System.out.println("-----------------");
                System.out.println("Indice invalido. No se puede  modificar la tarea");
                System.out.println("-----------------");
            }
        } catch (Exception e) {
            System.out.println("Error al leer la entrada. Asegúrate de ingresar un número.");
        }
    }

    public void EliminarTarea() {
        LinkedList<Tarea> listaEliminar = tareasDao.obtenerTareas();
        mostrarTareas();

        System.out.println("Ingresa el numero de la tarea que quieres eliminar");
        try {
            int opc = sc.nextInt();

            if (opc >= 1 && opc <= listaEliminar.size()) {
                Tarea tareaEliminar = listaEliminar.get(opc - 1);
                int idTarea = tareaEliminar.getId();

                if (tareasDao.eliminarTarea(idTarea)) {
                    listaEliminar.remove(tareaEliminar);

                    System.out.println("Tarea eliminada correctamente");

                } else {
                    System.out.println("Error al eliminar la tarea de la base de datos");
                }
            } else {
                System.out.println("Indice invalido. No se puede eliminar la tarea");
            }
        } catch (Exception e) {
            System.out.println("Error al leer la entrada. Asegúrate de ingresar un número.");
        }
    }


    public void completarTareasPendientes() {  //Error aqui cuando entrar se salta la opcion para completar la tarea
        LinkedList<Tarea> listaP = tareasDao.obtenerTareas();

        tareasP = new Stack<>();

        for (Tarea lista : listaP) {
            if (lista.getEstatus().equals("Pendiente") && !listaP.isEmpty()) {
                tareasP.push(lista);
            }

        }

        System.out.println(tareasP.peek());
        System.out.print("¿Quieres marcar como completada? (S/N): ");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {
            Tarea t = tareasP.pop();
            t.setEstatus("Completado");

            if (tareasDao.marcarCompletado(t)) {
                tareasC.add(t);
            }

        } else if (respuesta.equalsIgnoreCase("N")) {

            System.out.println("No se completo la tarea");

        } else {
            System.out.println("Ingresa una respuesta valida");
        }
    }

    public void mostrarTareasCompletadas() {

        LinkedList<Tarea> listaC = tareasDao.obtenerTareas();

        tareasC = new Stack<>();

        for (Tarea lista : listaC) {
            if (lista.getEstatus().equals("Completado") && !listaC.isEmpty()) {
                tareasC.add(lista);
            }
        }

        if (tareasC.isEmpty()) {
            System.out.println("No hay tareas completadas");
        } else {
            for (Tarea tarea : tareasC) {
                System.out.println(tarea.toString());
            }
        }
    }

}