package com.alexandru.obaj.soa.user.dl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity class modelling a User of the system.
 */
@Entity
@Table(name = "SOA_USER")
@NamedQueries(value = {@NamedQuery(name = "UserEntity.findByUserIdAndPassword",
        query = "from UserEntity u WHERE u.userId =:userId AND u.password =:password"), @NamedQuery(name = "UserEntity.findByUserId",
        query = "from UserEntity u WHERE u.userId =:userId")})
@Data
@EqualsAndHashCode(of = {"id", "userId"})
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "USERID", nullable = false)
    private String userId;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    /**
     * Explicit empty constructor.
     */
    public UserEntity() {
        super();
    }
}
