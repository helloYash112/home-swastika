import { Header } from "./Header"
import { Link } from "react-router-dom"

export function Home(){
    return<div id="app"
    >
        <Header></Header>
        <h1>welcome to home...</h1>
        <nav>
            <ul>
                <li>
                    <Link to='/form'>create-reading</Link>
                    <Link to='/Signup'>Signup</Link>
                </li>
            </ul>
        </nav>
    </div>
}