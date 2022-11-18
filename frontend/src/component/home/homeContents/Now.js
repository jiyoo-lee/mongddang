import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import MyNow from "./MyNow";
import { useNavigate } from "react-router-dom";
import UploadedFriends from "./UploadedFriends";
import LogoHeader from "../../LogoHeader";


const Now = () => {

    const navigate = useNavigate();

    return (
    <div className="all_wrapper">
       <LogoHeader/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'피드'}  onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'베스트'} onClick={()=>{navigate('/home/best')}}/>
                <MyButton text={'지금 그림'} type={'nothing'} onClick={()=>{navigate('/home/now')}}/>
                <MyButton text={'업로드'} onClick={()=>{navigate('/home/upload')}}/>
            </div>
            <div className="home_wrapper">
            <br/>
            <UploadedFriends/>
        <br/>
            <MyNow/>
            </div>
            </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );
}

export default Now;