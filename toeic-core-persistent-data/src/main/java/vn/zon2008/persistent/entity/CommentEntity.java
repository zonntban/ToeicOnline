package vn.zon2008.persistent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by zon2008 on 6/17/2018.
 */
@Table(name = "comment")
@Entity
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity creator;
    @ManyToOne
    @JoinColumn(name = "thread_id")
    private ThreadEntity thread;
}
