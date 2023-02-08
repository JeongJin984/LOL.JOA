import { all, fork } from 'redux-saga/effects';
import createSaga from "../utils/createSaga";
import { backURL } from "../../config/config";

const user_back = "http://localhost:8081"
const league_back = "http://localhost:8080"
const betting_back = "http://localhost:8083"
const db_back = "http://localhost:8082"

const watchTest = createSaga("TEST", backURL + "/api/betting/game/data", "get");

export default function* leagueSaga() {
  yield all([
    fork(watchTest)
  ]);
}