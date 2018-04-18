import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * The aim of this class is to read the java properties file.
 * 
 * @author 160021429
 */
public class ReadingProperties {
	private Properties properties = null;
	
	//attributes from the properties file
	private String type;
	private String host;
	private String port;
	private String userName;
	private String password;
	private String db;
	/**
	 * This constructor takes the String path as a parameter to get the path name of the
	 * java properties file. Then it uses the getProperty method to get information from
	 * the java properties file. Then it stores those information into the private fields.
	 * Also, this constructor throws the IOException to avoid the possible problem.
	 * 
	 * @param url
	 * @throws IOException
	 */
	ReadingProperties(String path) throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(path));
		//set values to private fields
		type = properties.getProperty("type");
		host = properties.getProperty("host");
		port = properties.getProperty("port");
		userName = properties.getProperty("username");
		password = properties.getProperty("password");
		db = properties.getProperty("db");
	}
	/**
	 * The aim of this method is returning the database url. It returns the string 
	 * to connect the database.
	 * @return
	 */
	public String getURL(){
		StringBuffer sb = new StringBuffer("jdbc:");
		sb.append(type);
		sb.append("://");
		sb.append(host);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(db);
		return sb.toString();
	}
	/**
	 * This method is the getter for the userName
	 * @return
	 */
	public String getUserName(){
		return userName;
	}
	/**
	 * This method is the getter for the password
	 * @return
	 */
	public String getPassword(){
		return password;
	}
}

