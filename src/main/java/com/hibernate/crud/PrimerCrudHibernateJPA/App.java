package com.hibernate.crud.PrimerCrudHibernateJPA;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class App 
{
    public static void main( String[] args )
    {
    	//nombre del archivo persistence
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrimerCrudHibernateJPA");

		EntityManager em = emf.createEntityManager();
		
		int opcion = 0;
		//almacen valores ingresados por consola
		Scanner scanner = new Scanner(System.in);
		//creamos un producto de la clase Producto
		Producto producto;
		
		//Menú
				while (opcion!=5) {
					System.out.println("1. Crear Producto");
					System.out.println("2. Buscar Producto");
					System.out.println("3. Actualizar Producto");
					System.out.println("4. Eliminar Producto");
					System.out.println("5. Salir");
					System.out.println("Elija una opción:");
					opcion = scanner.nextInt();
					switch (opcion) {
					case 1:
						System.out.println("Digite el nombre del producto:");
						//creo objeto tipo producto
						producto = new Producto();
						producto.setId(null);
						scanner.nextLine();
						producto.setNombre(scanner.nextLine());

						System.out.println("Digite el precio del producto:");
						producto.setPrecio(scanner.nextDouble());
						System.out.println(producto);
						em.getTransaction().begin();
						em.persist(producto);
						em.getTransaction().commit();
						
						System.out.println("Producto registrado..");
						System.out.println();
						break;
							
					case 2:
						System.out.println("Digite el id del producto a buscar:");
						producto = new Producto();
		
						producto = em.find(Producto.class, scanner.nextLong());
						
						if (producto != null) {
							System.out.println(producto);
							System.out.println();
						} else {
							System.out.println();
							System.out.println("Producto no encontrado... Lista de productos completa");
							
							//guardamos los producto en el arreglo
							List<Producto> listaProductos= new ArrayList<>();
							Query query=em.createQuery("SELECT p FROM Producto p");
							//obtenemos los registros mapeados hacia objetos java y guardados en listaProductos.
							listaProductos=query.getResultList();
							for (Producto p : listaProductos) {
								System.out.println(p);
							}
							
							System.out.println();
						}

						break;
						
						//actualizar
					case 3:
						System.out.println("Digite el id del producto a actualizar:");
						producto = new Producto();

						producto = em.find(Producto.class, scanner.nextLong());
						if (producto != null) {
							System.out.println(producto);
							System.out.println("Digite el nombre del producto:");
							scanner.nextLine();
							producto.setNombre(scanner.nextLine());
							System.out.println("Digite el precio del producto:");
							producto.setPrecio(scanner.nextDouble());
							
							em.getTransaction().begin();
							//actualiza merge
							em.merge(producto);
							em.getTransaction().commit();
							
							System.out.println("Producto actualizado..");
							System.out.println();
						} else {
							System.out.println("Producto no encontrado....");
							System.out.println();
						}
						break;
					case 4:
						//Elimina
						System.out.println("Digite el id del producto a eliminar:");
						producto = new Producto();
						//busca el producto con el método find y con remove lo elimina
						producto = em.find(Producto.class, scanner.nextLong());
						if (producto != null) {
							System.out.println(producto);
							em.getTransaction().begin();
							//remove borra
							em.remove(producto);
							em.getTransaction().commit();
							System.out.println("Producto eliminado...");
						} else {
							System.out.println("Producto no encontrado...");
						}
						break;
					case 5:
						System.out.println("Terminando...");
						em.close();
					break;

					default:
						System.out.println("Opción no válida\n");
						break;
					}
				}
			}
		}
