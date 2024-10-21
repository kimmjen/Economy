import React, { useState } from 'react';

const FinvizMap = () => {
    const [isLoading, setIsLoading] = useState(true);

    const handleImageLoad = () => {
        setIsLoading(false);
    };

    const handleImageError = () => {
        setIsLoading(false);
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
            <h2>Finviz Market Map</h2>
            {isLoading && <p>Loading map...</p>}
            <img
                src="https://finviz.com/published_map.ashx?t=sec_all&st=d1&f=102124&i=sec_all_d1_022273618"
                alt="Finviz Market Map"
                style={{ maxWidth: '100%', height: 'auto' }}
                onLoad={handleImageLoad}
                onError={handleImageError}
            />
        </div>
    );
};

export default FinvizMap;