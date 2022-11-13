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
import NonAuthPage from './utils/route/NonAuthPage';
import AuthPage from './utils/route/AuthPage';

function App() {
  return (
  <div>    
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<NonAuthPage><Home/></NonAuthPage>}/>
        <Route path='/login' element={<NonAuthPage><Login/></NonAuthPage>}/>
        <Route path='/join' element={<NonAuthPage><Join/></NonAuthPage>}/>
        <Route path='/join/profile' element={<AuthPage><JoinProfile/></AuthPage>}/>
        <Route path='/findId' element={<NonAuthPage><FindId/></NonAuthPage>}/>
        <Route path='/findPw' element={<NonAuthPage><FindPw/></NonAuthPage>}/>
        <Route path='/find-result' element={<NonAuthPage><Findresult/></NonAuthPage>}/>
        <Route path='/find' element={<NonAuthPage><Find/></NonAuthPage>}/>
        <Route path='/findPw-result' element={<NonAuthPage><FindPwResult/></NonAuthPage>}/>
        <Route path='/change-pw' element={<NonAuthPage><ChangePw/></NonAuthPage>}/>
        <Route path='/home/feed' element={<AuthPage><Feed/></AuthPage>}/>
      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;
