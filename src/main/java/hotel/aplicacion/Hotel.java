/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package hotel.aplicacion;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Seguis
 */
public class Hotel {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Variables para almacenar los clientes y reservas (sin uso de arrays o
		// colecciones)
		Cliente cliente1 = null, cliente2 = null, cliente3 = null;
		Reserva reserva1 = null, reserva2 = null, reserva3 = null;
		int opcion = 0;

		do {
			System.out.println("\n--- Gestión de Reservas en un Hotel ---");
			System.out.println("1. Crear clientes");
			System.out.println("2. Crear reserva");
			System.out.println("3. Mostrar datos de reservas");
			System.out.println("4. Salir");
			System.out.print("Seleccione una opción: ");

			try {
				opcion = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Debe ingresar un número válido.");
				continue;
			}

			switch (opcion) {
			case 1:
				// Opción para crear clientes
				try {
					String nombre;
					do {
						System.out.println("\nIntroduzca los datos del cliente:");
						System.out.print("Nombre: ");
						nombre = sc.nextLine();
					} while(nombre.isEmpty());
					
					String dni=null;
					boolean dniValido;
					do {
						try {
							System.out.print("Introduzca DNI: ");
							dni = sc.nextLine();
							Utilidades.validarDNI(dni);
							dniValido=true;
						} catch (Exception e) {
							System.out.println(e.getMessage());
							dniValido=false;
						}
					} while (!dniValido);
					System.out.print("Teléfono: ");
					String telefono = sc.nextLine();
					// Se crea el primer cliente con datos ingresados por el usuario.
					cliente1 = new Cliente(nombre, dni, telefono);
					System.out.println("Cliente creado correctamente:\n" + cliente1.mostrarInformacion());
					
					// Creación de dos clientes adicionales con datos literales.
					cliente2 = new Cliente("Ana García", "12345678Z", "600111222");
					cliente3 = new Cliente("Luis Martínez", "87654321X", "600333444");
					System.out.println("Cliente creado correctamente:\n" + cliente2.mostrarInformacion());
					System.out.println("Cliente creado correctamente:\n" + cliente3.mostrarInformacion());
				} catch (Exception e) {
					System.out.println("Error al crear cliente: " + e.getMessage());
				}
				break;
			case 2:
				if (cliente1 == null || cliente2 == null || cliente3 == null) {
					System.out.println("Primero debe crear los clientes (opción 1).");
					break;
				}
				System.out.println("\nIntroduzca los datos para la reserva:");
				System.out.print("Número de cliente (1, 2 o 3): ");
				int numCliente = Integer.parseInt(sc.nextLine());
				Cliente clienteSeleccionado = null;
				if (numCliente == 1) {
					clienteSeleccionado = cliente1;
				} else if (numCliente == 2) {
					clienteSeleccionado = cliente2;
				} else if (numCliente == 3) {
					clienteSeleccionado = cliente3;
				} else {
					System.out.println("Número de cliente no válido.");
					break;
				}
				LocalDate fechaEntrada = null;
				boolean fechaEntradaValida = false;
				while (!fechaEntradaValida) {
					fechaEntrada = Utilidades.leerFecha("Introduzca la fecha de entrada");
					LocalDate hoy = LocalDate.now();
					if (fechaEntrada.isBefore(hoy)) {
						System.out.println(
								"La fecha de entrada no puede ser anterior a la fecha actual. Por favor, intente de nuevo.");
					} else {
						fechaEntradaValida = true;
					}
				}
				// Lectura y validación de la fecha de salida utilizando el método validarFechas
				LocalDate fechaSalida = null;
				boolean fechaSalidaValida = false;
				while (!fechaSalidaValida) {
					fechaSalida = Utilidades.leerFecha("Introduzca la fecha de salida");
					try {
						Utilidades.validarFechas(fechaEntrada, fechaSalida);
						fechaSalidaValida = true;
					} catch (Exception e) {
						System.out.println(e.getMessage() + " Por favor, intente de nuevo.");
					}
				}
				String tipoHabCadena = null;
				TipoHabitacion tipoHabitacion = null;
				boolean tipoValido = false;
				while (!tipoValido) {
					System.out.print("Tipo de habitación (DOBLE/SUITE): ");
					tipoHabCadena = sc.nextLine().toUpperCase();
					switch (tipoHabCadena) {
					case "DOBLE":
						tipoHabitacion = TipoHabitacion.DOBLE;
						tipoValido = true;
						break;
					case "SUITE":
						tipoHabitacion = TipoHabitacion.SUITE;
						tipoValido = true;
						break;
					default:
						System.out.println("Tipo de habitación no válido. Por favor, intente de nuevo.");
						break;
					}
				}
				boolean camaSupletoria = false;
				boolean respuestaValida = false;
				while (!respuestaValida) {
					System.out.print("¿Se solicita cama supletoria? (S/N): ");
					String camaInput = sc.nextLine().trim().toUpperCase();
					switch (camaInput) {
					case "S":
						camaSupletoria = true;
						respuestaValida = true;
						break;
					case "N":
						camaSupletoria = false;
						respuestaValida = true;
						break;
					default:
						System.out.println("Respuesta no válida. Por favor, ingrese S o N.");
						break;
					}
				}
				Reserva nuevaReserva = null;
				try {
					nuevaReserva = new Reserva(clienteSeleccionado, fechaEntrada, fechaSalida, tipoHabitacion,
							camaSupletoria);
				} catch (Exception e) {
					System.out.println("Error al crear reserva: " + e.getMessage());
				}
				if (reserva1 == null) {
					reserva1 = nuevaReserva;
				} else if (reserva2 == null) {
					reserva2 = nuevaReserva;
				} else if (reserva3 == null) {
					reserva3 = nuevaReserva;
				} else {
					System.out.println("Se ha alcanzado el máximo de reservas permitidas.");
					break;
				}
				System.out.println("Reserva creada correctamente:\n" + nuevaReserva.mostrarDetalles());
				break;

			case 3:
				// Opción para mostrar los datos de las reservas creadas
				System.out.println("\n=== Reservas creadas ===");
				if (reserva1 != null) {
					System.out.println(reserva1.mostrarDetalles());
					System.out.println("---------------------------");
				}
				if (reserva2 != null) {
					System.out.println(reserva2.mostrarDetalles());
					System.out.println("---------------------------");
				}
				if (reserva3 != null) {
					System.out.println(reserva3.mostrarDetalles());
					System.out.println("---------------------------");
				}
				if (reserva1 == null && reserva2 == null && reserva3 == null) {
					System.out.println("No hay reservas creadas.");
				}
				break;
			case 4:
				System.out.println("Saliendo del programa.");
				break;
			default:
				System.out.println("Opción no válida.");
				break;
			}
		} while (opcion != 4);

		sc.close();
	}

}
