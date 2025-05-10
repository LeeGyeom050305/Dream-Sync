import { combineReducers } from 'redux';
import userReducer from './reducers/userSlice';

const rootReducer = combineReducers({
  root: userReducer, // userSlice의 리듀서를 'root'로 설정
});

export default rootReducer;
