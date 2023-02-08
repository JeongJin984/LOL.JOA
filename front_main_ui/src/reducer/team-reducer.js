import produce from "immer";
import { ADD_TEAM_DATA, DELETE_TEAM_DATA, } from "../../config/event/eventName/matchEvent"


const initialState = {
  teamData: []
}

const teamReducer = (state = initialState, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case ADD_TEAM_DATA:
        let newData = action.payload;
        // const index = Result.findIndex( (element) => element.grade === 'B');
        let ifSameId = draft.teamData.findIndex((element) => element.id === newData["id"]) > 0;
        let ifSameValue = draft.teamData.findIndex((element) => element.team === newData["team"] && element.id === newData["id"]) > 0;
        if (ifSameId) {
          if (ifSameValue) {
            draft.teamData = draft.teamData.filter((item) => item.id !== newData["id"]);
          }
          else {
            draft.teamData = draft.teamData.filter((item) => item.id !== newData["id"]);
            draft.teamData.push(action.payload);
          }
        }
        else {
          draft.teamData.push(action.payload);
        }
        break;
      default:
        break;
    }
  })
}

export default teamReducer;