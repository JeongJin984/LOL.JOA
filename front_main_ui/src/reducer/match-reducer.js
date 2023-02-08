import produce from "immer";
import {
  CALL_MATCH_SUCCESS,
  CALL_MATCH_FAILURE,
  SPLIT_TEAM_NAME,
  GET_GAME_DATA_SUCCESS, BETTING_SUCCESS, BETTING_CANCELED
} from "../../config/event/eventName/matchEvent"
const initialState = {
  matchData: [],
  game: []
}

const matchReducer = (state = initialState, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case CALL_MATCH_SUCCESS:
        draft.matchData = action.data.schedules.sort((a, b) => new Date(a.startTime.split(" ")[0]) - new Date(b.startTime.split(" ")[0]));
        break;
      case CALL_MATCH_FAILURE:
        draft.message = "error";
        break;
      case GET_GAME_DATA_SUCCESS:
        let index = -1;
        draft.matchData.map((v, i) => {
          if(v.id === action.plus.leagueId) {
            index = i
          }
        })
        draft.matchData[index].details = action.data
        break;
      case BETTING_CANCELED:
        draft.matchData.map(league => {
          league.details && league.details.map(gameData => {
            if(gameData.gameId === action.plus.gameId) {
              gameData.choices.map(c => {
                if(c.choiceId === action.plus.choiceId) {
                  c.totalPoint -= action.plus.point
                }
              })
            }
          })
        })
        break
      // case SPLIT_TEAM_NAME:
      //   draft.matchData
      default:
        break;
    }
  })
}

export default matchReducer;