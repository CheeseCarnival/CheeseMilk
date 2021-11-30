package cn.qisee.common.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_category")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BasicEntity{

    private String categoryName;

    private String description;

    private String avatarUrl;

}
