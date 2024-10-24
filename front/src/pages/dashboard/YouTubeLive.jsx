import React, { useEffect, useState, useCallback } from 'react';
import axios from "axios";
import './YouTubeLive.css'; // 별도의 CSS 파일로 스타일을 관리

const YouTubeLive = () => {
    const [isLive, setIsLive] = useState(true);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [embedUrl, setEmbedUrl] = useState('');

    const channelId = import.meta.env.VITE_CHANNEL_ID;
    const API_KEY = import.meta.env.VITE_API_KEY;

    const checkLiveStatus = useCallback(async () => {
        try {
            setLoading(true); // 로딩 상태 시작
            const response = await axios.get(
                `https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=${channelId}&eventType=live&type=video&key=${API_KEY}`
            );
            const liveStatus = response.data.items.length > 0;
            if (liveStatus) {
                setEmbedUrl(`https://www.youtube.com/embed/live_stream?channel=${channelId}&autoplay=1`);
                setIsLive(true);
            } else {
                setIsLive(false);
            }
            setError(null); // 에러 초기화
        } catch (error) {
            setError("Failed to fetch live stream status. Please try again later.");
        } finally {
            setLoading(false); // 로딩 상태 종료
        }
    }, [API_KEY, channelId]);
    useEffect(() => {
        // 컴포넌트 마운트 시 라이브 상태 체크
        checkLiveStatus();

        // isLive가 false일 때만 주기적으로 상태를 체크
        if (!isLive) {
            const intervalId = setInterval(() => {
                checkLiveStatus();
            }, 60000);

            return () => clearInterval(intervalId); // 컴포넌트 언마운트 시 interval 정리
        }

        // isLive가 true일 때는 더 이상 interval 설정을 하지 않음
    }, [checkLiveStatus, isLive]); // isLive 의존성을 추가
    // useEffect(() => {
    //     // 컴포넌트 마운트 시 라이브 상태 체크
    //     checkLiveStatus();
    //
    //     // 1분마다 상태 체크
    //     const intervalId = setInterval(() => {
    //         checkLiveStatus();
    //     }, 60000);
    //
    //     return () => clearInterval(intervalId); // 컴포넌트 언마운트 시 interval 정리
    // }, [checkLiveStatus]);

    if (loading) {
        return <div className="loader">Loading...</div>; // 로딩 중인 상태에 스피너 제공
    }

    if (error) {
        return (
            <div className="error-message">
                <p>{error}</p>
                <button onClick={checkLiveStatus}>Retry</button> {/* 다시 시도 버튼 제공 */}
            </div>
        );
    }

    return (
        <div className="stream-wrapper">
            <div className="stream-card">
                {isLive ? (
                    <div className="live-stream">
                        <iframe
                            title="YouTube Live Stream"
                            src={embedUrl}
                            frameBorder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowFullScreen
                        />
                        <div className="live-badge">
                            <span>🔴 Live</span>
                        </div>
                    </div>
                ) : (
                    <div className="offline">
                        <img
                            src="https://via.placeholder.com/640x360.png?text=Stream+is+currently+offline"
                            alt="Stream offline"
                        />
                        <p>현재 방송 중이지 않습니다.</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default YouTubeLive;
