import Cookies from "js-cookie";
import * as uri from "../consts/uri";

export function getAccessToken() {
    return Cookies.get("accessToken");
}

export function clearAllAndRedirectToLogin() {
    localStorage.clear();
    sessionStorage.clear();
    Cookies.remove("accessToken");
    window.location.href = "#" + uri.login;
}

export function getUser() {
    let user = {};

    try {
        const userData = localStorage.getItem('user');

        if (userData) {
            user = JSON.parse(userData);
        } else {
            if (window.location.hash.substring(1) !== uri.login) {
                alert("User information not found. Please log in again.");
            }
            clearAllAndRedirectToLogin();
        }
    } catch (error) {
        console.error("JSON parsing error:", error);

        // login uri가 아닐 경우 alert
        if (window.location.hash.substring(1) !== uri.login) {
            alert("An error occurred while loading the user information. Please log in again.");
        }
        clearAllAndRedirectToLogin();
    }

    return user;
}