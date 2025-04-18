package com.sandriver.api.model.base;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.sandriver.common.utils.Xid;


import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity {

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null || this.id.isEmpty()) {
            this.id = Xid.string();
        }
        Date now = new Date();
        this.createdAt = now;
        this.updatedAt = now;
        System.out.println("PrePersist called: id=" + this.id + ", createdAt=" + this.createdAt);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
