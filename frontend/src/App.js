import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './component/home/Home';
import Login from './component/member/Login';
import Join from './component/member/Join';
import FindId from './component/member/FindId';
import FindPw from './component/member/FindPw';
import Findresult from './component/member/Findresult';
import Find from './component/member/Find';
import FindPwResult from './component/member/FindPwResult';
import ChangePw from './component/member/ChangePw';
import Feed from './component/home/homeContents/Feed';


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
        <Route path='/find' element={<Find/>}/>
        <Route path='/findPw-result' element={<FindPwResult/>}/>
        <Route path='/change-pw' element={<ChangePw/>}/>
        <Route path='/home/feed' element={<Feed/>}/>
      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;
