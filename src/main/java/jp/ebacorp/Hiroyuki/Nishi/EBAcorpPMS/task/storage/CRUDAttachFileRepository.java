package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CRUDAttachFileRepository extends CrudRepository<AttachFileEntity, Integer> {

    List<AttachFileEntity> findByTicketidEquals(Integer ticketId);
    boolean existsByTicketidAndFilename(Integer ticketId, String filename);
}
