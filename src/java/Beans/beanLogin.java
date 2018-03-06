package beans;

import java.io.Serializable;

public class beanLogin implements Serializable {
    
    public beanLogin() {
    }
   
    public boolean check( String user, String password) {
        
        if( user==null) return false;
        if( password==null) return false;
        if( user.trim().isEmpty()) return false;
        if( password.trim().isEmpty()) return false;
        
        if( user.equals("admin"))
            if( password.equals("root"))
                return true;
        
        return false;
    }
    
}






