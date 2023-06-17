package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@Entity
public class TaskForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //概要
    @Length(min = 1, max = 255)
    private String overview;

    //期限
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //private Date deadline;

}
