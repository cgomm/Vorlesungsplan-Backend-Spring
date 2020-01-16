package api.database.psql;

import controller.PostgreSQLController;

import java.sql.*;

public class CourseListTable {
    private static PostgreSQLController psql = PostgreSQLController.getInstance();

    public static boolean checkIfCourseListTableExist() {
        try {
            DatabaseMetaData dbm = psql.getConnection().getMetaData();
            ResultSet result = dbm.getTables(null, null, "courselist", null);
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Deprecated
    private static void createCourseListTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS courselist(id SERIAL NOT NULL PRIMARY KEY, courseValue int8 NOT NULL, courseName varchar(255))";
        try {
            PreparedStatement st = psql.getConnection().prepareStatement(SQL);
            st.executeUpdate();
            st.close();
            System.out.println("Created Table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillCourseList() {

    }
}
