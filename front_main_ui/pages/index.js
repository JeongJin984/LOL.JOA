import Axios from 'axios';
import Betline from '../src/component/Betline';
import wrapper from "../src/store/store-wrapper";
import { END } from "redux-saga";
import { useDispatch, useSelector } from 'react-redux';
import { CALL_MATCH_REQUEST } from '../config/event/eventName/matchEvent'
import MyBetting from '../src/component/MyBetting';
import { GET_USER_REQUEST } from "../config/event/eventName/userEvent";

const Home = () => {
  const dispatch = useDispatch()
  const { matchData } = useSelector(state => state.matchReducer)
  const { user } = useSelector(state => state.userReducer)
  console.log({ user })
  return (
    <div style={{ display: "flex", background: "#f6f8fa" }}>
      <div style={{ flex: 4 }}>
        <div style={{ textAlign: "center", margin: "30px" }}>
          <h2>League of Legends
            /
            Most Popular</h2>
        </div>
        <div style={{ textAlign: "center", margin: "30px" }}>
          <Betline matchData={matchData} />
        </div>
      </div>
      <div style={{ color: "white", flex: 1, backgroundColor: "#242737" }}>

        <div style={{ display: "flex", flexDirection: "column", textAlign: "center", margin: "10px" }}>
          <div style={{ fontSize: "22px", marginBottom: "10px" }}>내 베팅</div>
          <MyBetting user={user} />
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


export default Home;