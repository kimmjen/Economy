import { useState } from 'react';
import {BanknotesIcon, ChartBarIcon, UserPlusIcon, UsersIcon} from "@heroicons/react/24/solid/index.js";

// 날짜 선택 및 서버 상태 처리 훅
export const useIndices = () => {
    const today = new Date().toISOString().split('T')[0];  // 'YYYY-MM-DD' 형식의 현재 날짜
    const [date, setDate] = useState(today);
    const [status, setStatus] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [indicesData, setIndicesData] = useState([]);  // 주가 지수 데이터를 위한 상태

    // 날짜 변경 핸들러
    const handleDateChange = (event) => {
        setDate(event.target.value);
    };

    // 날짜 기반 상태 데이터를 가져오는 함수
    const fetchStatusByDate = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await fetch(`http://localhost:8080/api/date?date=${date}`);
            if (!response.ok) {
                throw new Error('Failed to fetch status');
            }
            const data = await response.text();
            setStatus(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    // 날짜 기반 주가 지수 데이터를 가져오는 함수
    const fetchIndicesData = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await fetch(`http://localhost:8080/api/date?date=${date}`);
            const data = await response.json();

            // 데이터를 포맷에 맞게 변환
            const formattedData = [
                {
                    color: 'gray',
                    icon: ChartBarIcon,
                    title: 'Dow Jones',
                    value: `$${data.dowData[0].closePrice.toFixed(2)}`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data.dowData[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data.dowData[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
                {
                    color: 'gray',
                    icon: UsersIcon,
                    title: 'S&P 500',
                    value: `$${data.sp500Data[0].closePrice.toFixed(2)}`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data.sp500Data[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data.sp500Data[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
                {
                    color: 'gray',
                    icon: UserPlusIcon,
                    title: 'Nasdaq 100',
                    value: `$${data.nasdaq100Data[0].closePrice.toFixed(2)}`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data.nasdaq100Data[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data.nasdaq100Data[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
                {
                    color: 'gray',
                    icon: BanknotesIcon,
                    title: 'Russell 2000',
                    value: `$${data.russell2000Data[0].closePrice.toFixed(2)}`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data.russell2000Data[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data.russell2000Data[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
            ];

            setIndicesData(formattedData);  // 데이터를 상태에 저장
        } catch (error) {
            console.error('Error fetching data:', error);
            setError(error.message);  // 에러 상태 설정
        } finally {
            setLoading(false);  // 로딩 완료
        }
    };

    return {
        date,
        status,
        indicesData,
        loading,
        error,
        handleDateChange,
        fetchStatusByDate,
        fetchIndicesData,
    };
};
