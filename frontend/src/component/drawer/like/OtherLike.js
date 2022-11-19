import MyButton from "../../button/MyButton";
import { useNavigate, useParams } from "react-router-dom";
import Profile from "../../home/homeContents/Profile";
import Friends from "../../home/homeContents/Friends";
import OtherLikeList from "./OtherLikeList";
import LogoHeader from "../../LogoHeader";

const OtherLike = () => {

    const navigate = useNavigate();
    const {userId} = useParams();

    return(
        <div className="all_wrapper">
            <LogoHeader/>
   <div className="main_wrapper">
       <div className="menu_wrapper">
           <MyButton text={'서랍장'} onClick={()=>{navigate('/drawer/'+userId)}}/>
           <MyButton text={'몽땅'} type={'nothing'} onClick={()=>{navigate('/drawer/like/'+userId)}}/>
           <MyButton text={'방명록'} onClick={()=>{navigate('/guestbook/'+userId)}}/>
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