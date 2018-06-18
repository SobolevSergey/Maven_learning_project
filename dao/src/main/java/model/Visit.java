package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Visit extends Model {
    @ManyToOne()
    @JoinColumn(name = "user_login",nullable = false)
    private User user;
    private Date visitdate;

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
