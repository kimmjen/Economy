import axios from 'axios';

// 공통 Axios 인스턴스 생성
const apiClient = axios.create({
    baseURL: 'http://lcoalhost:8080',
    timeout: 10000,
});

// 응답 인터셉터
apiClient.interceptors.response.use(
    (response) => response,
    (error) => {
        return Promise.reject(error);
    }
);

export default apiClient;
