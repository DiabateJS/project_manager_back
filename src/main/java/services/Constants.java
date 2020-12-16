package services;

public class Constants {
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL_DATABASE = "jdbc:mysql://localhost/project_manager?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    public static String USERNAME = "root";
    public static String PASSWORD = "";

    public static String SELECT_PROJECTS_QUERY = "select * from projet";
    public static String SELECT_PROJECTS_TASK_QUERY = "select * from tache where idprojet = ";
}
