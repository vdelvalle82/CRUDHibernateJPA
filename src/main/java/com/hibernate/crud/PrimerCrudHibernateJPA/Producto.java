package com.hibernate.crud.PrimerCrudHibernateJPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//con entity decimos que la clase es una entidad en una base de datos
@Entity
//indicamos que la clase producto se almacena en la tabla productos
@Table(name="productos")
public class Producto {
	//id identifica que es el id de la tabla a la que se va a mapear
		@Id
		//decimos que va a ser este campo autoincrementable
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@Column
		private String nombre;
		@Column
		private double precio;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		@Override
		public String toString() {
			return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
		}
}
