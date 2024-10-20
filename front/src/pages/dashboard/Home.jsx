import React, {useEffect} from "react";
import {Typography} from "@material-tailwind/react";
import {StatisticsCard} from "@/widgets/cards";
import {useIndices} from '@/api/indices';
import {useBonds} from '@/api/bonds';
import Datepicker from "@/components/datepicker/Datepicker";
import {useDate} from "@/context/DateContext.jsx";
import {useGold} from "@/api/gold.js";
import {useDollar} from "@/api/dollar.js";

export const Home = () => {
    const {date, handleDateChange} = useDate();  // date와 handleDateChange를 가져옴
    const {indicesData, fetchIndicesData} = useIndices();
    const {bondsData, fetchBondsData} = useBonds();
    const {goldData, fetchGoldData} = useGold();
    const {dollarData, fetchDollarData} = useDollar()

    useEffect(() => {
        fetchIndicesData();  // 날짜가 변경되면 주가 지수 데이터를 가져옴
        fetchBondsData();    // 국채 데이터를 가져옴
        fetchGoldData()
        fetchDollarData()
    }, [date]);


    return (
        <div>
            <div className="flex justify-between">
                <div>
                    <Datepicker date={date} handleDateChange={handleDateChange}/> {/* 공통 Datepicker */}
                </div>
            </div>
            <div className="mt-4">
                <div className="mb-12 grid gap-y-10 gap-x-6 md:grid-cols-2 xl:grid-cols-4">
                    {indicesData.map(({icon, title, footer, ...rest}) => (
                        <StatisticsCard
                            key={title}
                            {...rest}
                            title={title}
                            icon={React.createElement(icon, {className: "w-6 h-6 text-white"})}
                            footer={
                                <Typography className="font-normal text-blue-gray-600">
                                    <strong className={footer.color}>{footer.value}</strong>&nbsp;{footer.label}
                                </Typography>
                            }
                        />
                    ))}
                    {bondsData.map(({icon, title, footer, ...rest}) => (
                        <StatisticsCard
                            key={title}
                            {...rest}
                            title={title}
                            icon={React.createElement(icon, {className: "w-6 h-6 text-white"})}
                            footer={
                                <Typography className="font-normal text-blue-gray-600">
                                    <strong className={footer.color}>{footer.value}</strong>&nbsp;{footer.label}
                                </Typography>
                            }
                        />
                    ))}

                    {dollarData && Array.isArray(dollarData) && dollarData.map(({icon, title, footer, ...rest}) => (
                        <StatisticsCard
                            key={title}
                            {...rest}
                            title={title}
                            icon={React.createElement(icon, {className: "w-6 h-6 text-white"})}
                            footer={
                                <Typography className="font-normal text-blue-gray-600">
                                    <strong className={footer.color}>{footer.value}</strong>&nbsp;{footer.label}
                                </Typography>
                            }
                        />
                    ))}

                    {goldData && Array.isArray(goldData) && goldData.map(({icon, title, footer, ...rest}) => (
                        <StatisticsCard
                            key={title}
                            {...rest}
                            title={title}
                            icon={React.createElement(icon, {className: "w-6 h-6 text-white"})}
                            footer={
                                <Typography className="font-normal text-blue-gray-600">
                                    <strong className={footer.color}>{footer.value}</strong>&nbsp;{footer.label}
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
