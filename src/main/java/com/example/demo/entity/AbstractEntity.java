package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Column(name = "create_date", nullable = false, updatable = false)
    private Timestamp createDate;

    @Column(name = "update_date", nullable = false)
    private Timestamp updateDate;

    @PrePersist
    public void onPrePersist() {
        setCreateDate(new Timestamp(System.currentTimeMillis()));
        setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }
}
