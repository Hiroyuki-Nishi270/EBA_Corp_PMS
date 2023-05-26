package jp.ebacorp.Hiroyuki.Nishi.EBAtecPMS;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Properties {

    @Value("${page.default}")
    private String pageDefault;

    @Value("${page.index}")
    private String pageIndex;

    @Value("${page.register}")
    private String pageRegister;

    @Value("${model.attribute.title.name}")
    private String titleName;

    @Value("${model.attribute.title.value.default}")
    private String defaultTitle;

    @Value("${model.attribute.title.value.register}")
    private String registerTitle;

    @Value("${password.strength}")//パスワード強度
    private int strength;

}
