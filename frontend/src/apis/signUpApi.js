import API from "../utils/API";

/**
 * 회원가입 API
 * @param {Object} params - 회원가입 정보 (username, password, email 등)
 * @param {Function} [callback] - 성공 시 실행할 콜백 (선택적)
 * @returns {Promise<any>} - API 응답 데이터
 */
export const signUpApi = async (params, callback) => {
    try {
        const res = await API().post('/users/signUp', params);

        if (callback) {
            callback(res.data);
        }

        return res.data;
    } catch (error) {
        const data = error.response?.data;
    
        let message = "회원가입 중 오류가 발생했습니다.";
    
        if (data?.detailMessage) {
            // 한글 메시지를 추출 (예: detailMessage 안에 포함된 유효성 메시지)
            const matches = data.detailMessage.match(/default message \[(.*?)\]/g);
            if (matches && matches.length > 0) {
                // 마지막 메시지를 추출 (가장 유저에게 적절한 메시지일 가능성 높음)
                const lastMessage = matches[matches.length - 1];
                const extracted = lastMessage.match(/\[(.*?)\]/);
                if (extracted && extracted[1]) {
                    message = extracted[1];
                }
            } else {
                message = data.detailMessage;
            }
        } else if (data?.errorMessage) {
            message = data.errorMessage;
        }
    
        throw new Error(message);
    }    
};
