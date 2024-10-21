import {useDate} from "@/context/DateContext.jsx";
import {useEffect, useState} from "react";
import {ChartBarIcon} from "@heroicons/react/24/solid";

export const useGold = () => {
    const {date, handleDateChange} = useDate();  // date와 handleDateChange를 가져옴
    const [goldData, setGoldData] = useState(null);
    const url = "http://localhost:8080/api/v1"

    const fetchGoldData = async () => {
        try {
            const response = await fetch(`${url}/golds?date=${date}`);
            if (!response.ok) {
                throw new Error('Failed to fetch data');
            }
            const data = await response.json();
            const formattedData = [
                {
                    color: 'gray',
                    icon: ChartBarIcon,
                    title: 'Gold',
                    value: `$${data[0].closePrice.toFixed(2)}`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
            ];

            setGoldData(formattedData);  // 데이터를 상태에 저장
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }
    useEffect(() => {
        if (date) {
            fetchGoldData();  // 날짜가 변경되면 데이터를 가져옴
        }
    }, [date]);

    return {
        date,
        goldData,
        handleDateChange,
        fetchGoldData,
    }
}