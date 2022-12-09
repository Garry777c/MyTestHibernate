package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name="country")
public class Country {

    @Id
    @Column(name="country_id")
    public Integer countryId;

    @Transient
    public String name;

    public Date date;


}