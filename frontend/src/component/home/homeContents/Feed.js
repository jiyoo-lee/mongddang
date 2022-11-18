import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import MyFeed from "./MyFeed";
import { useNavigate } from "react-router-dom";
import UploadedFriends from "./UploadedFriends";
import { useEffect } from "react";
import LogoHeader from "../../LogoHeader";


const Feed = () =>{

    const navigate = useNavigate();
    
    return (
        <div className="all_wrapper">
            <LogoHeader/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'피드'} type={'nothing'} onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'베스트'} onClick={()=>{navigate('/home/best')}}/>
                <MyButton text={'지금 그림'} onClick={()=>{navigate('/home/now')}}/>
                <MyButton text={'업로드'} onClick={()=>{navigate('/home/upload')}}/>
            </div>
        <div className="home_wrapper">
        <br/>
            <UploadedFriends/>
        <br/>
            <MyFeed/>
        </div>
        </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
        </div>
    );
}

export default Feed;