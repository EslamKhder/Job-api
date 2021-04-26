package com.spring.api.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "ID of Job",name="id",required=true)
    private Long id;

    @Column(name = "name")
    @ApiModelProperty(notes = "Name of Job",name="name",required=true)
    private String name;

    @Column(name = "code",unique = true)
    @ApiModelProperty(notes = "code of Job",name="code",required=true)
    private String code;

    @Column(name = "status")
    @ApiModelProperty(notes = "status of Job",name="status",required=true)
    private boolean status;

}
