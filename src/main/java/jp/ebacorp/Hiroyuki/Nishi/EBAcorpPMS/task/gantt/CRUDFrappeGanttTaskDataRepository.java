package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.gantt;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CRUDFrappeGanttTaskDataRepository extends CrudRepository<FrappeGanttTaskData, Integer> {
    public List<FrappeGanttTaskData> findAllByOrderByStart();
    public List<FrappeGanttTaskData> findAllByOrderByStartAscEndAsc();

}
