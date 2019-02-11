package com.alexandru.obaj.soa.alarm.dl.model;

import com.alexandru.obaj.soa.user.dl.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity class modelling a User defined alarm for a given stock.
 */
@Entity
@Table(name = "SOA_ALARM")
@NamedQueries(value = {@NamedQuery(name = "AlarmEntity.findByUserId",
        query = "from AlarmEntity ae WHERE ae.user.userId =:userId"), @NamedQuery(name = "AlarmEntity.deleteByUserId",
        query = "DELETE from AlarmEntity ae WHERE ae.user.userId =:userId")})
@Data
@AllArgsConstructor
public class AlarmEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "SOA_USER_ID", nullable = false, updatable = false)
    private UserEntity user;

    @Column(name = "STOCKID", nullable = false)
    private String stockId;

    @Column(name = "INITIAL_VALUE", nullable = false)
    private int intialValue;


    @Column(name = "INCREASE_PERCENTAGE", nullable = false)
    private int increasePercentage;

    @Column(name = "DECREASE_PERCENTAGE", nullable = false)
    private int decreasePercentage;

    /**
     * Explicit empty constructor.
     */
    public AlarmEntity() {
        super();
    }
}
