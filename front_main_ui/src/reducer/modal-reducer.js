import produce from "immer";

const initialState = {
}

const modalReducer = (state = initialState, action) => {
  return produce(state, draft => {
    switch (action.type) {
      default:
        break;
    }
  })
}

export default modalReducer;