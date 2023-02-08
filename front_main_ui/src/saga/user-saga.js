import { all, fork } from 'redux-saga/effects';
import createSaga from "../utils/createSaga";
import { backURL } from "../../config/config";

const user_back = "http://localhost:8081"
const league_back = "http://localhost:8080"
const betting_back = "http://localhost:8083"
const db_back = "http://localhost:8082"

const watchLogin = createSaga("LOGIN", backURL + "/api/account/login", "get");
const watchGetUser = createSaga("GET_USER", backURL + "/api/betting/game/user", "get");
const watchCancelBetting = createSaga("CANCEL_BETTING", backURL + "/api/betting/game/cancel", "post")

export default function* userSaga() {
  yield all([
    fork(watchLogin),
    fork(watchGetUser),
    fork(watchCancelBetting)
  ]);
}