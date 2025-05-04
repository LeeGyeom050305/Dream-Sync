import axios from 'axios'
import {clearAllAndRedirectToLogin, getAccessToken} from "./UserUtil";
import {noData} from "./CommonUtil";


axios.defaults.withCredentials = true
const instance = axios.create({
    headers: {
        //'Pragma': 'no-cache',
        withCredentials: true
    }
})
const API = () => {
    return instance
}
export const MULTIPART_CONTENT_TYPE = {
    headers: {"Content-Type": "multipart/form-data"}
}

export function initAxios() {
    instance.interceptors.request.use((config) => {
        // storage에서 token 가져오기
        const token = getAccessToken();
        // uri logging
        // console.log(config.method, config.url, config.params, config.data);

        if (!noData(token)) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }

        return config
    }, (error) => {
        return Promise.reject(error)
    })
    instance.interceptors.response.use(
        (res) => {
            return {
                ...res
            }
        },
        (error) => {
            if (error.response.status === 401) {
                alert(error.response?.data?.errorMessage || "session expired. please login again.");
                clearAllAndRedirectToLogin();
            }/* else {
                let message = null;
                if (error.response.data)
                    message = error.response.data;
                console.log(message)
                console.log(error.message)
                console.log(error)
                alert(message !== null ? message.body?.message : error?.message);
            }*/
            return Promise.reject(error)
        }
    )
}

export default API