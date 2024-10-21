import React, { useEffect, useState } from 'react';

const YouTubeLive = () => {
    const [isLive, setIsLive] = useState(false);
    const [loading, setLoading] = useState(true);
    const liveStreamId = 't_rODNJku-I'; // Replace with actual live stream ID
    const embedUrl = `https://www.youtube.com/embed/${liveStreamId}?autoplay=1`;

    // Function to simulate checking if the live stream is active
    const checkLiveStatus = async () => {
        try {
            // Replace this logic with actual YouTube API call if you have API key
            // Simulating an API call delay
            setTimeout(() => {
                // Simulating live status, you will replace it with real API response.
                const liveStatus = true; // Set it dynamically
                setIsLive(liveStatus);
                setLoading(false);
            }, 1000); // Simulating API delay
        } catch (error) {
            console.error("Error fetching live stream status:", error);
            setLoading(false);
        }
    };

    useEffect(() => {
        checkLiveStatus(); // Check if the stream is live when the component mounts
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    return (

        <div style={{ textAlign: 'center', marginTop: '20px' }}>

            <h2>Live Stream</h2>

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
                    {/*<div style={{*/}
                    {/*    position: 'absolute',*/}
                    {/*    top: '10px',*/}
                    {/*    left: '10px',*/}
                    {/*    color: 'white',*/}
                    {/*    fontWeight: 'bold',*/}
                    {/*    backgroundColor: 'rgba(0, 0, 0, 0.7)',*/}
                    {/*    padding: '5px 10px',*/}
                    {/*    borderRadius: '8px'*/}
                    {/*}}>*/}
                    {/*    <span>🔴 14 Live</span> /!* Live Indicator *!/*/}
                    {/*</div>*/}
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
                        현재 방송 중이지 않습니다.
                    </p>
                </div>
            )}
            {/* YouTube Stream Embed */}

        </div>
    );
};

export default YouTubeLive;