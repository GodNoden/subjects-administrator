import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClaseAdministradora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opc = 0, id;
		ClaseAdministradora claseAdmnistradora = new ClaseAdministradora();
		Scanner scanner = new Scanner(System.in);
		//4.	Desde main se solicite registrar 4 tuplas
		claseAdmnistradora.registro("I7029", "SISTEMAS OPERATIVOS");
		claseAdmnistradora.registro("I7030", "SEMINARIO DE SISTEMAS OPERATIVOS");
		claseAdmnistradora.registro("I7033", "SISTEMAS OPERATIVOS DE RED");
		claseAdmnistradora.registro("I7034", "SEMINARIO DE SISTEMAS OPERATIVOS DE RED");
		
		//7.	En main elaborar un menú que permita el registro de una materia, eliminar una materia, obtener los datos de una materia, imprimir materias o salir.
		while(opc!=5) {
			//POSIBILIDAD DE PARTICIPACIÓN
			try {
				System.out.println("Administrador de Materias");
				System.out.println("1. ALTA DE UNA MATERIA");
				System.out.println("2. CONSULTA DE MATERIA");
				System.out.println("3. BAJA MATERIA");
				System.out.println("4. MOSTRAR TABLA");
				System.out.println("5. SALIR");
				
				opc = scanner.nextInt();
			
			switch (opc) {
			case 1:
				String clave, materia="";
				System.out.print("Ingrese clave: ");
				clave = scanner.next();
				System.out.print("Ingrese materia: ");
				scanner.nextLine();
				materia = scanner.nextLine();
				claseAdmnistradora.registro(clave, materia);
				break;
			case 2:
				try {
				System.out.print("Ingrse el Id de la materia que desea consultar: ");
				id = scanner.nextInt();
				Materia materia1 = claseAdmnistradora.consulta(id);
				System.out.println("Clave  |  Nombre materia");
				System.out.println(materia1.clave + "  |  " + materia1.nombre); 
				} catch (InputMismatchException ex) {
					System.out.println("Debe ingresar obligatoriamente un número entero.");
					scanner.next();
				}
				break;
			case 3: 
				try {
				System.out.print("Ingrse el Id de la materia que desea eliminar: ");
				id = scanner.nextInt();
				claseAdmnistradora.elimina(id);
				} catch (InputMismatchException ex) {
					System.out.println("Debe ingresar obligatoriamente un número entero.");
					scanner.next();
				}
				break;
			case 4:
				claseAdmnistradora.imprimir();
				break;
			case 5:
				break;
			default:
				System.out.println("Opción no válida");
			}
			} catch (InputMismatchException ex) {
				System.out.println("Debe ingresar obligatoriamente un número entero.");
				scanner.next();
			}
		}
		

	}
	
	//3.	Los campos de la tabla sean: id_secuencial, clave_materia, nombre_materia
	//a)	un atributo tipo Hashtable y otro contador
	private Hashtable<Integer,Materia> tabla; //
	private int contador;
	
	//5.	Los métodos sean públicos;
	public ClaseAdministradora() {// 2.	Implementar un programa que utilice una ClaseAdministradora para escribir en una tabla que maneje registros;
		tabla = new Hashtable<Integer,Materia>();
		contador = 1;
	}
	
	//d)	el objeto tipo Materia no contenga el id_secuencial
	class Materia{
		private String clave, nombre;
		
		// b)	un método que agrega tupla recibiendo 2 parámetros: clave y nombre_materia y regrese el id_secuencial
		public Materia(String clave, String nombre) {
			this.clave = clave; 
			this.nombre = nombre;
		}
	}
	
	//c)	el método anterior agregue un objeto tipo Materia en la tabla (la llave del método put de Hashtable sea el id secuencial envuelto como objeto)
	public int registro(String clave, String nombre) {
		Materia materia = new Materia(clave,nombre);
		
		tabla.put(contador, materia);
		//i)	cada que se agregue ó elimine una tupla, imprimir el contenido actual de la tabla
		imprimir();
		
		return contador++;
	}
	
	//e)	un método que obtenga una Materia de la tabla según parámetro id_secuencial y devuelva el objeto Materia
	public Materia consulta(int idSecuencial) {
		Materia materia = tabla.get(idSecuencial);
		return materia;
	}
	
	//f)	un método que elimine una tupla según parámetro id_secuencial.	
	public void elimina(int idSecuencial) {
		
		tabla.remove(idSecuencial);
		//i)	cada que se agregue ó elimine una tupla, imprimir el contenido actual de la tabla
		imprimir();
	}
	
	//g)	implementar un método que imprima en consola todas las tuplas de la tabla
	public void imprimir() {
		Enumeration<Integer> llaves = tabla.keys();
		Enumeration<Materia> elementos = tabla.elements();
		
		System.out.println("Id_sec  |  Clave  |  Nombre materia");
		while(llaves.hasMoreElements()) {
			Materia materia = elementos.nextElement();
			int id = llaves.nextElement();
			//h)	el método que muestre todas las tuplas, las muestre una por línea 
			System.out.println(id + "       |  " + materia.clave + "  |  " + materia.nombre); 
		}
	}
}	
