import { HYDRATE } from 'next-redux-wrapper';
import { combineReducers } from 'redux';

import leagueReducer from './league-reducer';
import modalReducer from "./modal-reducer";
import userReducer from "./user-reducer";
import matchReducer from './match-reducer';
import teamReducer from './team-reducer';

const rootReducer = (state, action) => {
  switch (action.type) {
    case HYDRATE:
      // Attention! This will overwrite client state! Real apps should use proper reconciliation.
      return {
        ...state,
        ...action.payload,
      };
    default: {
      const combineReducer = combineReducers({
        leagueReducer,
        modalReducer,
        userReducer,
        matchReducer,
        teamReducer
      });
      return combineReducer(state, action);
    }
  }
};

export default rootReducer;