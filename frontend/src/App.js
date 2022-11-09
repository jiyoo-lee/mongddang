import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './component/home/Home';
import Login from './component/member/Login';
import Join from './component/member/Join';
import FindId from './component/member/FindId';
import FindPw from './component/member/FindPw';
import Findresult from './component/member/Findresult';


function App() {
  return (
  <div>    
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/join' element={<Join/>}/>
        <Route path='/findId' element={<FindId/>}/>
        <Route path='/findPw' element={<FindPw/>}/>
        <Route path='/find-result' element={<Findresult/>}/>
      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;
