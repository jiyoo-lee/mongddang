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
import Upload from './component/home/homeContents/Upload';
import NewPainting from './component/home/upload/NewPainting';
import NewDrops from './component/home/upload/NewDrops';
import Best from './component/home/homeContents/Best';
import Now from './component/home/homeContents/Now';
import Drawer from './component/drawer/Drawer';
import Like from './component/drawer/like/Like';
import OtherLike from './component/drawer/like/OtherLike';
import MyPage from './component/mypage/Mypage';
import Paintings from './component/drawer/Paintings';
import OtherDrawer from './component/drawer/OtherDrawer';
import Guestbook from './component/guestbook/Guestbook';
import Followers from './component/home/homeContents/Followers';
import Comments from './component/Comments';
import ArtsCenter from './component/artsCenter/ArtsCenter';
import ArtsCenterDetail from './component/artsCenter/ArtsCenterDetail';
import ArtsCenterUpload from './component/artsCenter/ArtsCenterUpload';
import SearchMyPainting from './component/artsCenter/SearchMyPainting';

function App() {
  return (
  <div>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<NonAuthPage><Home/></NonAuthPage>}/>
        <Route path='/login' element={<NonAuthPage><Login/></NonAuthPage>}/>
        <Route path='/join' element={<NonAuthPage><Join/></NonAuthPage>}/>
        <Route path='/profile' element={<AuthPage><JoinProfile/></AuthPage>}/>
        <Route path='/findId' element={<NonAuthPage><FindId/></NonAuthPage>}/>
        <Route path='/findPw' element={<NonAuthPage><FindPw/></NonAuthPage>}/>
        <Route path='/find-result' element={<NonAuthPage><Findresult/></NonAuthPage>}/>
        <Route path='/find' element={<NonAuthPage><Find/></NonAuthPage>}/>
        <Route path='/findPw-result' element={<NonAuthPage><FindPwResult/></NonAuthPage>}/>
        <Route path='/change-pw' element={<NonAuthPage><ChangePw/></NonAuthPage>}/>
        <Route path='/home/feed' element={<AuthPage><Feed/></AuthPage>}/>
        <Route path='/home/best' element={<AuthPage><Best/></AuthPage>}/>
        <Route path='/home/now' element={<AuthPage><Now/></AuthPage>}/>
        <Route path='/home/upload' element={<AuthPage><Upload/></AuthPage>}/>
        <Route path='/home/upload/painting' element={<AuthPage><NewPainting/></AuthPage>}/>
        <Route path='/home/upload/new-drops' element={<AuthPage><NewDrops/></AuthPage>}/>        
        <Route path='/drawer' element={<AuthPage><Drawer/></AuthPage>}/>        
        <Route path='/drawer/paintings' element={<AuthPage><Paintings/></AuthPage>}/>       
        <Route path='/drawer/like' element={<AuthPage><Like/></AuthPage>}/>       
        <Route path='/drawer/like/:userId' element={<AuthPage><OtherLike/></AuthPage>}/>       
        <Route path='/drawer/mypage' element={<AuthPage><MyPage/></AuthPage>}/>        
        <Route path='/drawer/:userId' element={<AuthPage><OtherDrawer/></AuthPage>}/>
        <Route path='/drawer/buddy/:userId' element={<AuthPage><Followers/></AuthPage>}/>
        <Route path='/guestbook/:userId' element={<AuthPage><Guestbook/></AuthPage>}/>
        <Route path='/comments/:paintingId' element={<AuthPage><Comments/></AuthPage>}/>
        <Route path='/arts-center' element={<AuthPage><ArtsCenter/></AuthPage>}/>
        <Route path='/arts-center/:contestId' element={<AuthPage><ArtsCenterDetail/></AuthPage>}/>
        <Route path='/arts-center/:contestId/upload' element={<AuthPage><ArtsCenterUpload/></AuthPage>}/>
        <Route path='/arts-center/:contestId/upload/:userId' element={<AuthPage><SearchMyPainting/></AuthPage>}/>
      </Routes>
    </BrowserRouter>
  </div>
  );
}

export default App;
