import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import MyDrawer from "./MyDrawer";
import { useEffect, useState } from "react";
import GetAxios from "../../utils/axios/GetAxios";
import LogoHeader from "../LogoHeader";

const Drawer = () => {

    const navigate = useNavigate();

    const [drops, setDrops] = useState([]);

    useEffect(()=>{
        GetAxios('/drawer/list/'+(sessionStorage.getItem("userId")),{params:{}},(res)=>{setDrops(res.data)
        console.log(res.data)})
    },[])

    return (
        <div className="all_wrapper">
            <LogoHeader/>
   <div className="main_wrapper">
       <div className="menu_wrapper">
           <MyButton text={'서랍장'} type={'nothing'} onClick={()=>{navigate('/drawer')}}/>
           <MyButton text={'몽땅'} onClick={()=>{navigate('/drawer/like')}}/>
           <MyButton text={'마이 페이지'} onClick={()=>{navigate('/drawer/mypage')}}/>
       </div>
   <div className="home_wrapper">
   <br/>
       <MyDrawer drops={drops}/>
   </div>
   </div>
   <div className="sub_wrapper">
       <Profile/>
       <Friends/>
   </div>
   </div>
    );
}

export default Drawer;