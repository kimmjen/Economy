import { useState, useEffect } from 'react';

export const useStatus = () => {
    const [statusData, setStatusData] = useState(null);  // 서버 상태 데이터를 저장
    const [connected, setConnected] = useState(false);  // 연결 상태를 저장
    const [loading, setLoading] = useState(true);  // 로딩 상태를 저장
    const [error, setError] = useState(null);  // 에러 상태를 저장

    useEffect(() => {
        const fetchStatus = async () => {
            try {
                // Spring Boot 백엔드의 /api/status로 GET 요청 보내기
                const response = await fetch('http://localhost:8080/api/status');
                if (!response.ok) {
                    throw new Error('Failed to fetch status');
                }
                const data = await response.json();  // JSON 데이터로 파싱

                // status 값이 "OK"이면 연결 상태를 true로 설정
                if (data.status === "OK") {
                    setConnected(true);
                } else {
                    setConnected(false);  // "OK"가 아닐 때 연결되지 않음으로 설정
                }

                setStatusData(data);  // 전체 데이터를 저장
            } catch (err) {
                setConnected(false);  // 에러 발생 시 연결 상태를 false로 설정
                // setError(`Error fetching status: ${err.message}`);  // 에러 발생 시 메시지 설정
            } finally {
                setLoading(false);  // 로딩 완료
            }
        };

        fetchStatus();  // 함수 호출하여 상태 가져오기
    }, []);

    return { statusData, connected, loading, error };  // 상태 반환
};
