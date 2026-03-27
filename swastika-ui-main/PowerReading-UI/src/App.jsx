import { Home } from './components/Home';
import './App.css'

import PowerInputForm from './components/PowerInputForm';
import Signup from './components/Signup'
import {Route, Routes} from 'react-router-dom';



function App() {

  
 
  return (
    <Routes>
    <Route path="/" element={<Home />} /> 
    <Route path='/form' element={<PowerInputForm></PowerInputForm>}></Route>
    <Route path='/Signup' element={<Signup/>}></Route>
    </Routes>
  )
}

export default App
