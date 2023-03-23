package data;

public class Administrator extends User{
    private static int ID = 0;
    public Administrator(String username, String password) {
        super(username, password);
        ID++;
        this.setId(ID);
        this.setRole("ADMIN");
    }
}
