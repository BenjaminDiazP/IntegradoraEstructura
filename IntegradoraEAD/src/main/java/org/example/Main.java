package org.example;

import org.example.model.TareasDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    TareasDao tareasDao = new TareasDao();

    //Creacion de la Lista Linkedist
    Stack<Tarea> tareasP = new Stack<>(); //Tareas
    Stack<Tarea> tareasC = new Stack<>(); //Tareas completadas
    PriorityQueue<Tarea> tareasProgramadas = new PriorityQueue<>(Comparator.comparing(Tarea::getPrioridad)); //Tareas programadas

    //Variables globales
    int cont = 0;

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
                System.out.println("3. Mis tareas");
                System.out.println("4. Salir");
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
                                System.out.println("AGREGAR TAREA"); // Listo
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
                            System.out.println("1. Agregar tareas"); // Vamos  agreagar las atreas a una pila esa pila va mantenerlas hasta que se meta a la opcion guardar
                            System.out.println("2. Guardar Tareas"); //Guardar tareas en la base
                            System.out.println("3. Tareas Pendientes"); //mientras no las mande a guardar debe de decirle un aviso que las debe de guardar // va a mostrar las tareas de ese dia Pila
                            System.out.println("4. Tareas Programadas"); //mientras no las mande a guardar debe de decirle un aviso que las debe de guardar //Cola
                            System.out.println("5. Tareas Completadas");
                            System.out.println("6. Salir");
                            opc3 = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Ingresa un número entero válido.");
                            sc.nextLine();
                            opc3 = 0;
                        }
                        switch (opc3) {
                            case 1:
                                System.out.println("-----------------");
                                System.out.println("AGREGAR TAREAS PENDIENTES");
                                System.out.println("-----------------");
                                sc.nextLine();
                                agregarTareasPendientes();
                                break;
                            case 2:
                                System.out.println("-----------------");
                                System.out.println("GUARDAR TAREAS PENDIENTES");
                                System.out.println("-----------------");
                                agregarTareasP();
                                break;
                            case 3:
                                System.out.println("-----------------");
                                System.out.println("TAREAS PENDIENTES"); // Nos falta hacer que las tareas dependiendo de su prioridad se pongan primerp
                                System.out.println("-----------------");
                                completarTareasPendientes();
                                break;
                            case 4:
                                System.out.println("-----------------");
                                System.out.println("TAREAS PROGRAMADAS");
                                System.out.println("-----------------");
                                tareasProgramadas();
                                break;
                            case 5:
                                System.out.println("-----------------");
                                System.out.println("TAREAS COMPLETADAS");
                                System.out.println("-----------------");
                                mostrarTareasCompletadas();
                                break;
                            case 6:
                                System.out.println("Saliendo de la gestion");
                                System.out.println("-----------------");
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                System.out.println("-----------------");
                        }
                    } while (opc3 != 7);
                    break;
                case 3:
                    System.out.println("-----------------");
                    System.out.println("MIS TAREAS");
                    misTareas();
                    System.out.println("-----------------");
                    break;
                case 4:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Inténtelo de nuevo.");
            }
        } while (opcion != 4);
    }

    private void agregarTareas() {
        sc.nextLine();
        String titulo = validarTitulo();

        String descripcion = validarDescripcion();

        String prioridad = validarPrioridad();

        String fecha = obtenerFecha();

        String estatus = "Pendiente";

        String resultado = insertar(titulo, descripcion, prioridad, estatus, fecha);
        System.out.println(resultado);
    }

    //Validar Prioridad
    public String validarPrioridad() {
        String prioridad;
        do {
            System.out.println("Prioridad (Alta, Media ó Baja): ");
            prioridad = sc.nextLine().toLowerCase();
        } while (!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja"));
        return prioridad;
    }

    // Obtener la fecha para despues ser validada
    public String obtenerFecha() {
        String fecha;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        do {
            System.out.println("Fecha (yyyy-MM-dd): ");
            fecha = sc.nextLine();
        } while (!esFechaValida(fecha, dateFormat));
        return fecha;
    }

    //Validar el formato de fecha
    public static boolean esFechaValida(String fecha, SimpleDateFormat dateFormat) {
        try {
            Date date = dateFormat.parse(fecha);

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
        } catch (ParseException e) {
            System.out.println("Error: Ingresa una fecha válida.");
            return false;
        }
    }

    public String validarTitulo() {
        TareasDao nuevaLista = new TareasDao();
        LinkedList<Tarea> listaTitulo = nuevaLista.obtenerTareas();
        String titulo;
        do {
            System.out.println("Titulo: ");
            titulo = sc.nextLine();

            if (titulo.isEmpty()) {
                System.out.println("El titulo no puede estar vacio");
                continue;
            }
            boolean tituloValido = false;

            for (Tarea tarea : listaTitulo) {
                if (titulo.equals(tarea.getTitulo())) {
                    tituloValido = true;
                    System.out.println("Titulo ya registrado. Ingresa uno nuevo");
                    break;
                }
            }

            if (!tituloValido) {
                break;
            }

        } while (true);
        return titulo;
    }

    public String validarDescripcion() {
        TareasDao nuevaLista = new TareasDao();
        LinkedList<Tarea> listaDescripcion = nuevaLista.obtenerTareas();

        String descripcion;

        do {
            System.out.println("Descripcion: ");
            descripcion = sc.nextLine();

            if (descripcion.isEmpty()) {
                System.out.println("La descripcion no puede estar vacia");
                continue;
            }
            boolean descripcionValida = false;

            for (Tarea tarea : listaDescripcion) {
                if (descripcion.equals(tarea.getDescripcion())) {
                    descripcionValida = true;
                    System.out.println("Descripcion ya registrada. Ingresa una nueva");
                    break;
                }
            }

            if (!descripcionValida) {
                break;
            }

        } while (true);
        return descripcion;
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
        System.out.println("Ingresa el índice de la tarea a editar:");

        try {
            int indice = sc.nextInt();
            sc.nextLine();
            if (indice >= 1 && indice <= listaEditar.size()) {
                Tarea tareaAEditar = listaEditar.get(indice - 1);

                String nuevoTitulo;
                String nuevaDescripcion;
                String nuevaFecha;

                System.out.println("Título anterior: " + tareaAEditar.getTitulo());
                nuevoTitulo = validarTitulo();
                tareaAEditar.setTitulo(nuevoTitulo);

                System.out.println("Descripción anterior: " + tareaAEditar.getDescripcion());
                nuevaDescripcion = validarDescripcion();
                tareaAEditar.setDescripcion(nuevaDescripcion);

                System.out.println("Fecha anterior: " + tareaAEditar.getFecha());
                nuevaFecha = obtenerFecha();
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
                System.out.println("Índice inválido. No se puede modificar la tarea");
                System.out.println("-----------------");
            }
        } catch (NumberFormatException e) {
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

//   **************************    Metodos para las tareas pendientes    **************************

    public String validarTituloP() {
        TareasDao nuevaLista = new TareasDao();
        LinkedList<Tarea> listaTitulo = nuevaLista.obtenerTareasP();
        String titulo;
        do {
            System.out.println("Titulo: ");
            titulo = sc.nextLine();

            if (titulo.isEmpty()) {
                System.out.println("El titulo no puede estar vacio");
                continue;
            }
            boolean tituloValido = false;

            for (Tarea tarea : listaTitulo) {
                if (titulo.equals(tarea.getTitulo())) {
                    tituloValido = true;
                    System.out.println("Titulo ya registrado. Ingresa uno nuevo");
                    break;
                }
            }

            if (!tituloValido) {
                break;
            }

        } while (true);
        return titulo;
    }

    public String validarDescripcionP() {
        TareasDao nuevaLista = new TareasDao();
        LinkedList<Tarea> listaDescripcion = nuevaLista.obtenerTareasP();

        String descripcion;

        do {
            System.out.println("Descripcion: ");
            descripcion = sc.nextLine();

            if (descripcion.isEmpty()) {
                System.out.println("La descripcion no puede estar vacia");
                continue;
            }
            boolean descripcionValida = false;

            for (Tarea tarea : listaDescripcion) {
                if (descripcion.equals(tarea.getDescripcion())) {
                    descripcionValida = true;
                    System.out.println("Descripcion ya registrada. Ingresa una nueva");
                    break;
                }
            }

            if (!descripcionValida) {
                break;
            }

        } while (true);
        return descripcion;
    }

    private void agregarTareasPendientes() {
        System.out.println(tareasP);
        String titulo = validarTituloP();
        String descripcion = validarDescripcionP();
        String prioridad = validarPrioridad();
        String fecha = obtenerFecha();
        String estatus = "Pendiente";
        String resultado = insertarPendientes(titulo, descripcion, prioridad, estatus, fecha);
        System.out.println(resultado);
    }

    // Método insertar tareas pendientes
    public String insertarPendientes(String titulo, String descripcion, String prioridad, String estatus, String fecha) {
        Tarea nuevaTarea = new Tarea(titulo, descripcion, prioridad, estatus, fecha);
        tareasP.push(nuevaTarea);
        System.out.println(tareasP);
        return "-----------------<\nTarea agregada correctamente\n-----------------";
    }

    public void agregarTareasP() {
        cont = 0;
        TareasDao dao = new TareasDao();

        if (tareasP.isEmpty()) {
            System.out.println("-----------------");
            System.out.println("No hay tareas pendientes para agregar a la base de dato primer if");
            System.out.println("-----------------");
            return;
        }
        System.out.println(tareasP);
        for (Tarea tarea : tareasP) {
            if (tarea.getEstatus().equals("Pendiente") && !dao.existeTareaConTitulo(tarea.getTitulo())) {
                dao.insertarTareaP(tarea);
                cont = cont + 1;
            }
        }
        if (cont == 0) {
            System.out.println("-----------------");
            System.out.println("No hay tareas pendientes para agregar a la base de dato segundo if");
            System.out.println("-----------------");
            return;
        } else if (cont >= 1) {
            System.out.println("-----------------");
            System.out.println("Tarea pendiente agregada correctamente");
            System.out.println("-----------------");
            return;
        }
    }

    public void completarTareasPendientes() {
        LinkedList<Tarea> listaP = tareasDao.obtenerTareasP();

        if (listaP.isEmpty()) {
            System.out.println("-----------------");
            System.out.println("No hay tareas pendientes para mostrar");
            System.out.println("-----------------");
            return;
        }

        //infla la tareas p si entro primero a agregar tarea y despues al 3
        // Filtrar las tareas pendientes que ya están en la base de datos
        for (Tarea tarea : listaP) {
            if (tarea.getEstatus().equals("Pendiente") && !tareasP.contains(tarea)) {
                tareasP.push(tarea);
            }
        }

        if (tareasP.isEmpty()) {
            System.out.println("-----------------");
            System.out.println("No hay tareas pendientes para mostrar");
            System.out.println("-----------------");
            return;
        }

        for (Tarea tarea : tareasP) {
            boolean tareaEnLista = false;
            for (Tarea tareaLista : listaP) {
                if (tarea.getTitulo().equals(tareaLista.getTitulo())) {
                    tareaEnLista = true;
                    break;
                }
            }
            if (!tareaEnLista) {
                System.out.println("Agrega tareas a la base de datos.");
                return;
            }
        }
        // me  quede aqui
        int opc = 1;
        do {
            Tarea tareaEnLaCima = tareasP.peek();
            if (tareaEnLaCima != null && tareaEnLaCima.getEstatus().equals("Pendiente")) {
                System.out.println("Tarea a completar: " + "\n" + tareasP.peek());
                sc.nextLine();
                System.out.print("¿Quieres marcar como completada? (S/N): ");
                String respuesta = sc.nextLine();

                if (respuesta.equalsIgnoreCase("S")) {
                    if (!tareasP.isEmpty()) {
                        Tarea t = tareasP.pop();
                        t.setEstatus("Completado");
                        tareasDao.marcarCompletado(t);

                        // Actualizar la lista de tareas pendientes después de marcar una tarea como completada
                        LinkedList<Tarea> nuevasTareasP = new LinkedList<>(tareasDao.obtenerTareasP());
                        tareasP.clear();  // Limpiar la pila actual
                        for (Tarea tarea : nuevasTareasP) {
                            tareasP.push(tarea);
                            opc = 0;
                        }

                        System.out.println("-----------------");
                        System.out.println("Tarea marcada como completada");
                        System.out.println("-----------------");
                    } else {
                        System.out.println("-----------------");
                        System.out.println("La pila de tareas está vacía");
                        System.out.println("-----------------");
                    }
                } else if (respuesta.equalsIgnoreCase("N")) {
                    System.out.println("-----------------");
                    System.out.println("No se completó la tarea");
                    System.out.println("-----------------");
                    opc = 0;
                } else {
                    System.out.println("Ingresa una respuesta válida");
                }
            } else if (tareaEnLaCima != null && tareaEnLaCima.getEstatus().equals("Completado")) {
                System.out.println("-----------------");
                System.out.println("No hay tareas pendientes");
                opc = 0; // Actualiza opc para salir del bucle
            }
        } while (opc == 1);
    }

    public void mostrarTareasCompletadas() {
        LinkedList<Tarea> listaC = tareasDao.obtenerTareasP();  // Obtener todas las tareas de la base de datos

        boolean hayTareasNuevas = false;

        for (Tarea tarea : listaC) {
            if (!contieneTareaConId(tareasC, tarea.getId()) && tarea.getEstatus().equals("Completado")) {
                tareasC.push(tarea);
                hayTareasNuevas = true;
            }
        }

        if (hayTareasNuevas) {
            while (!tareasC.isEmpty()) {
                Tarea tarea = tareasC.pop();
                System.out.println(tarea.toString());
                System.out.println("-----------------");
            }
        } else {
            System.out.println("No hay tareas completadas");
        }
    }

    private boolean contieneTareaConId(Stack<Tarea> stack, int id) {
        for (Tarea tarea : stack) {
            if (tarea.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void tareasProgramadas() {
        sc.nextLine();
        TareasDao dao = new TareasDao();

        // 1.- Validar la fecha
        System.out.println("Ingresa fecha para ver las tareas programadas para ese día");
        String fecha = obtenerFecha();

        // 2.- Obtener tareas programadas para esa fecha
        LinkedList<Tarea> tareasO = dao.obtenerTareasProgramadas(fecha);

        if (tareasO.isEmpty()) {
            System.out.println("-----------------");
            System.out.println("No hay tareas programadas para esa fecha");
            System.out.println("-----------------");
            return;
        }

        // 2.1 Meter todas las tareas obtenidas a la PriorityQueue
        for (Tarea tarea : tareasO) {
            if (tarea.getEstatus().equals("Pendiente")) {
                tareasProgramadas.add(tarea);
            }
        }

        if (tareasProgramadas.isEmpty()) {
            System.out.println("-----------------");
            System.out.println("No hay tareas pendientes programadas para esa fecha");
            System.out.println("-----------------");
            return;
        }

        // 3.- Extraer las tareas de la PriorityQueue a una lista
        List<Tarea> listaTareasOrdenadas = new ArrayList<>(tareasProgramadas);

        // 4.- Mostrar las tareas en orden de prioridad
        listaTareasOrdenadas.sort(Comparator.comparing(this::prioridadStringToInteger).reversed());

        for (Tarea tarea : listaTareasOrdenadas) {
            System.out.println(tarea);
            System.out.println("--------------------");
        }
    }

    private int prioridadStringToInteger(Tarea tarea) {
        // Igualar las prioridades "alta", "media", "baja" a valores numéricos
        switch (tarea.getPrioridad().toLowerCase()) {
            case "alta":
                return 3;
            case "media":
                return 2;
            case "baja":
                return 1;
            default:
                return 0; // Manejar el caso por defecto si la prioridad no es reconocida
        }
    }

    public void misTareas() {
        TreeSet<Tarea> tareas = tareasDao.treeTareas();
        System.out.println("¿Cómo deseas ver tus tareas?\n1. Por fecha\n2. Por prioridad\n3. Por título");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                mostrarTareasPorFecha(tareas);
                break;
            case 2:
                mostrarTareasPorPrioridad(tareas);
                break;
            case 3:
                mostrarTareasPorTitulo(tareas);
                break;
            default:
                System.out.println("Opción no válida.");
        }

    }

    private void mostrarTareasPorPrioridad(TreeSet<Tarea> tareas) {
        TreeSet<Tarea> tareasPorPrioridad = new TreeSet<>();
        tareasPorPrioridad.addAll(tareas);
        if (tareasPorPrioridad.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
            return;
        } else {
            System.out.println("Tareas ordenadas por prioridad:");
            for (Tarea tarea : tareasPorPrioridad) {

                System.out.println(tarea);
            }
        }

    }

    private void mostrarTareasPorTitulo(TreeSet<Tarea> tareas) {
        TreeSet<Tarea> tareasPorTitulo = new TreeSet<>(Comparator.comparing(Tarea::getTitulo));
        tareasPorTitulo.addAll(tareas);
        if (tareasPorTitulo.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
            return;
        } else {
            System.out.println("Tareas ordenadas por título:");
            for (Tarea tarea : tareasPorTitulo) {
                System.out.println(tarea);
            }
        }
    }

    private void mostrarTareasPorFecha(TreeSet<Tarea> tareas) {
        TreeSet<Tarea> tareasPorFechas = new TreeSet<>(Comparator.comparing(Tarea::getFecha)
                .thenComparing(Tarea::getTitulo));
        tareasPorFechas.addAll(tareas);
        if (tareasPorFechas.isEmpty()) {
            System.out.println("No hay tareas para mostrar.");
            return;
        } else {
            System.out.println("Tareas ordenadas por fecha:");
            for (Tarea tarea : tareasPorFechas) {
                System.out.println(tarea);
            }
        }
    }

}