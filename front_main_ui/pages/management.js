import Axios from 'axios';
import ManagementLine from '../src/component/ManagementLine';
import wrapper from "../src/store/store-wrapper";
import { END } from "redux-saga";
import { useDispatch, useSelector } from 'react-redux';
import { CALL_MATCH_REQUEST } from '../config/event/eventName/matchEvent'
import { GET_USER_REQUEST } from "../config/event/eventName/userEvent";

const Management = () => {
  const dispatch = useDispatch()
  const { matchData } = useSelector(state => state.matchReducer)
  const { user } = useSelector(state => state.userReducer)
  console.log({ user })
  return (
    <div style={{ display: "flex", background: "#f6f8fa" }}>
      <div style={{ flex: 4 }}>
        <div style={{ textAlign: "center", margin: "30px" }}>
          <h2>경기 결과</h2>
        </div>
        <div style={{ textAlign: "center", margin: "30px" }}>
          <ManagementLine matchData={matchData} />
        </div>
      </div>
    </div >
  )
}

export const getServerSideProps = wrapper.getServerSideProps(store =>
  async ({ req, res, ...etc }) => {
    const cookie = req ? req.headers.cookie : '';
    Axios.defaults.headers.Cookie = '';
    Axios.defaults.withCredentials = true;
    // if (req) {
    store.dispatch({
      type: CALL_MATCH_REQUEST
    })
    // }

    if (cookie) {
      Axios.defaults.headers.Cookie = cookie;
      store.dispatch({
        type: GET_USER_REQUEST
      })
    }
    store.dispatch(END);
    await store.sagaTask.toPromise();
  }
);


export default Management;