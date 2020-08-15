package vn.zon2008.persistent.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zon2008 on 6/17/2018.
 */
@Entity
@Table(name = "thread")
@Getter
@Setter
public class ThreadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "image")
    private String image;
    @Column(name = "content")
    private String content;
    @Column(name = "create_date")
    private Timestamp createDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccountEntity creator;
    @OneToMany(mappedBy = "thread", fetch = FetchType.LAZY)
    private List<CommentEntity> commentList;
}
