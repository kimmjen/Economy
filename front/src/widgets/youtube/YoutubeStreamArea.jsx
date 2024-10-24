import React, { useEffect, useState } from 'react';
import axios from "axios";

const YouTubeLive = () => {
    const [isLive, setIsLive] = useState(true);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [embedUrl, setEmbedUrl] = useState('');  // Use state for embedUrl


    const checkLiveStatus = async () => {
        try {
            const response = await axios.get(
                `https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=${channelId}&eventType=live&type=video&key=${API_KEY}`
            );
            console.log('response', response);
            const liveStatus = response.data.items.length > 0;
            if (liveStatus) {
                console.log('Live Stream ID:', response.data.items[0].id.videoId);
                // Set the embed URL using the live stream
                setEmbedUrl(`https://www.youtube.com/embed/live_stream?channel=${channelId}&autoplay=1`);
                setIsLive(true);
            } else {
                setIsLive(false);
            }
            setError(null); // Clear any previous errors
        } catch (error) {
            console.error("Error fetching live stream status:", error);
            setError("Failed to fetch live stream status.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        // Check live status on component mount
        checkLiveStatus();

        // Set up interval to check live status every 1 minute (60000ms)
        const intervalId = setInterval(() => {
            checkLiveStatus();
        }, 60000);

        // Cleanup interval on component unmount
        return () => clearInterval(intervalId);
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    if (error) {
        return <p style={{color: 'red'}}>{error}</p>;
    }

    return (
        <div className="stream-wrapper"
             style={{display: 'flex', justifyContent: 'center', alignItems: 'center', padding: '20px'}}>
            <div className="stream-card" style={{
                display: 'flex',
                width: '900px',
                boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
                borderRadius: '8px',
                overflow: 'hidden'
            }}>
                {isLive ? (
                    <div style={{position: 'relative', paddingBottom: '56.25%', height: 0, overflow: 'hidden'}}>
                        <iframe
                            title="YouTube Live Stream"
                            src={embedUrl}
                            frameBorder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowFullScreen
                            style={{position: 'absolute', top: 0, left: 0, width: '100%', height: '100%'}}
                        />
                        <div style={{
                            position: 'absolute',
                            top: '10px',
                            left: '10px',
                            color: 'white',
                            fontWeight: 'bold',
                            backgroundColor: 'rgba(0, 0, 0, 0.7)',
                            padding: '5px 10px',
                            borderRadius: '8px'
                        }}>
                            <span>🔴 Live</span>
                        </div>
                    </div>
                ) : (
                    <div>
                        <div style={{
                            position: 'relative',
                            paddingBottom: '56.25%',
                            height: 0,
                            overflow: 'hidden',
                            maxWidth: '100%',
                            background: '#000'
                        }}>
                            <img
                                src="https://via.placeholder.com/640x360.png?text=Stream+is+currently+offline"
                                alt="Stream offline"
                                style={{width: '100%', height: 'auto'}}
                            />
                        </div>
                        <p style={{color: 'red', marginTop: '20px'}}>
                            현재 방송 중이지 않습니다. 🔴 Live
                        </p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default YouTubeLive;
