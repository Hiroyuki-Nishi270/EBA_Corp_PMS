package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CRUDAttachFileRepository extends CrudRepository<AttachFile, Integer> {

    List<AttachFile> findByTicketidEquals(Integer ticketId);
}
