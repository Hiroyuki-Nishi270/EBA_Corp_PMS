package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "File_Data")
@Data
public class AttacheFile {
    //フィールド
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //紐づいたチケットのid
    @NotBlank
    private Integer ticketId;

    //ファイル名
    @NotBlank
    private String filename;

    //ファイルパス
    @NotBlank
    private String filePath;

}
