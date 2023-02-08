import React, { useCallback, useEffect, useState } from 'react'
import styles from '../../styles/Match.Betline.module.css';
import { useDispatch, useSelector } from "react-redux";
import { teamSelectRequest, TEAM_SELECT_REQUEST, addTeamData, DELETE_TEAM_DATA } from '../../config/event/eventName/matchEvent';

const MatchBetline = ({ matchData }) => {

  // const { teamData } = useSelector(state => state.teamReducer)
  const dispatch = useDispatch()
  const onAddTeamData = (data) => dispatch(addTeamData(data))

  const leftClick = (item) => {
    const leftTeamData = { "id": item.id, "startTime": item.startTime, "team": item.leagueName.split("vs")[0] }
    onAddTeamData(leftTeamData)
    console.log({ leftTeamData })
  }

  const rightClick = (item) => {
    const rightTeamData = { "id": item.id, "startTime": item.startTime, "team": item.leagueName.split("vs")[1] }
    onAddTeamData(rightTeamData)
    console.log({ rightTeamData })
  }


  // document.querySelector('#styles.leftMatch').classList.toggle()

  const onClickRaw = () => {
    dispatch
  }

  return (
    <div>
      {matchData.map((item, i) => (
        <div id={item.id} key={item.id} className={styles.betlineWide}>
          <div className={styles.tournament}>
            <img src={'/image/LCK.png'} style={{ height: "25px" }} />
            <div>LOL Champions Korea</div>
          </div>
          <div className={styles.matchup}>
            <button className={styles.leftMatch} onClick={() => leftClick(item)}>
              <img src={"/image/" + item.leagueName.split("vs")[0] + ".png"} style={{ height: "25px" }}/>
              <div className={styles.leftTeam} >{item.leagueName.split("vs")[0]}</div>
              <div className={styles.leftOdds} >{item.leftOdds}</div>
            </button>
            <div className={styles.middle}>VS</div>
            <button className={styles.rightMatch} onClick={() => rightClick(item)}>
              <div className={styles.rightOdds} >{item.rightOdds}</div>
              <div className={styles.rightTeam}>{item.leagueName.split("vs")[1]}</div>
              <img src={"/image/" + item.leagueName.split("vs")[1] + ".png"} style={{ height: "25px" }}/>
            </button>
          </div>
          <div className={styles.betlineDate}>{item.startTime}</div>
        </div>
      ))}
    </div>
  )
}



export default MatchBetline