public abstract class User 
{
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() 
    {
        return username;
    }

    protected boolean validatePassword(String inputPassword) 
    {
        return password.equals(inputPassword);
    }

    public abstract boolean login(String username, String password);

}
