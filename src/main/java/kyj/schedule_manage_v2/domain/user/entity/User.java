package kyj.schedule_manage_v2.domain.user.entity;

import jakarta.persistence.*;
import kyj.schedule_manage_v2.common.util.entity.Base;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public void update(String email, String userName, String password) {
        if(email != null) {
            this.email = email;
        }

        if(userName != null) {
            this.userName = userName;
        }

        if(password != null) {
            this.password = password;
        }
    }
}
