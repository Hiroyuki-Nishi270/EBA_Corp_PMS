package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AttachFile {
    //フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //紐づいたチケットのid
    private Integer ticketid;

    //ファイル名
    private String filename;

    //ファイルパス
    private String filepath;

    public void setAttachFile(Integer ticketId, String fileName, String filePath){
        this.ticketid = ticketId;
        this.filename = fileName;
        this.filepath = filePath;
    }


}
