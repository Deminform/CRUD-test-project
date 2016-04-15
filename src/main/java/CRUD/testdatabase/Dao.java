package CRUD.testdatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private static User user;
    private static List<User> users;

    public static void main(String[] args) {

        // -------------- Creating users -------------
//        for (int i=0; i<15; i++) {
//            user = new User();
//            user.setName(i<10 ? "User 0"+i : "User "+i);
//            user.setAge((int) (Math.random() * 80));
//            user.setAdmin((int) (Math.random() * 7) % 2 != 0);
//            create(user);
//        }
//        printAllUsers();

        // -------------- Reading users -------------
//        getAllUsersDB();      // #1 get all users
//        getUserById(35);      // #2 get specifically user

        // -------------- Updating users -------------
        // Updating by any field/fields
//        user = new User();
//        user.setId(30);
//        user.setName("Prokofiy");
//        user.setAge((int) (Math.random() * 80));
//        user.setAdmin((int) (Math.random() * 7) % 2 != 0);
//        updateById(user);\

        // -------------- Deleting users -------------
//        delete("User 05");    //  #1 delete by name user
//        delete(false);        //  #1 delete by boolean values
//        delete(45);           //  #1 delete by id user

    }

    public static void create(User user) {
        String createUser = "INSERT INTO crud VALUES (?,?,?,?,?)";
        CrudDBConnection connection = new CrudDBConnection();
        try (
                PreparedStatement statement = connection.getConnection().prepareStatement(createUser)
        )   {
            statement.setInt(1, statement.getMaxRows());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getAge());
            statement.setBoolean(4, user.isAdmin());
            statement.setDate(5, Date.valueOf(LocalDate.now().toString()));

            statement.execute();
            connection.getConnection().close();
            System.out.println("User created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateById(User user) {
        User userTMP = getUserById(user.getId());
        String updateUser = "UPDATE crud SET name=?, age=?, admin=? where id="+user.getId();
        CrudDBConnection connection = new CrudDBConnection();
        try (
                PreparedStatement statement = connection.getConnection().prepareStatement(updateUser)
        )   {
            statement.setString(1, user.getName() != null ? user.getName() : userTMP.getName());
            statement.setInt(2, user.getAge() != null ? user.getAge() : userTMP.getAge());
            statement.setBoolean(3, user.isAdmin() != null ? user.isAdmin() : userTMP.isAdmin());

            statement.executeUpdate();
            connection.getConnection().close();
            System.out.println("User updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsersDB() {
        String getUser = "select * from crud";
        List<User> users = new ArrayList<>();
        CrudDBConnection connection = new CrudDBConnection();
        try (
                Statement statement = connection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(getUser)
        )   {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setAdmin(resultSet.getBoolean("admin"));
                user.setDate(LocalDate.parse(resultSet.getString("createdDate").split(" ")[0]));

                users.add(user);
            }
            connection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(Integer id) {
        User userTMP = new User();
        String params = "select * from crud where id = "+id;
        CrudDBConnection connection = new CrudDBConnection();
        try (
                Statement statement = connection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(params)
        )   {
            resultSet.next();
            userTMP.setId(resultSet.getInt("id"));
            userTMP.setName(resultSet.getString("name"));
            userTMP.setAge(resultSet.getInt("age"));
            userTMP.setAdmin(resultSet.getBoolean("admin"));
            userTMP.setDate(LocalDate.parse(resultSet.getString("createdDate").split(" ")[0]));
            connection.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTMP;
    }

    public static void delete(String value) {
        String updateUser = "DELETE FROM crud where name='"+value+"'";
        CrudDBConnection connection = new CrudDBConnection();
        try (
                PreparedStatement statement = connection.getConnection().prepareStatement(updateUser)
        )   {
            statement.execute();
            connection.getConnection().close();
            System.out.println("User removed by name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Integer value) {
        String updateUser = "DELETE FROM crud where id="+value;
        CrudDBConnection connection = new CrudDBConnection();
        try (
                PreparedStatement statement = connection.getConnection().prepareStatement(updateUser)
        )   {
            statement.execute();
            connection.getConnection().close();
            System.out.println("User removed by id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Boolean value) {
        String updateUser = "DELETE FROM crud where admin="+value;
        CrudDBConnection connection = new CrudDBConnection();
        try (
                PreparedStatement statement = connection.getConnection().prepareStatement(updateUser)
        )   {
            statement.execute();
            connection.getConnection().close();
            System.out.println("User removed for boolean values");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllUsers() {
        users = getAllUsersDB();
        for (User tmp : users) {
            System.out.println(tmp);
        }
    }
}
