package Validation;

public class Validate {
    public boolean isnull(String uname) {
        if (uname.isEmpty()) {
            return true;
        }
        return false;
    }
}
