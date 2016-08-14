package kr.co.nanalog.api.web.diary.service;

import kr.co.nanalog.api.web.diary.model.request.DiaryComponentGetRequest;
import kr.co.nanalog.api.web.diary.model.request.DiaryPageGetRequest;
import kr.co.nanalog.api.web.diary.model.request.DiaryPreviewRequest;
import kr.co.nanalog.api.web.diary.model.response.DiaryComponentGetResponse;
import kr.co.nanalog.api.web.diary.model.response.DiaryPageGetResponse;
import kr.co.nanalog.api.web.diary.model.response.DiaryPreviewResponse;

import java.util.List;

/**
 * Created by lcw on 7/26/16.
 */
public interface DiaryGetService {

    DiaryPageGetResponse getDiaryPages(DiaryPageGetRequest diaryPageGetRequest);
    DiaryComponentGetResponse getDiaryCompoents(DiaryComponentGetRequest diaryComponentGetRequest);
    List<DiaryPreviewResponse> getDiaryPreviewList(DiaryPreviewRequest diaryPreviewRequest);

}
