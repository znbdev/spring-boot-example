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
public class CommonEntity implements Serializable {

    @Column(name = "create_date", nullable = false, updatable = false)
    private Timestamp createDate;

    @Column(name = "update_date", nullable = false)
    private Timestamp updateDate;

    // 在持久化之前，设置createDate和updateDate
    // @PrePersist 是一个 JPA（Java Persistence API）注解，
    // @PrePersist：在实体第一次被持久化（保存到数据库）之前调用。
    // 这个方法通常用于初始化某些字段或执行一些验证逻辑，以确保实体在第一次保存时是有效的。
    // 标注了 @PrePersist 的方法会自动被调用。
    @PrePersist
    public void onPrePersist() {
        setCreateDate(new Timestamp(System.currentTimeMillis()));
        setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }

    // 在更新之前，设置updateDate
    // @PreUpdate：在实体被更新到数据库之前调用。
    // 这个方法通常用于在实体更新之前执行某些操作，如修改时间戳、验证数据一致性等。
    @PreUpdate
    public void onPreUpdate() {
        setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }
}
