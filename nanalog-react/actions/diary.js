import * as types from '../constants/ActionTypes';
import merge from 'lodash/merge';

// read all pages
// uid, date

export function fetchPageList(url) {
    return (dispatch, getState) => {
      return fetch('localhost:8080/v1/user/?uid=test2')
      .then(
        function(response) {
          if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
        }
      ).then(response => response.json())
      .then(json => {
        let diaryPages = null;
        let date = null;
        if(json.diaryPageGetResponseModels){
          diaryPages = json.diaryPageGetResponseModels;
        }
        if(json.date){
          date = json.date;
        }
        return {
          type: types.READ_ALL_PAGE,
          date,
          diaryPages
        }
      })
  }
}

// read month pages 랑 read weekly motn pages 필요

// read page
// uid, pageId
export function fetchPage(pageId) {
  return (dispatch, getState) => {
    return fetch('http://localhost:8000/pagedata.json')
      .then(response => response.json())
      .then(json => {
        let pageId = null;
        let diaryComponents= null;
        if(json.pageId){
          pageId = json.pageId;
        }
        if(json.diaryComponentGetResponseModels){
          diaryComponents = json.diaryComponentGetResponseModels;
        }
        return dispatch(getPage(pageId,diaryComponents));

      })
  };
}
export function getPage(pageId,diaryComponents) {
  return {
    type: types.READ_PAGE,
    pageId,
    diaryComponents
  }
}
export function fetchPreviewPage(startDate, endDate){
  return (dispatch, getState) => {
    return fetch('http://localhost:8000/previewPageData.json')
      .then(response => response.json())
      .then(json => {
        let pageList = json;
        console.log(pageList);
        return dispatch(PreviewPage(pageList));

      })
  };
}
export function PreviewPage(pageList) {
  return {
    type: types.READ_PREVIEW_PAGE,
    pageList : pageList,
    pageCount : pageList.length
  };
}
// pageId, userId, createdAt
export function createPage(){

}
//componentId,pageId,componentDate,componentType,componentPosition
export function createComponent(){

}
//pageId,componentId,componentPosition,componentData => list
export function updatePage(){

}

//pageId,componentId,componentPosition,componentData
export function updateComponent(){

}
//deletePageId,userId
export function deletePage(){

}
//UserId,componentId
export function deleteComponent(){

}
