import React, {useState} from 'react';
import {TextField, MenuItem, Button} from '@mui/material';
import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
import timezone from 'dayjs/plugin/timezone';
import {Alert} from "@material-tailwind/react";

dayjs.extend(utc);
dayjs.extend(timezone);
const EventForm = () => {
    // State management for form inputs
    const [formData, setFormData] = useState({
        eventType: '',
        indicatorName: '',
        fedSpeaker: '',
        fedPosition: '',
        eventDate: '',
        eventTimeKst: '',
        eventTimeEst: '',
        indicatorValue: '',
        forecastValue: '',
        previousValue: '',
        content: '',
        importance: 0
    });

    const [showAlerts, setShowAlerts] = useState({
        success: false,
        error: false,
    });

    // Example data for dropdowns
    const eventType = [
        {value: 'economic', label: 'Economic'},
        {value: 'political', label: 'Political'},
    ];
    const indicatorType = [
        {value: 'gdp', label: 'GDP'},
        {value: 'cpi', label: 'CPI'},
    ];
    const fedSpeakers = [
        {value: 'powell', label: 'Jerome Powell'},
        {value: 'brainard', label: 'Lael Brainard'},
    ];
    const fedPositions = [
        {value: 'chair', label: 'Chair'},
        {value: 'vice-chair', label: 'Vice Chair'},
    ];

    // Function to convert KST to EST
    const convertKstToEst = (kstDateTime) => {
        const kstTime = dayjs.tz(kstDateTime, 'Asia/Seoul');
        const estTime = kstTime.tz('America/New_York');
        return estTime.format('YYYY-MM-DDTHH:mm:ss');
    };

    // Handler to update form state
    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setFormData((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    // Handler to combine date and time
    // Time change handler with auto-conversion
    const handleTimeChange = (name, time) => {
        const eventDate = formData.eventDate;
        if (eventDate) {
            const eventTimeKst = dayjs(`${eventDate}T${time}`).format('YYYY-MM-DDTHH:mm:ss');
            setFormData((prevState) => ({
                ...prevState,
                [name]: eventTimeKst,
            }));

            if (name === 'eventTimeKst') {
                const eventTimeEst = convertKstToEst(eventTimeKst);
                setFormData((prevState) => ({
                    ...prevState,
                    eventTimeEst: eventTimeEst,  // Automatically set EST time
                }));
            }
        }
    };
    // Function to automatically hide alerts after 3 seconds
    const autoHideAlert = () => {
        setTimeout(() => {
            setShowAlerts({
                success: false,
                error: false,
            });
        }, 3000);  // 3000ms = 3 seconds
    };

    // Form submit handler
    // Form submit handler
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/economic-indicators', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (!response.ok) {
                throw new Error('Failed to submit data');
            }
            const result = await response.json();
            console.log('Success:', result);
            setShowAlerts({success: true, error: false});  // Show success alert
            autoHideAlert();  // Automatically hide the alert after 3 seconds
        } catch (error) {
            console.error('Error:', error);
            setShowAlerts({success: false, error: true});  // Show error alert
            autoHideAlert();  // Automatically hide the alert after 3 seconds
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className="p-5 grid gap-y-10 gap-x-6 md:grid-cols-4 xl:grid-cols-4">
                    <TextField
                        size="small"
                        name="eventType"
                        select
                        label="Select Event Type"
                        value={formData.eventType}
                        onChange={handleInputChange}
                        helperText="Please select the event type"
                    >
                        {eventType.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>

                    <TextField
                        size="small"
                        name="indicatorName"
                        select
                        label="Select Indicator Name"
                        value={formData.indicatorName}
                        onChange={handleInputChange}
                        helperText="Please select the indicator name"
                    >
                        {indicatorType.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>

                    <TextField
                        size="small"
                        name="fedSpeaker"
                        select
                        label="Select Fed Speaker"
                        value={formData.fedSpeaker}
                        onChange={handleInputChange}
                        helperText="Please select the Fed speaker"
                    >
                        {fedSpeakers.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>

                    <TextField
                        size="small"
                        name="fedPosition"
                        select
                        label="Select Fed Position"
                        value={formData.fedPosition}
                        onChange={handleInputChange}
                        helperText="Please select the Fed position"
                    >
                        {fedPositions.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>

                    <TextField
                        size="small"
                        name="eventDate"
                        type="date"
                        label="Event Date"
                        value={formData.eventDate}
                        onChange={handleInputChange}
                        helperText="Please select the event date"
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />

                    <TextField
                        size="small"
                        name="eventTimeKST"
                        type="time"
                        label="Event Time KST"
                        value={dayjs(formData.eventTimeKst).format('HH:mm')}  // Extract time for UI
                        onChange={(e) => handleTimeChange('eventTimeKst', e.target.value)}
                        helperText="Please select the event time (KST)"
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />

                    <TextField
                        size="small"
                        name="eventTimeEST"
                        type="time"
                        label="Event Time EST"
                        value={dayjs(formData.eventTimeEst).format('HH:mm')}  // Extract time for UI
                        onChange={(e) => handleTimeChange('eventTimeEst', e.target.value)}
                        helperText="Please select the event time (EST)"
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />

                    <TextField
                        size="small"
                        name="indicatorValue"
                        label="Indicator Value"
                        value={formData.indicatorValue}
                        onChange={handleInputChange}
                        helperText="Enter the indicator value"
                    />

                    <TextField
                        size="small"
                        name="forecastValue"
                        label="Forecast Value"
                        value={formData.forecastValue}
                        onChange={handleInputChange}
                        helperText="Enter the forecast value"
                    />

                    <TextField
                        size="small"
                        name="previousValue"
                        label="Previous Value"
                        value={formData.previousValue}
                        onChange={handleInputChange}
                        helperText="Enter the previous value"
                    />

                    <TextField
                        size="small"
                        name="content"
                        multiline
                        rows={4}
                        label="Content"
                        value={formData.content}
                        onChange={handleInputChange}
                        helperText="Enter event details"
                    />

                    <TextField
                        size="small"
                        name="importance"
                        type="number"
                        label="Importance"
                        value={formData.importance}
                        onChange={handleInputChange}
                        helperText="Rate the importance (1-5)"
                        inputProps={{min: 1, max: 5}}
                    />
                </div>

                <div className="p-5 flex justify-between">
                    <pre>{JSON.stringify(formData, null, 2)}</pre>
                    {/* For debugging */}
                </div>

                <div className="p-5 flex justify-between">
                    <div></div>
                    <div>
                        <Button type="submit" variant="contained" color="primary">
                            Submit
                        </Button>
                    </div>
                </div>
            </form>
            {/* Alerts for success and error */}
            {showAlerts.success && (
                <Alert
                    color="green"
                    onClose={() => setShowAlerts({...showAlerts, success: false})}
                >
                    Data submitted successfully!
                </Alert>
            )}
            {showAlerts.error && (
                <Alert
                    color="red"
                    onClose={() => setShowAlerts({...showAlerts, error: false})}
                >
                    Failed to submit data.
                </Alert>
            )}
        </div>
    );
};

export default EventForm;