import React, { createContext, useState, useContext } from 'react';
import dayjs from 'dayjs';

const DateContext = createContext();

export const DateProvider = ({ children }) => {

    // getDefaultDate를 함수 정의 전에 사용하지 않도록 위치를 변경
    const getDefaultDate = () => {
        const today = dayjs();
        const day = today.day();
        if (day === 6) {
            return dayjs(today).subtract(1, 'day').format('YYYY-MM-DD');  // 토요일 -> 금요일
        } else if (day === 0) {
            return dayjs(today).subtract(2, 'day').format('YYYY-MM-DD');  // 일요일 -> 금요일
        }
        return dayjs(today).format('YYYY-MM-DD');  // 주말이 아니면 오늘 날짜
    };

    const [date, setDate] = useState(getDefaultDate());  // 기본 날짜 설정

    const handleDateChange = (newDate) => {
        setDate(newDate.target.value);
    };

    return (
        <DateContext.Provider value={{ date, handleDateChange }}>
            {children}
        </DateContext.Provider>
    );
};

export const useDate = () => {
    return useContext(DateContext);
};
