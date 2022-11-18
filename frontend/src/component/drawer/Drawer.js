import { useParams,useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import MyDrawer from "./MyDrawer";
import { useEffect, useState } from "react";
import GetAxios from "../../utils/axios/GetAxios";
import LogoHeader from "../LogoHeader";

const Drawer = () => {

    const navigate = useNavigate();

    const [state, setState] = useState([]);
    const [info, setInfo] = useState([]);
    const userId = sessionStorage.getItem("userId")

    useEffect(()=>{

        GetAxios('/drawer/' + userId, {param:{}}, (res)=>{
            console.log(res.data);
            setState(res.data);
            setInfo(res.data.drawerUserInfo);
        });
    }, []);

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
       <MyDrawer state={state} info={info}/>
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