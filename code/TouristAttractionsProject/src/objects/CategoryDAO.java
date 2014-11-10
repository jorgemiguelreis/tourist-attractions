package objects;

import java.sql.*;
import java.util.Vector;

public class CategoryDAO {

	public static void insertCategory(Category cat) throws CategoryException {
		Connection connection = FactoryDAO.getConnection();
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			String sql = "insert into category values('"+cat.getId()+"', '"+ cat.getTitle() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Category inserted successfully");
		} catch ( Exception e ) {
			//e.printStackTrace();
			//System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			throw new CategoryException(cat.getTitle());
		}
	}

	public static void insertCategoriesInheritance(Category cat, Category subcat) {
		
		try {
			Connection connection = FactoryDAO.getConnection();
			Statement stmt = null;
			stmt = connection.createStatement();
			String sql = "insert into categories_inheritance values('"+cat.getId()+ "', '" + subcat.getId() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Category Inheritance inserted successfully");
		} catch ( Exception e ) {
			e.printStackTrace();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}


}
