package kr.co.nanalog.api.web.diary.service;

import kr.co.nanalog.api.web.diary.model.entity.Component;
import kr.co.nanalog.api.web.diary.model.entity.Page;
import kr.co.nanalog.api.web.diary.repository.ComponentRepository;
import kr.co.nanalog.api.web.diary.repository.PageRepository;
import kr.co.nanalog.api.web.diary.model.request.DiaryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by choijinjoo on 2016. 7. 30..
 */
@Service
@Transactional
public class DiaryUpdateServiceImpl implements DiaryUpdateService {

    @Autowired
    PageRepository pageRepository;
    @Autowired
    ComponentRepository componentRepository;

    @Override
    public Integer updateComponent(DiaryUpdateRequest diaryUpdateRequest) {
//        if (diaryUpdateRequest.getComponentId() == null || diaryUpdateRequest.getPageId() == null) {
//            return -1;
//        }
        Page page = pageRepository.findByPageId(diaryUpdateRequest.getPageId());

        if(page == null){
            return -1;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        page.setModifiedDate(format.format(new Date()));

        pageRepository.save(page);


        Component component = componentRepository.findByComponentId(diaryUpdateRequest.getComponentId());
        if(component == null){
            return -1;
        }
        component.setComponentPosition(diaryUpdateRequest.getComponentPosition());
        component.setComponentData(diaryUpdateRequest.getComponentData());

        componentRepository.save(component);


        return 1;
    }

    @Override
    public Integer updateDiary(ArrayList<DiaryUpdateRequest> diaryUpdateRequest) {
        Page page = pageRepository.findByPageId(diaryUpdateRequest.get(0).getPageId());
        ArrayList<Component> components = componentRepository.getComponentsByPageId(page.getPageId());
        // ArrayList<Component> updatedComponents = diaryUpdateRequest.getComponents();
        ArrayList<Component> deletedComponents = new ArrayList<>();

        if (page == null) {
            return -1;
        }
        // delete될 컴포넌트 find
        for (Component component : components) {
            for (int i = 0; i < diaryUpdateRequest.size(); i++) {
                DiaryUpdateRequest updatedComponent = diaryUpdateRequest.get(i);
                if (!component.getComponentId().equals(updatedComponent.getComponentId())) {
                    if (i == diaryUpdateRequest.size() - 1) {
                        deletedComponents.add(component);
                    }
                    continue;
                } else {
                    break;
                }
            }
        }
        //delete component
        if (deletedComponents.size() > 0) {
            for (Component deletedComponent : deletedComponents) {
                //componentRepository.deleteComponentByComponentId(deletedComponent.getComponentId());
            }
        }
        // update or create
        for (DiaryUpdateRequest updatedComponent : diaryUpdateRequest) {
            Component component = componentRepository.findByComponentId(updatedComponent.getComponentId());
            //create
            if (component == null) {
                // componentRepository.create(updatedComponent);
            } else { //update
                component.setComponentPosition(updatedComponent.getComponentPosition());
                component.setComponentData(updatedComponent.getComponentData());
                componentRepository.save(component);
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        page.setModifiedDate(format.format(new Date()));
        pageRepository.save(page);

        return 1;
    }
}
