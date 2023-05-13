package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Pages")
@Data
public class Page {

    @Id
    private Integer id;

    private String mappingString;

    private String controllerName;

    private String viewName;

    private String pageTitle;
}
