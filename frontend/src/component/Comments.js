import MyButton from "./button/MyButton";
import LogoHeader from "./LogoHeader";
import Profile from "./home/homeContents/Profile";
import Friends from "./home/homeContents/Friends";
import UploadedFriends from "./home/homeContents/UploadedFriends";
import { useNavigate, useParams } from "react-router-dom";
import MyComments from "./MyComments";

const Comments = () =>{

    const navigate = useNavigate();
    const {paintingId} = useParams();

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
        <MyComments paintingId={paintingId}/>
    </div>
    </div>
    <div className="sub_wrapper">
        <Profile/>
        <Friends/>
    </div>
    </div>

    );
}

export default Comments;