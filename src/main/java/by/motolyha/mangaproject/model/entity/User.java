package by.motolyha.mangaproject.model.entity;

import java.util.Objects;

public class User implements Entity {

    private int id;
    private String login;
    private String password;
    private String description;
    private String email;
    private Role role;
    private String avatarSrc;

    public User(int id, String login, String password, String description,
                String email, Role role, String avatarSrc) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.description = description;
        this.email = email;
        this.role = role;
        this.avatarSrc = avatarSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var user = (User) o;
        return id == user.id
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(description, user.description)
                && Objects.equals(email, user.email)
                && role == user.role
                && Objects.equals(avatarSrc, user.avatarSrc);
    }

    @Override
    public int hashCode() {
        int result = 31 * id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (avatarSrc != null ? avatarSrc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append(", avatarSrc='").append(avatarSrc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
