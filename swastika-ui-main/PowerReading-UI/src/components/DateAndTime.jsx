import { useReducer, useEffect } from "react";


function reduceDate(state, action) {
    if (action.type === 'upd-date') {
        
        return new Date().toLocaleString(); 
    }
    return state;
}

function DateAndTime() {
    
    const [dateAndTime, dispatch] = useReducer(reduceDate, new Date().toLocaleString());

    useEffect(() => {
        
        const timer = setInterval(() => {
            dispatch({ type: 'upd-date' });
        }, 1000);

        return () => clearInterval(timer);
    }, []);

    return (
        <span>
            <h3 style={{ color: "blue", fontSize: "28px" }}>{dateAndTime}</h3>
        </span>
    );
}

export default DateAndTime;