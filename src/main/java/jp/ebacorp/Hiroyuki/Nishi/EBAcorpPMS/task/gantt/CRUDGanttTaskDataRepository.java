package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CRUDGanttTaskDataRepository extends CrudRepository<GanttTaskData, Integer> {
    public List<GanttTaskData> findAllByOrderByStart();
}
