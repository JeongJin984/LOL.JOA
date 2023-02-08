import produce from "immer";
import { TEST_FAILURE, TEST_SUCCESS } from "../../config/event/eventName/test";
import { GET_GAME_DATA_SUCCESS } from "../../config/event/eventName/matchEvent";

const initialState = {
  message: "Click!"
}

const leagueReducer = (state = initialState, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case TEST_SUCCESS:
        draft.message = action.data;
        break;
      case TEST_FAILURE:
        draft.message = "error";
        break;
      case GET_GAME_DATA_SUCCESS:
        break;
      default:
        break;
    }
  })
}

export default leagueReducer;