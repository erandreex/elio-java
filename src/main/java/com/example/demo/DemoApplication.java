package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Endpoint
	@GetMapping("/hello")
	public String hello() {
		System.out.println("Hola elias");
		return "Hola Elias";
	}

	// Endpoint
	@GetMapping("/usuario")
	public ModeloUsuarios usuario(@RequestParam(value = "id", defaultValue = "none") String id) {
		ModeloUsuarios usuario = new ModeloUsuarios();
		usuario = listaUsuarioBaseDatos(id);
		return usuario;
	}

	public ModeloUsuarios listaUsuarioBaseDatos(String id) {
		ModeloUsuarios usuario = new ModeloUsuarios();

		try (Connection conexion = ConexionDB.getConection();
				PreparedStatement ps = conexion.prepareStatement("select * from prueba.usuarios where id = " + id)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				usuario.setId(rs.getString(1));
				usuario.setNombre(rs.getString(2));
				usuario.setApellido(rs.getString(3));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return usuario;
	}

	//

	// Endpoint
	@GetMapping("/usuarios")
	public List<ModeloUsuarios> usuarios() {
		List<ModeloUsuarios> usuarios = new ArrayList<>();
		usuarios = listarUsuariosBaseDatos();
		return usuarios;
	}

	public List<ModeloUsuarios> listarUsuariosBaseDatos() {

		List<ModeloUsuarios> listaUsuarios = new ArrayList<>();

		try (Connection conexion = ConexionDB.getConection();
				PreparedStatement ps = conexion.prepareStatement("select * prueba.from usuarios")) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ModeloUsuarios usuario = new ModeloUsuarios();
				usuario.setId(rs.getString(1));
				usuario.setNombre(rs.getString(2));
				usuario.setApellido(rs.getString(3));
				listaUsuarios.add(usuario);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return listaUsuarios;
	}

}
