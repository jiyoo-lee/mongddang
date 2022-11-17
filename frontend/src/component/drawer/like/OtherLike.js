import MyButton from "../../button/MyButton";
import { useNavigate } from "react-router-dom";
import Profile from "../../home/homeContents/Profile";
import Friends from "../../home/homeContents/Friends";
import OtherLikeList from "./OtherLikeList";

const OtherLike = () => {

    const navigate = useNavigate();

    return(
        <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
   <div className="main_wrapper">
       <div className="menu_wrapper">
           <MyButton text={'서랍장'} onClick={()=>{navigate('/drawer')}}/>
           <MyButton text={'몽땅'} type={'nothing'} onClick={()=>{navigate('/drawer/like')}}/>
       </div>
   <div className="home_wrapper">
   <br/>
    <OtherLikeList/>
   </div>
   </div>
   <div className="sub_wrapper">
       <Profile/>
       <Friends/>
   </div>
   </div>
    );
}

export default OtherLike;