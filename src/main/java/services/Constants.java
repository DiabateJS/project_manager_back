package services;

public class Constants {
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL_DATABASE = "jdbc:mysql://localhost/project_manager?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    public static String USERNAME = "root";
    public static String PASSWORD = "";

    //SQL GESTION PROJET
    public static String SELECT_PROJECTS_QUERY = "select * from projet";
    public static String SELECT_PROJECTS_TASK_QUERY = "select * from tache where idprojet = ?";
    public static String INSERT_PROJECT_QUERY = "insert into projet(libelle, etat, description) values (?, ?, ?)";
    public static String DELETE_PROJECT_TASK_QUERY = "delete from tache where idProjet = ?";
    public static String DELETE_PROJECT_QUERY = "delete from projet where id = ?";
    public static String UPDATE_PROJECT_QUERY = "update projet set libelle = ? , etat = ? , description = ? where id = ?";

    //SQL GESTION TACHE
    public static String INSERT_PROJECT_TASK_QUERY = "insert into tache(libelle, estimation, description, etat, idProjet) values (?, ?, ?, ?, ?)";
    public static String DELETE_TASK_QUERY = "delete from tache where idProjet = ? and id = ?";
    public static String UPDATE_TASK_QUERY = "update tache set  libelle = ? ,estimation = ?, etat = ? , description = ? where id = ? and idProjet = ?";

    public static String SELECT_USERS_QUERY = "select * from users";
    public static String INSERT_USER_QUERY = "insert into users(fullname, login, password, email, profile) values (?, ?, ?, ?, ?)";
    public static String DELETE_USER_QUERY = "delete from users where id = ?";
    public static String UPDATE_USER_QUERY = "update users set  fullname = ? ,login = ?, password = ? , email = ?, profile = ?  where id = ? ";

    public static String SUCCES_CODE = "SUCCES";
    public static String ERROR_CODE = "ERROR";
}
