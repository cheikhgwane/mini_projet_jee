package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Client;

public class ClientDao {
	private static String ADD_QUERY = "INSERT INTO CLIENT(nom,prenom,adresse,telephone) VALUES(?,?,?,?)";
	private static String SELECT_QUERY = "SELECT * FROM CLIENT ";
	private static String DELETE_QUERY = "DELETE FROM CLIENT WHERE idClient = ?";
	private static String UPDATE_QUERY = "UPDATE CLIENT set nom = ?, prenom = ?, adresse = ?, telephone = ? WHERE idClient = ?";
	
	public static void ajouterClient(Client c) throws DaoException {
		Connection connection = DatabaseManager.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(ADD_QUERY);
			statement.setString(1, c.getNom());
			statement.setString(2, c.getPrenom());
			statement.setString(3, c.getAdresse());
			statement.setString(4, c.getTelephone());
			statement.execute();
		}catch(SQLException s) {
			throw new DaoException("Aborting insertion operation: " +s.getMessage());
		}
	}
	
	public static ArrayList<Client> getClients() throws DaoException{
		ArrayList<Client> clients = new ArrayList<Client>();
		Connection connection = DatabaseManager.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_QUERY);
			String nom,prenom,adresse,telephone;
			int idClient ;
			while(resultSet.next()) {
				idClient= resultSet.getInt("idClient");
				nom = resultSet.getString("nom");
				prenom = resultSet.getString("prenom");
				adresse = resultSet.getString("adresse");
				telephone = resultSet.getString("telephone");
				clients.add(new Client(idClient,nom, prenom, adresse, telephone));
			}
		}catch(SQLException s) {
			throw new DaoException(s.getMessage());
		}
		return clients;
	}
	
	public static void updateClient(Client c) throws DaoException {
		Connection connection = DatabaseManager.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, c.getNom());
			statement.setString(2, c.getPrenom());
			statement.setString(3, c.getAdresse());
			statement.setString(4, c.getTelephone());
			statement.setInt(5, c.getIdClient());
			statement.executeUpdate();
			System.out.println(c.getIdClient()+" "+c.getPrenom());
		}catch(SQLException s) {
			throw new DaoException("Aborting update operation : " +s.getMessage());
		}
	}
	
	public static void deleteClient(int idClient) throws DaoException {
		Connection connection = DatabaseManager.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
			statement.setInt(1, idClient);
			statement.execute();
		}catch(SQLException s) {
			throw new DaoException("Aborting delete operation : " +s.getMessage());
		}
	}

}
