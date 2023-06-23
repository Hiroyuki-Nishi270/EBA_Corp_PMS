package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CRUDAttachFileRepository extends CrudRepository<AttachFile, Integer> {
    //@Query("SELECT * FROM attachfile WHERE ticket_id = :number")
    //List<AttachFile> getAttachFileList(Integer number);

    List<AttachFile> findByTicketidEquals(Integer ticketId);
}
