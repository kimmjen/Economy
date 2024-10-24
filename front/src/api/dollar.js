import { useDate } from "@/context/DateContext.jsx";
import { useEffect, useState } from "react";
import { BanknotesIcon, ChartBarIcon } from "@heroicons/react/24/solid";

export const useDollar = () => {
    const { date, handleDateChange } = useDate();  // date와 handleDateChange를 가져옴
    const [dollarData, setDollarData] = useState(null);  // 달러 데이터를 위한 상태
    const url = import.meta.env.VITE_API_URL;;

    const fetchDollarData = async () => {
        try {
            const response = await fetch(`${url}/dollars?date=${date}`);
            if (!response.ok) {
                throw new Error('Failed to fetch data');
            }
            const data = await response.json();
            console.log('Fetched data:', data);

            // 데이터를 포맷에 맞게 변환
            const formattedData = [
                {
                    color: 'gray',
                    icon: ChartBarIcon,
                    title: 'Dollar Index',
                    value: `$${data[0].closePrice.toFixed(2)}`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
            ];

            setDollarData(formattedData);  // 데이터를 상태에 저장
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        if (date) {
            fetchDollarData();  // 날짜가 변경되면 데이터를 가져옴
        }
    }, [date]);

    return {
        date,
        dollarData,  // 달러 데이터 반환
        handleDateChange,
        fetchDollarData,
    };
};
