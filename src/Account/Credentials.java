package Account;

/**
 * `email`
 * `password`
 *
 * Constructor that uses email and password
 * Empty constructor.
 * */
public class Credentials {
    private String email;
    private String password;

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Credentials() {
        this(null, null);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials [email=" + email + ", password=" + password + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return email.equals(((Credentials) obj).email) && password.equals(((Credentials) obj).password);
    }
}
