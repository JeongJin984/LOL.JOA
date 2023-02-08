import produce from "immer";
import { GET_USER_SUCCESS, LOGIN_SUCCESS } from "../../config/event/eventName/userEvent";
import {BETTING_SUCCESS, CANCEL_BETTING_SUCCESS} from "../../config/event/eventName/matchEvent";

const initialState = {
  user: {},
  bettingGameList: []
}

const userReducer = (state = initialState, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case LOGIN_SUCCESS:
        draft.user = action.data
        draft.user.bettingData.map(v => draft.bettingGameList.push(v.gameId))
        break
      case GET_USER_SUCCESS:
        draft.user = action.data
        draft.user.bettingData.map(v => draft.bettingGameList.push(v.gameId))
        break
      case BETTING_SUCCESS:
        draft.user.bettingData.push(action.data)
        draft.bettingGameList.push(action.plus.gameId)
        draft.user.point -= action.plus.point
        break
      case CANCEL_BETTING_SUCCESS:
        let index = -1
        let index2 = -1
        draft.user.bettingData.map((v, i) => {
          if(v.choiceId === action.plus.choiceId) {
            index = i
          }
        })
        draft.user.bettingData.splice(index, 1)
        draft.user.point += action.plus.point

        draft.bettingGameList.map((v, i) => {
          if(v === action.plus.gameId) {
            index2 = i
          }
        })
        draft.bettingGameList.splice(index2, 1)
        break
      default:
        break;
    }
  })
}

export default userReducer;