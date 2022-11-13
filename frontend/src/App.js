import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Home from './component/home/Home';
import Login from './component/member/Login';
import Join from './component/member/Join';
import JoinProfile from './component/member/JoinProfile';
import FindId from './component/member/FindId';
import FindPw from './component/member/FindPw';
import Findresult from './component/member/Findresult';
import Find from './component/member/Find';
import FindPwResult from './component/member/FindPwResult';
import ChangePw from './component/member/ChangePw';
import Feed from './component/home/homeContents/Feed';
import AuthRoute from './utils/AuthRoute';


function App() {
  return (
  <div>    
    <BrowserRouter>
        <AuthRoute needAuth={false} path='/' element={<Home/>}/>
        <AuthRoute needAuth={false} path='/login' element={<Login/>}/>
        <AuthRoute needAuth={false} path='/join' element={<Join/>}/>
        <AuthRoute needAuth={true} path='/join/profile' element={<JoinProfile/>}/>
        <AuthRoute needAuth={false} path='/findId' element={<FindId/>}/>
        <AuthRoute needAuth={false} path='/findPw' element={<FindPw/>}/>
        <AuthRoute needAuth={false} path='/find-result' element={<Findresult/>}/>
        <AuthRoute needAuth={false} path='/find' element={<Find/>}/>
        <AuthRoute needAuth={false} path='/findPw-result' element={<FindPwResult/>}/>
        <AuthRoute needAuth={false} path='/change-pw' element={<ChangePw/>}/>
        <AuthRoute needAuth={true} path='/home/feed' element={<Feed/>}/>
    </BrowserRouter>
  </div>
  );
}

export default App;
