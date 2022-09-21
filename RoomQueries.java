package roomscheduleranthonyalb6732;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Anthony Bravo
 */
public class RoomQueries {
    private static Connection connection;
    private static ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
    private static PreparedStatement getAllRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static ResultSet resultSet;
    
    
    public static ArrayList<RoomEntry> getAllRooms(){
        //Getting all rooms from rooms table.
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<RoomEntry>();
        try
        {
            getAllRooms = connection.prepareStatement("select name, seats from rooms order by name");
            resultSet = getAllRooms.executeQuery();
            
            while(resultSet.next())
            {
                rooms.add(new RoomEntry(resultSet.getInt("Seats"), resultSet.getString("Name")));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public static void addRoom(String name, Integer seats){
        //Adding a room to rooms table by a given name and seats.
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (name, seats) values (?, ?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static void dropRoom(String name, Integer seats){
        //Dropping a room by a given name and seats.
        connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("delete from rooms where name = (?) and seats = (?)");
            dropRoom.setString(1, name);
            dropRoom.setInt(2, seats);
            addRoom.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }   
    }
  
}
