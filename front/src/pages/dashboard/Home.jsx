import React, {useEffect, useState} from "react";
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
    // const liveStreamId = 't_rODNJku-I'; // Example live stream ID
    // const embedUrl = `https://www.youtube.com/embed/${liveStreamId}?autoplay=1`;
    const [isLive, setIsLive] = useState(false); // Simulating live status (false for not live)

    const channelId = 'UC_JJ_NhRqPKcIOj5Ko3W_3w'; // Replace with actual channel ID
    const embedUrl = `https://www.youtube.com/embed/live_stream?channel=${channelId}&autoplay=1`;
    return (
        <div>
            <div className="stream-wrapper"
                 style={{display: 'flex', justifyContent: 'center', alignItems: 'center', padding: '20px'}}>
                <div className="stream-card" style={{
                    display: 'flex',
                    width: '900px',
                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
                    borderRadius: '8px',
                    overflow: 'hidden'
                }}>
                    {/* Stream Video */}
                    <div className="stream-video" style={{flex: 3, position: 'relative'}}>
                        <div style={{position: 'relative', paddingBottom: '56.25%', height: 0, overflow: 'hidden'}}>
                            <iframe
                                title="YouTube Live Stream"
                                src={embedUrl}
                                frameBorder="0"
                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                allowFullScreen
                                style={{position: 'absolute', top: 0, left: 0, width: '100%', height: '100%'}}
                            />
                        </div>

                        {/* Live Badge or Not Live Message */}
                        {isLive ? (
                            <span style={{
                                position: 'absolute',
                                top: '10px',
                                left: '10px',
                                backgroundColor: 'red',
                                color: 'white',
                                padding: '5px 10px',
                                borderRadius: '8px'
                            }}>
                            🔴 생방송
                        </span>
                        ) : (
                            <div style={{
                                position: 'absolute',
                                top: '10px',
                                left: '10px',
                                backgroundColor: 'gray',
                                color: 'white',
                                padding: '5px 10px',
                                borderRadius: '8px'
                            }}>
                                현재 방송중이 아닙니다.
                            </div>
                        )}
                    </div>

                    {/* Stream Information */}
                    {/*<div className="stream-info" style={{flex: 2, padding: '20px', backgroundColor: '#f9f9f9'}}>*/}
                    {/*    <h2 style={{margin: 0}}>리트밍</h2>*/}
                    {/*    <p>포트나이트</p>*/}
                    {/*    <p>시청자 15명</p>*/}
                    {/*    <div style={{display: 'flex', gap: '10px', margin: '10px 0'}}>*/}
                    {/*        <span style={{*/}
                    {/*            backgroundColor: '#efefef',*/}
                    {/*            padding: '5px 10px',*/}
                    {/*            borderRadius: '8px'*/}
                    {/*        }}>한국어</span>*/}
                    {/*        <span style={{*/}
                    {/*            backgroundColor: '#efefef',*/}
                    {/*            padding: '5px 10px',*/}
                    {/*            borderRadius: '8px'*/}
                    {/*        }}>korean</span>*/}
                    {/*    </div>*/}
                    {/*    <p style={{color: '#555'}}>게임 좋아하는 수학선생님의 엉뚱한 방송! [시청하기]</p>*/}
                    {/*</div>*/}
                </div>
            </div>
            <div className="flex justify-between">
                <div>
                    {/*<Datepicker date={date} handleDateChange={handleDateChange}/> /!* 공통 Datepicker *!/*/}
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
