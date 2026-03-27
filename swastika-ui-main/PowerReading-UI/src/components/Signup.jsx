import './signup.css'
import { useRef } from 'react';
export default function Signup() {
    const userRef=useRef(null);
    const pswRef=useRef(null);
    function onSubmit(){
        alert(`userName :${userRef.current.value}  password :${pswRef.current.value}`)


    }
    return (
       <form onSubmit={onSubmit}>
    <div>
        <label for="username">Username</label>
        <input type="text" id="username"  ref={userRef} placeholder="Enter your username" />
    </div>

    <div>
        <label for="password">Password</label>
        <input type="password" id="password" ref={pswRef} placeholder="Enter your password" />
    </div>

    <button type="submit">Login</button>
</form>
    );
}

