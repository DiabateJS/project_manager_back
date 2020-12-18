package services;

public class Constants {
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL_DATABASE = "jdbc:mysql://localhost/project_manager?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    public static String USERNAME = "root";
    public static String PASSWORD = "";

    public static String SELECT_PROJECTS_QUERY = "select * from projet";
    public static String SELECT_PROJECTS_TASK_QUERY = "select * from tache where idprojet = ";
    public static String INSERT_PROJECT_QUERY = "insert into projet(libelle, etat, description) values ";
    public static String DELETE_PROJECT_TASK_QUERY = "delete from tache where idProjet = ";
    public static String DELETE_PROJECT_QUERY = "delete from projet where id = ";
    public static String UPDATE_PROJECT_QUERY = "update projet set ";

    public static String SUCCES_CODE = "SUCCES";
    public static String ERROR_CODE = "ERROR";
}
