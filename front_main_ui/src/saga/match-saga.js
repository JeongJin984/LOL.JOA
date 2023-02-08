import { all, fork } from 'redux-saga/effects';
import createSaga from "../utils/createSaga";
import { backURL } from "../../config/config";

const user_back = "http://localhost:8081"
const league_back = "http://localhost:8080"
const betting_back = "http://localhost:8083"
const db_back = "http://localhost:8082"

const watchMatch = createSaga("CALL_MATCH", backURL + "/api/league/schedule/all", "get");
const watchGetGameData = createSaga("GET_GAME_DATA", backURL + "/api/betting/game/data", "get")
const watchBetting = createSaga("BETTING", backURL + "/api/betting/game/betting", "get")
const watchSelectWinner = createSaga("SELECT_WINNER", backURL + "/api/betting/game/distribute", "post")

const watchAddTeam = createSaga("ADD_TEAM_DATA", "http://localhost:4321/team", "get")

export default function* bettingSaga() {
  yield all([
    fork(watchMatch),
    fork(watchAddTeam),
    fork(watchGetGameData),
    fork(watchBetting),
    fork(watchSelectWinner)
  ]);
}