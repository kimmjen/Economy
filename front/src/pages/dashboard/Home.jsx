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

    // 모든 데이터가 없는 경우 처리
    const isEmptyData = !indicesData.length && !bondsData.length && !dollarData?.length && !goldData?.length;

    return (
        <div>
            <div className="flex justify-between">
                <div>
                    <Datepicker date={date} handleDateChange={handleDateChange}/> {/* 공통 Datepicker */}
                </div>
            </div>
            <div className="mt-4">
                {/* 데이터가 없을 경우 경고 메시지 표시 */}
                {isEmptyData ? (
                    <Typography className="text-red-500 text-center">
                        미국 증시가 아직 시작되지 않았거나, 데이터가 존재하지 않습니다.
                    </Typography>
                ) : (
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
                )}
            </div>
        </div>
    );
};

export default Home;
