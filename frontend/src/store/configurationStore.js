import {combineReducers, configureStore} from '@reduxjs/toolkit';
import {initAxios} from '../utils/API';
import rootReducer from "../rootReducer";
import {FLUSH, PAUSE, PERSIST, persistReducer, persistStore, PURGE, REGISTER, REHYDRATE,} from 'redux-persist';
import storage from "redux-persist/lib/storage";

const appReducer = combineReducers({
    root: rootReducer,
});

const persistConfig = {
    key: "root",
    // localStorage에 저장
    storage,
};

// Axios 초기화 
initAxios();

const RootReducer = (state, action) => {
    if (action.type === "RESET_STORE") {
        return appReducer(undefined, action);
    }
    return appReducer(state, action);
}

const store = configureStore({
    reducer: persistReducer(persistConfig, RootReducer),
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware({
            serializableCheck: {
                // redux-persist 관련 액션 예외 처리
                ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
            },
        }),
    devTools: process.env.NODE_ENV !== 'production', // 개발 모드에서만 Redux DevTools 활성화
});

// persistor 생성
export const persistor = persistStore(store);
export default store;
