// AuthContext.js
import {createContext, useContext} from "react";
import Cookies from "js-cookie";
import {noData} from "./utils/CommonUtil";
import {clearAllAndRedirectToLogin} from "./utils/UserUtil";
import {useDispatch} from "react-redux";

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const dispatch = useDispatch();
    // 쿠키에서 로그인 상태를 가져옴
    const isAuthenticated = () => !noData(Cookies.get("accessToken"));


    // 로그아웃
    const logout = () => {
        dispatch({
            type: "RESET_STORE",
        });
        clearAllAndRedirectToLogin();
    };

    const init = async () => {
        if (!isAuthenticated()) {
            return;
        }
    }
    init();

    return (
        <AuthContext.Provider value={{isAuthenticated, logout}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
