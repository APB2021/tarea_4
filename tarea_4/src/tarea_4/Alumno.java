package tarea_4;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Alumno implements Serializable {

	private static final long serialVersionUID = 5101083615448063518L;

	// Constante para indicar el número de alumnos
	private static final int NUMERO_DE_ALUMNOS = 5;

	// Scanner declarado como static para no tener que cerrarlo:
	private static Scanner sc = new Scanner(System.in);

	// Enumerado para elegir el criterio de ordenación:
	private enum Criterio {
		NIA, NOMBRE, APELLIDOS, GENERO, FECHA_NACIMIENTO, CICLO, CURSO, GRUPO
	};

	// Atributos privados de la clase Alumno
	private int nia = 0;
	private String nombre;
	private String apellidos;
	private char genero = 'S';
	private Date fechaNacimiento;
	private String ciclo;
	private String curso;
	private String grupo;

	// Constructores de la clase Alumno
	public Alumno() {
	}

	public Alumno(int nia, String nombre, String apellidos, char genero, Date fechaNacimiento, String ciclo,
			String curso, String grupo) {

		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
	}

	// Getters & Setters:
	public int getNia() {
		return nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "NIA: " + nia + " --> " + nombre + ", " + apellidos + " - Género: " + genero + " - Fecha de Nacimiento: "
				+ fechaNacimiento + " - Ciclo: " + ciclo + " - Curso: " + curso + " Grupo: " + grupo;
	}

	// Método para leer NUMERO_DE_ALUMNOS:

	public void leerAlumnos() {

		// Limpiar el buffer:
		// sc.nextLine();

		for (int i = 0; i < NUMERO_DE_ALUMNOS; i++) {
			System.out.print("Introduzca el NOMBRE del alumno " + (i + 1) + ": ");
			nombre = sc.nextLine().toUpperCase();
			System.out.print("Introduzca los APELLIDOS del alumno: ");
			apellidos = sc.nextLine().toUpperCase();

			// Convertimos el char en String para poder manejarlo:
			String generoString = Character.toString(genero);

			while (!generoString.equalsIgnoreCase("H") && !generoString.equalsIgnoreCase("M")) {
				System.out.print("Introduzca el GÉNERO del alumno(H/M): ");
				genero = sc.nextLine().toUpperCase().charAt(0);
				generoString = Character.toString(genero);
			}

			/////////////// FECHA DE NACIMIENTO//////////////////////////////
			System.out.print("Introduzca la FECHA DE NACIMIENTO del alumno en formato (dd/MM/yyyy): ");
			String fechaNacimientoString = sc.nextLine();

			// Definir formato de fecha según el String:
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			try {
				// Convertir el String en Date
				fechaNacimiento = formato.parse(fechaNacimientoString);
				// System.out.println("Fecha convertida: " + fecha);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/////////////// FIN FECHA DE NACIMIENTO//////////////////////////////
			System.out.print("Introduzca el CICLO del alumno: ");
			ciclo = sc.nextLine().toUpperCase();
			System.out.print("Introduzca el CURSO del alumno: ");
			curso = sc.nextLine();
			System.out.print("Introduzca el GRUPO del alumno: ");
			grupo = sc.nextLine().toUpperCase();

			// Vamos añadiendo el nia secuencialmente incrementándolo en 1 unidad:
			nia = nia + 1;

			// Añadimos el alumno al ArrayList de alumnos:
			// alumnos.add(new Alumno(nia, nombre, apellidos, genero, fechaNacimiento,
			// ciclo, curso, grupo));
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("Se han leído los datos de " + NUMERO_DE_ALUMNOS + " alumnos.");
	}

	// MÉTODO para crear un fichero BINARIO solicitado por teclado

	public File creaFicheroBinario() {

		String nombreFicheroBinario, ruta = "";
		File ficheroBinario = null;

		while (ficheroBinario == null) {
			System.out.print("Introduzca el NOMBRE del fichero BINARIO donde desee almacenar " + NUMERO_DE_ALUMNOS
					+ " alumnos: ");
			nombreFicheroBinario = sc.nextLine();

			System.out.println("Introduzca la RUTA del fichero BINARIO llamado: " + nombreFicheroBinario
					+ " donde desee almacenar " + NUMERO_DE_ALUMNOS + " alumnos.");
			System.out.print("(Por ejemplo: \\src\\tarea_4\\): ");
			sc.next();
			ruta = sc.nextLine();

			ficheroBinario = new File(nombreFicheroBinario, ruta);

			if (!ficheroBinario.exists()) {
				try {
					ficheroBinario.createNewFile();
					System.out.println(
							"Fichero binario llamado - " + ficheroBinario.getName() + " - creado correctamente");
					System.out.println("----------------------------------------------------------------------");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

				System.out.println("El fichero: " + ficheroBinario.getName() + " ya existe en la ruta: "
						+ ficheroBinario.getAbsolutePath());

				ficheroBinario = null;

				System.out.println("-----------------------------------------------------------");
				System.out.println("Por favor, introduzca un fichero que no exista previamente.");
				System.out.println("-----------------------------------------------------------");
			}
		}
		return ficheroBinario;
	}

	// MÉTODO para leer 1 alumno y guardarlo CAMPO a CAMPO en un fichero BINARIO
	// solicitado por teclado

	public void guarda_Alumno_Campo_a_Campo_en_Fichero_Binario(File ficheroBinario) {

		DataOutputStream dos = null;

		for (int i = 0; i < NUMERO_DE_ALUMNOS; i++) {

			try {
				dos = new DataOutputStream(new FileOutputStream(ficheroBinario, true));

				// NIA - int
				// Vamos añadiendo el nia secuencialmente incrementándolo en 1 unidad:
				nia = nia + 1;
				dos.writeInt(nia);

				// NOMBRE - String
				System.out.print("Introduzca el NOMBRE del alumno " + (i + 1) + ": ");
				nombre = sc.nextLine();
				dos.writeUTF(nombre);

				// APELLIDOS - String
				System.out.print("Introduzca los APELLIDOS del alumno " + (i + 1) + ": ");
				apellidos = sc.nextLine();
				dos.writeUTF(apellidos);

				// GÉNERO - char
				// Convertimos el char en String para poder manejarlo:
				String generoString = Character.toString(genero);

				while (!generoString.equalsIgnoreCase("H") && !generoString.equalsIgnoreCase("M")) {
					System.out.print("Introduzca el GÉNERO del alumno(H/M): ");

					genero = sc.nextLine().toUpperCase().charAt(0);
					generoString = Character.toString(genero);
					dos.writeUTF(generoString);
				}

				// Debo restablecer el género para cambiar la condición del bucle while
				genero = 'S';

				// FECHA DE NACIMIENTO - Date
				System.out.print("Introduzca la FECHA DE NACIMIENTO del alumno en formato (dd/MM/yyyy): ");
				String fechaNacimientoString = sc.nextLine();

				// Definir formato de fecha según el String:
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

				try {
					// Convertir el String en Date
					fechaNacimiento = formato.parse(fechaNacimientoString);
					// System.out.println("Fecha convertida: " + fecha);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// CICLO - String
				System.out.print("Introduzca el CICLO del alumno " + (i + 1) + ": ");
				ciclo = sc.nextLine().toUpperCase();
				dos.writeUTF(ciclo);

				// CURSO - String
				System.out.print("Introduzca el CURSO del alumno " + (i + 1) + ": ");
				curso = sc.nextLine().toUpperCase();
				dos.writeUTF(curso);

				// GRUPO - String
				System.out.print("Introduzca el GRUPO del alumno " + (i + 1) + ": ");
				grupo = sc.nextLine().toUpperCase();
				dos.writeUTF(grupo);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (dos != null) {
					try {
						dos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		System.out.println("------------------------------------------------------------");
		System.out.println("Se han leído los datos de " + NUMERO_DE_ALUMNOS + " alumnos.");
	}
}