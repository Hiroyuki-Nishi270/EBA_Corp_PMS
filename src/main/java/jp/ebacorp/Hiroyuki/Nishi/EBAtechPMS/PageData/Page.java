package jp.ebacorp.Hiroyuki.Nishi.EBAtechPMS.PageData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "Pages")
@Getter
public class Page {

    @Id
    private Integer id;

    private String mappingString;

    private String controllerName;

    private String viewName;

    private String pageTitle;
}
