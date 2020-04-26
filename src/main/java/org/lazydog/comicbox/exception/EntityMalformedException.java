package org.lazydog.comicbox.exception;

/**
 * Entity malformed exception.
 * 
 * @author rjrjr
 */
public class EntityMalformedException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public EntityMalformedException(String message) {
        super(message);
    }
    
    public EntityMalformedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public EntityMalformedException(Throwable cause) {
        super(cause);
    }
}
