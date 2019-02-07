package com.alexandru.obaj.soa.user.dl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Table(name = "SOA_USER")
@Entity
@Data
@Slf4j
public class UserEntity {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public UserEntity() {
        super();
    }
}
