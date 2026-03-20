import java.io.Serializable;

public abstract class User implements Serializable
{
    private String username;
    private String password;
    private String name;
    private boolean isVIP;

    public User(String username, String password, String name) 
    {
        this.username=username;
        this.password=password;
        this.name=name;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getPassword() 
    {
        return password;
    }

    public String getName() 
    {
        return name;
    }

    public boolean isVIP() 
    {
        return isVIP;
    }

    protected boolean validatePassword(String inputPassword) 
    {
        return password.equals(inputPassword);
    }

    public abstract boolean login(String username, String password);

}
