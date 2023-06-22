package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Task_Data")
@Data
public class TaskForm {
    //フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //start: '2018-10-01 0:00:00', //タスクの開始(時間0:00:00はデフォルトで入れなくてはいけない)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String start;

    //end: '2018-10-08 23:59:59', //タスクの終了(時間23:59:59はデフォルトで入れなくてはいけない)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end;

    //name: 'ウェブのリデザイン',    //タスク名
    @Length(min = 1, max = 255, message = "タスク名は空欄にできません")
    private String name;

    //progress: 20,				//タスクの進捗(0~100)
    @Min(value = 0, message = "0以上100以下の数字を入れてください")
    @Max(value = 100,message = "0以上100以下の数字を入れてください")
    private Integer progress;

    //dependencies: 'Task 0'      //親タスクのid(','で区切る)
    @Pattern(regexp = "[0-9,]")
    private String dependencies;

    @Length(min=0, max = 65535)
    private String detail;

}
