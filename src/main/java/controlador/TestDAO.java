package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDAO {
	
	private ConexionBD con;
	
	public TestDAO(String jdbcURL, String jdbcUser, String jdbcPassword) throws SQLException {
		con = new ConexionBD(jdbcURL, jdbcUser, jdbcPassword);
		//con.conectar();
		//System.out.println(con.getJdbcConecction());
	}
	
	public boolean registrar(Articulo obj) {
		boolean bandera = false;
		Statement stm;
		Articulo objArticulo = new Articulo(0, "Camiseta", "color celeste", 476);
		String sql = "INSERT INTO articulos VALUES(NULL,'"+obj.getNombre()+"', '"+obj.getDescripcion()+"',"+obj.getPrecio()+")";
		String sql1 = "INSERT INTO articulos VALUES(NULL,'"+objArticulo.getNombre()+"', '"+objArticulo.getDescripcion()+"',"+objArticulo.getPrecio()+")";
		try {
			con.conectar();
			con.getJdbcConecction().setAutoCommit(false);
			stm = con.getJdbcConecction().createStatement();
			stm.executeUpdate(sql1);
			stm.executeUpdate(sql);
			con.getJdbcConecction().commit();
			bandera = true;
			stm.close();
			con.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			bandera = false;
			try {
				con.getJdbcConecction().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		return bandera;
		
	}
	
	public boolean editar(Articulo obj) {
		boolean bandera = false;
		Statement stm;
		String sql = "UPDATE articulos SET nombre='"+obj.getNombre()+"' WHERE id="+obj.getId();
		
		try {
			con.conectar();
			stm = con.getJdbcConecction().createStatement();
			stm.execute(sql);
			bandera = true;
			stm.close();
			con.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			bandera = false;
			e.printStackTrace();
		}
		
		return bandera;
	}
	
	public boolean eliminar(Articulo obj) {
		boolean bandera = false;
		Statement stm;
		String sql = "DELETE FROM articulos WHERE id="+obj.getId();
		
		try {
			con.conectar();
			stm = con.getJdbcConecction().createStatement();
			stm.executeUpdate(sql);
			bandera = true;
			stm.close();
			con.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			bandera = false;
			e.printStackTrace();
		}
		
		return bandera;
	}
	
	public void consultar() {
		Statement stm;
		ResultSet res = null;
		Articulo objArticulo;
		String sql = "SELECT * FROM articulos";
		
		try {
			con.conectar();
			stm = con.getJdbcConecction().createStatement();
			res = stm.executeQuery(sql);
			while (res.next()) {
				objArticulo = new Articulo(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"), res.getDouble("precio"));
				System.out.println(objArticulo);
			}
			stm.close();
			con.desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
