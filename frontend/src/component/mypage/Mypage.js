import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import MyInfo from "./MyInfo";
import LogoHeader from "../LogoHeader";



const MyPage = () => {

    const navigate = useNavigate();
    return (
        <div className="all_wrapper">
            <LogoHeader/>
   <div className="main_wrapper">
       <div className="menu_wrapper">
           <MyButton text={'서랍장'}  onClick={()=>{navigate('/drawer')}}/>
           <MyButton text={'몽땅'} onClick={()=>{navigate('/drawer/like')}}/>
           <MyButton text={'마이 페이지'} type={'nothing'} onClick={()=>{navigate('/drawer/mypage')}}/>
       </div>
   <div className="home_wrapper">
   <br/>
    <MyInfo/>
   </div>
   </div>
   <div className="sub_wrapper">
       <Profile/>
       <Friends/>
   </div>
   </div>
    );
}

export default MyPage;