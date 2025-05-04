import API from "../utils/API";
import Cookies from "js-cookie";

export const refreshUserInfo = async (callback) => {
    const res = await API().get('/users');
    localStorage.setItem("user", JSON.stringify(res.data));

    if (callback) {
        callback(res.data);
    }
    return res.data;
}

export const loginApi = async (loginInfo, callback) => {
    const res = await API().post('/users/login', loginInfo);
    Cookies.set("accessToken", res.data);

    await refreshUserInfo();

    if (callback) {
        callback(res.data);
    }
}