package lk.ijse.hotBurger.dto;

public class UserDto {
        int id;
        String username;
        String password;
        String type;
        String emailAddress;
        String phone;

    public UserDto(){}

    public UserDto(int id, String username, String password, String type, String emailAddress, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.emailAddress = emailAddress;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
