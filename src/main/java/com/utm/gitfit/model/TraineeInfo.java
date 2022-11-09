package com.utm.gitfit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "trainee_info")
public class TraineeInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "fatRatio")
    private Double fatRatio;

    @Column(name = "suggestions")
    private String suggestions;

    @Column(name = "diet")
    private String diet;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Client client;
}
