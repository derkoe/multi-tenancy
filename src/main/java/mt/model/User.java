package mt.model;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String username;

    @Column(length = 100)
    private String password;

    private Boolean enabled;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "authorities")
    @Column(name = "authority", length = 100)
    private Set<String> authorities;

    User()
    {
    }

    public User(String username, String password, boolean enabled)
    {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    public Set<String> getAuthorities()
    {
        return authorities;
    }
}
