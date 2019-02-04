package com.alexandru.obaj.soa.user.dl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "SOA_USER")
@Entity
@Data
@Slf4j
public class UserEntity {

    @Column(name = "ID")
    @Id
    private int id;

    public UserEntity() {
        super();
    }
}
