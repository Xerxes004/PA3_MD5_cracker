package exceptions;

public class DictionaryNotFoundException 
        extends Exception {

    public DictionaryNotFoundException() {
        super("No dictionary was specified, or the given dictionary was not found.");
    }
    
}
