package br.com.nexus.goat.exceptions.user;

public class IncompleteDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IncompleteDataException() {
        super("Os dados estão incompletos!");
    }
    
}
