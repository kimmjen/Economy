import axios from 'axios';

// 공통 Axios 인스턴스 생성
const apiClient = axios.create({
    baseURL: 'http://lcoalhost:8080',
    timeout: 10000,
});

// 요청 인터셉터
// apiClient.interceptors.request.use(
//     (config) => {
//         // 요청 전에 인증 토큰 등을 설정할 수 있음
//         const token = localStorage.getItem('token');
//         if (token) {
//             config.headers['Authorization'] = `Bearer ${token}`;
//         }
//         return config;
//     },
//     (error) => {
//         return Promise.reject(error);
//     }
// );

// 응답 인터셉터
apiClient.interceptors.response.use(
    (response) => response,
    (error) => {
        return Promise.reject(error);
    }
);

export default apiClient;
