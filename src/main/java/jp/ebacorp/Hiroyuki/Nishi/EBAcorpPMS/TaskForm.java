package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class TaskForm {

    //概要
    @Range(min = 1, max = 255)
    private String overview;

    //詳細
    @Range(min = 1, max = 65535)
    private String detail;

    //期限
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    //状態
    //検討中
    private int Status;

    //前チケット
    @Pattern(regexp = "[0-9,]*")
    private String reference;
}
