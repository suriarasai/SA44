
package dao;

public class DAOException extends Exception {
    public DAOException() {}
    
    public DAOException(String s) {
        super(s);
    }
    
    public DAOException(Exception e) {
        super(e);
    }
}

