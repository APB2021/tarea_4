package tarea_4;

import java.io.File;

public class Main {

	/*
	 * TAREA 4 - OCTUBRE - 2024
	 * 
	 * Realiza un programa Java que lea los datos de 5 alumnos y los guarde campo a
	 * campo en un fichero binario cuyo nombre (y dirección) se solicitará por
	 * teclado. Cada alumno leído deberá ser guardado antes de solicitar el
	 * siguiente. Para cada alumno se tiene la siguiente información: NIA (entero),
	 * Nombre (String), Apellidos (String), Genero (Char), Fecha de Nacimiento
	 * (Fecha), Ciclo (String), Curso (String), Grupo (String).
	 */

	public static void main(String[] args) {
		Alumno alumno = new Alumno();
		File ficheroBinarioUsuario = alumno.creaFicheroBinario();
		alumno.guarda_Alumno_Campo_a_Campo_en_Fichero_Binario(ficheroBinarioUsuario);
	}
}