package graphics.data;

/*
 * DataAccessHelper provides methods to talk to Postgres data store
 * It can be further customized to talk to any RDBMS that is supported
 * using JDBC
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class DataAccessHelper {
	
	/*
	 * Method to execute a SELECT kind of query
	 */
	public static ResultSet executeQuery(String sql) throws SQLException
	{
		Connection conn = getConnection();
		ResultSet rs = null;
		
		try
		{
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		}
		catch (SQLException sqlEx)
		{
			throw sqlEx;
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			try {
//				conn.close();
			}
			catch(Exception ex)
			{
				
			}
		}
		
		return rs;		
	}

        public static ResultSet executeQuery(String sql,ArrayList<Object> params,ArrayList<String> paramTypes) throws SQLException
	{
		Connection conn = getConnection();
		ResultSet rs = null;

		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
                        setParameters(stmt, params, paramTypes);
			rs = stmt.executeQuery();
		}
		catch (SQLException sqlEx)
		{
			throw sqlEx;
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			try {
//				conn.close();
			}
			catch(Exception ex)
			{

			}
		}

		return rs;
	}
	
	/*
	 * Method to execute DML statements
	 */
	public static int execute(String sql,ArrayList<Object> params,ArrayList<String> paramTypes)
	{
		Connection conn = getConnection();
		int recordsAffected = 0;
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);                        
                        setParameters(stmt, params, paramTypes);
			recordsAffected = stmt.executeUpdate();
		}
		catch (SQLException sqlEx)
		{
			System.out.println(sqlEx.toString());
		}
		finally
		{
			try {
				conn.close();
			}
			catch(Exception ex)
			{
				
			}
		}
		
		return recordsAffected;
	}
        
        private static void setParameters(PreparedStatement stmt, ArrayList<Object> params,ArrayList<String> paramTypes)
            throws SQLException
        {
            for(int i=0; i < params.size(); i++){
                if(paramTypes.get(i).equals("String")){
                    stmt.setString(i+1, (String) params.get(i));
                }
                else if (paramTypes.get(i).equals("Integer")) {
                    stmt.setInt(i+1, (Integer) params.get(i));
                }
                else if (paramTypes.get(i).equals("Double")){
                    stmt.setDouble(i+1, (Double) params.get(i));
                }
                else if (paramTypes.get(i).equals("Boolean")){
                    stmt.setBoolean(i+1, (Boolean) params.get(i));
                }
            }
        }
	
	/*
	 * Method to establish connection with the database
	 */
	private static Connection getConnection()
	{
		Connection conn = null;
		
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/graphics","root","root");
                        conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=Graphics;user=dba;password=dba;encrypt=false;");
                }
		/*catch (ClassNotFoundException cnfEx)
		{			
		}*/
		catch (SQLException sqlEx)
		{			
		}
		
		return conn;
	}

}
