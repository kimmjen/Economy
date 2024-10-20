import React, {useEffect, useState} from "react";
import {
    Typography,
    Card,
    CardHeader,
    CardBody,
    IconButton,
    Menu,
    MenuHandler,
    MenuList,
    MenuItem,
    Avatar,
    Tooltip,
    Progress,
} from "@material-tailwind/react";
import {StatisticsCard} from "@/widgets/cards";
import { useIndices } from '@/api/indices';  // indices.js에서 훅을 불러옴
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';

export const Home = () => {
    const {
        date,
        status,
        indicesData,
        loading,
        error,
        handleDateChange,
        fetchStatusByDate,
        fetchIndicesData,
    } = useIndices();
    useEffect(() => {
        if (date) {
            fetchIndicesData();  // 날짜가 변경되면 주가 지수 데이터를 가져옴
        }
    }, [date]);
    return (
        <div>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker
                    label="Select a date"
                    value={dayjs(date)}  // date 값을 dayjs 객체로 변환하여 설정
                    onChange={(newValue) => {
                        handleDateChange({ target: { value: newValue.format('YYYY-MM-DD') } });  // 날짜를 'YYYY-MM-DD' 형식으로 변환하여 handleDateChange에 전달
                    }}
                    renderInput={(params) => <input {...params} />}
                />
            </LocalizationProvider>

            <div className="mt-12">
                <div className="mb-12 grid gap-y-10 gap-x-6 md:grid-cols-2 xl:grid-cols-4">
                    {indicesData.map(({icon, title, footer, ...rest}) => (
                        <StatisticsCard
                            key={title}
                            {...rest}
                            title={title}
                            icon={React.createElement(icon, {
                                className: "w-6 h-6 text-white",
                            })}
                            footer={
                                <Typography className="font-normal text-blue-gray-600">
                                    <strong className={footer.color}>{footer.value}</strong>
                                    &nbsp;{footer.label}
                                </Typography>
                            }
                        />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Home;
