package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import javax.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
@Table(name="hotel")
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    public Integer id;

    @Column(name="name")
    public String name;

    @Enumerated
    public Day day;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="country_id", nullable=true)
    public Country country_id;

}