import {TextField} from "@mui/material";
import dayjs from "dayjs";
import {DatePicker} from "@mui/x-date-pickers/DatePicker";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";

export const Datepicker = ({date, handleDateChange}) => {

    const disableWeekends = (date) => {
        const day = date.day();
        return day === 0 || day === 6;  // 0 = 일요일, 6 = 토요일
    };
    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
                disableFuture={true}
                label="Select a date"
                value={dayjs(date)}  // date 값을 dayjs 객체로 변환하여 설정
                onChange={(newValue) => {
                    handleDateChange({ target: { value: newValue.format('YYYY-MM-DD') } });
                }}
                shouldDisableDate={disableWeekends}  // 주말 비활성화
                renderInput={(params) => (
                    <TextField
                        {...params}
                        sx={{
                            width: '200px',  // 너비 설정
                            '& .MuiInputBase-input': {
                                padding: '10px',  // 패딩 설정
                            },
                            '& .MuiOutlinedInput-root': {
                                borderRadius: '8px',  // 모서리 둥글기 설정
                                borderColor: 'gray',  // border 색상 설정
                            },
                        }}
                    />
                )}
            />
        </LocalizationProvider>
    );
}
export default Datepicker;