package kyj.schedule_manage_v2.domain.schedule.entity;

import jakarta.persistence.*;
import kyj.schedule_manage_v2.common.util.entity.Base;
import kyj.schedule_manage_v2.domain.comment.entity.Comment;
import kyj.schedule_manage_v2.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_id", value = ConstraintMode.NO_CONSTRAINT))
    private User user;

    private String title;
    private String content;

    public Schedule(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
