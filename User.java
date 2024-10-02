public abstract class User 
{
    private String email;
    private String password;

    public User(String email, String password) 
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail() 
    {
        return email;
    }

    public boolean login(String email, String password) 
    {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void logout() 
    {
        System.out.println("Logged out\n");
    }
}
