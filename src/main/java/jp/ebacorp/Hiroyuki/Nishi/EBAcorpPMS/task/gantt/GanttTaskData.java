package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "frappe_gantt_task_data")
@Data
public class GanttTaskData {
    //フィールド

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private String id;

    //start: '2018-10-01 0:00:00', //タスクの開始(時間0:00:00はデフォルトで入れなくてはいけない)
    @JsonProperty("start")
    private String start;

    //end: '2018-10-08 23:59:59', //タスクの終了(時間23:59:59はデフォルトで入れなくてはいけない)
    @JsonProperty("end")
    private String end;

    //name: 'ウェブのリデザイン',    //タスク名
    @JsonProperty("name")
    private String name;

    //progress: 20,				//タスクの進捗(0~100)
    @JsonProperty("progress")
    private Integer progress;

    //dependencies: 'Task 0'      //親タスクのid(','で区切る)
    @JsonProperty("dependencies")
    private String dependencies;

}
