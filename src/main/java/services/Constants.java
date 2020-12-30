package services;

public class Constants {
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL_DATABASE = "jdbc:mysql://185.98.131.90/project_manager?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    public static String USERNAME = "djste1070339";
    public static String PASSWORD = "da6ysjpqpp";

    //SQL GESTION PROJET
    public static String SELECT_PROJECTS_QUERY = "select * from pm_projet";
    public static String SELECT_PROJECTS_TASK_QUERY = "select id, libelle, estimation, description, etat, (select fullname from pm_users where  id = tache.idUser ) as user from pm_tache where idprojet = ?";
    public static String INSERT_PROJECT_QUERY = "insert into pm_projet(libelle, etat, description) values (?, ?, ?)";
    public static String DELETE_PROJECT_TASK_QUERY = "delete from pm_tache where idProjet = ?";
    public static String DELETE_PROJECT_QUERY = "delete from pm_projet where id = ?";
    public static String UPDATE_PROJECT_QUERY = "update pm_projet set libelle = ? , etat = ? , description = ? where id = ?";

    //SQL GESTION TACHE
    public static String INSERT_PROJECT_TASK_QUERY = "insert into pm_tache (libelle, estimation, description, etat, idProjet, idUser) values (?, ?, ?, ?, ?, (select id from pm_users where fullname = ?))";
    public static String DELETE_TASK_QUERY = "delete from pm_tache where idProjet = ? and id = ?";
    public static String UPDATE_TASK_QUERY = "update pm_tache set  libelle = ? ,estimation = ?, etat = ? , description = ? , idUser = (select id from pm_users where fullname = ? ) where id = ? and idProjet = ?";

    public static String AUTH_USERS_QUERY = "select * from pm_users where login = ? and password = ?";
    public static String SELECT_USERS_QUERY = "select * from pm_users";
    public static String INSERT_USER_QUERY = "insert into pm_users (fullname, login, password, email, profile) values (?, ?, ?, ?, ?)";
    public static String DELETE_USER_QUERY = "delete from pm_users where id = ?";
    public static String UPDATE_USER_QUERY = "update pm_users set  fullname = ? ,login = ?, password = ? , email = ?, profile = ?  where id = ? ";

    public static String SUCCES_CODE = "SUCCES";
    public static String ERROR_CODE = "ERROR";
}
