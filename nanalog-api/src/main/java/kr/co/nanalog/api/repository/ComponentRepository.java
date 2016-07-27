package kr.co.nanalog.api.repository;

import kr.co.nanalog.api.entity.ComponentPosition;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by choijinjoo on 2016. 7. 27..
 */
public interface ComponentRepository {

    @Query("delete from Component c " +
            "where c.pageId = ?1")
    void deleteComponentBypageId(Long pageId);

    // deleteComponentByComponentId도 필요할 것 같네요!! -진주

    @Query("update Component c " +
            "set c.componentPosition = ?2 c.componentData =?3 where c.pageId = ?1")
    void updateComponentByComponentId(Long pageId, ComponentPosition componentPosition, String componentData);

}