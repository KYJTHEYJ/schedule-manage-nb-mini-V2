package kyj.schedule_manage_v2.domain.comment.entity;

import jakarta.persistence.*;
import kyj.schedule_manage_v2.common.util.entity.Base;
import kyj.schedule_manage_v2.domain.schedule.entity.Schedule;
import kyj.schedule_manage_v2.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false ,foreignKey = @ForeignKey(name = "fk_user_id", value = ConstraintMode.NO_CONSTRAINT))
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_id", nullable = false, foreignKey = @ForeignKey(name = "fk_schedule_id", value = ConstraintMode.NO_CONSTRAINT))
    private Schedule schedule;

    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }
}
