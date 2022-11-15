import { useLocation, useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import DropsPaintings from "./DropsPaintings";
import { useEffect, useState } from "react";
import GetAxios from "../../utils/axios/GetAxios";


const Paintings = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const [paintings, setPaintings] = useState([]);

    const dropsId = location.state.dropsId;

    useEffect(()=>{
        GetAxios('/drops/'+dropsId,{params:{}},(res)=>{setPaintings(res.data.paintings)})
    },[])


    return (
        <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
   <div className="main_wrapper">
       <div className="menu_wrapper">
           <MyButton text={'서랍장'} type={'nothing'} onClick={()=>{navigate('/drawer')}}/>
           <MyButton text={'몽땅'} onClick={()=>{navigate('/drawer/like')}}/>
           <MyButton text={'마이 페이지'} onClick={()=>{navigate('/drawer/mypage')}}/>
       </div>
   <div className="home_wrapper">
   <br/>
      <DropsPaintings paintings={paintings} />
   </div>
   </div>
   <div className="sub_wrapper">
       <Profile/>
       <Friends/>
   </div>
   </div>
    );
}

export default Paintings;