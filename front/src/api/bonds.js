import {useEffect, useState} from "react";
import {ChartBarIcon, BanknotesIcon} from "@heroicons/react/24/solid";
import {useCommon} from "@/api/common.js";
import {useDate} from "@/context/DateContext.jsx";

export const useBonds = () => {
    const {date, handleDateChange} = useDate();  // date와 handleDateChange를 가져옴
    const [bondsData, setBondsData] = useState([]);
    const url = import.meta.env.VITE_API_URL;

    const fetchBondsData = async () => {
        console.log('bondsData', date);
        try {
            const response = await fetch(`${url}/bonds?date=${date}`);
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
                    title: '2-Year Treasury Yield',
                    value: `${data.treasury2yrData[0].closePrice.toFixed(2)}%`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data.treasury2yrData[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data.treasury2yrData[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
                {
                    color: 'gray',
                    icon: BanknotesIcon,
                    title: '10-Year Treasury Yield',
                    value: `${data.treasury10yrData[0].closePrice.toFixed(2)}%`,  // 배열의 첫 번째 요소
                    footer: {
                        color: data.treasury10yrData[0].changePercentage >= 0 ? 'text-green-500' : 'text-red-500',
                        value: `${data.treasury10yrData[0].changePercentage.toFixed(3)}%`,
                        label: 'than last close',
                    },
                },
            ];

            setBondsData(formattedData);  // 데이터를 상태에 저장
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        if (date) {
            fetchBondsData();  // 날짜가 변경되면 데이터를 가져옴
        }
    }, [date]);

    return {
        date,
        bondsData,
        handleDateChange,
        fetchBondsData
    };
};
