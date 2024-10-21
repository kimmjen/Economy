import { useEffect, useState } from "react";
import dayjs from "dayjs";

export const useCommon = () => {
    const today = dayjs();  // 오늘 날짜

    // 주말이면 가장 최근 평일(금요일)을 기본 날짜로 설정하는 함수
    const getDefaultDate = () => {
        const day = today.day();
        if (day === 6) {
            return dayjs(today).subtract(1, "day").format("YYYY-MM-DD");  // 토요일 -> 금요일
        } else if (day === 0) {
            return dayjs(today).subtract(2, "day").format("YYYY-MM-DD");  // 일요일 -> 금요일
        }
        return dayjs(today).format("YYYY-MM-DD");  // 평일인 경우 오늘 날짜
    };

    const [date, setDate] = useState(getDefaultDate());  // 기본 날짜 설정

    // 날짜 변경 핸들러
    const handleDateChange = (newDate) => {
        const selectedDate = newDate.target ? newDate.target.value : newDate.format("YYYY-MM-DD");
        setDate(selectedDate);  // 선택된 날짜로 업데이트
        console.log("Updated date:", selectedDate);
    };

    // 주말 비활성화 함수
    const disableWeekends = (date) => {
        const day = date.day();
        return day === 0 || day === 6;  // 0 = 일요일, 6 = 토요일
    };


    return {
        date,
        disableWeekends,
        handleDateChange,
    };
};