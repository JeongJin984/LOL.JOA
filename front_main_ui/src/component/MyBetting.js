import React, { useCallback } from "react";
import styles from '../../styles/MyBetting.module.css';
import { useSelector, useDispatch } from "react-redux";
import { BETTING_CANCELED, CANCEL_BETTING_REQUEST } from "../../config/event/eventName/matchEvent";


const MyBetting = (userData) => {
  const dispatch = useDispatch()

  const { user } = useSelector(state => state.userReducer)

  const onClickCancel = useCallback((gameId, choiceId, point) => () => {
    dispatch({
      type: CANCEL_BETTING_REQUEST,
      params: {
        choiceId,
        accountId: user.accountId,
        gameId
      },
      plus: {
        gameId,
        choiceId,
        point: parseInt(point)
      }
    })

    dispatch({
      type: BETTING_CANCELED,
      plus: {
        gameId,
        choiceId,
        point
      }
    })
  }, [userData])

  return (
    <div>
      {userData.user.bettingData?.map((item, i) => (
        <div className={styles.bettingWide} >
          <div className={styles.matchData} >
            <img src={'/image/LCK.png'} style={{ height: "15px" }} />
            <div>{item.leftTeam} 대 {item.rightTeam}</div>
          </div>
          <div className={styles.predictWide}>
            <img className={styles.logo} src={"/image/" + item.choice + ".png"} />
            <div className={styles.choice}>
              <div style={{ display: "flex", fontSize: "20px" }}>{item.choice}</div>
              <div style={{ display: "flex", fontSize: "10px", color: "#F6BE23" }}>예측</div>
            </div>
            <div className={styles.odds}>{item.odd}</div>
          </div>
          <div className={styles.pintWide}>
            <div className={styles.bettingPoint}>{item.point}P 베팅</div>
            <div className={styles.rewardPoint}>{Math.round(Number(item.point) * Number(item.odd))}P 예상</div>
          </div>
          <button onClick={onClickCancel(item.gameId, item.choiceId, item.point)}>취소</button>
        </div>
      ))}
    </div>
  )
}

export default MyBetting