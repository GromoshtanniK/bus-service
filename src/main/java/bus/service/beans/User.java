package bus.service.beans;

public class User {

    public static final int GUEST = 0;
    public static final int REGISTERED_USER = 1;
    public static final int DISPATCHER = 2;

    private String userName;
    private String password;
    private int role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User createGuestUser() {
        return new User();
    }

    public boolean isGuest() {
        return role == GUEST;
    }

    public boolean isRegisteredUser() {
        return role == REGISTERED_USER;
    }

    public boolean isDispatcher() {
        return role == DISPATCHER;
    }
}
