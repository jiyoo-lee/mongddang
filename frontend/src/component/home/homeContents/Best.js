import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import { useNavigate } from "react-router-dom";
import UploadedFriends from "./UploadedFriends";
import MyBest from "./MyBest";
import LogoHeader from "../../LogoHeader";

const Best = () => {

    const navigate = useNavigate();

    return (
    <div className="all_wrapper">
        <LogoHeader/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'피드'}  onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'베스트'} type={'nothing'} onClick={()=>{navigate('/home/best')}}/>
                <MyButton text={'지금 그림'}  onClick={()=>{navigate('/home/now')}}/>
                <MyButton text={'업로드'} onClick={()=>{navigate('/home/upload')}}/>
            </div>
            <div className="home_wrapper">
            <br/>
            <UploadedFriends/>
        <br/>
            <MyBest/>
            </div>
            </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );
}

export default Best;