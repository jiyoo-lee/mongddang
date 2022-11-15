import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import { useNavigate } from "react-router-dom";
import UploadedFriends from "./UploadedFriends";
import MyUpload from "./MyUpload";

const Upload = () => {


    const navigate = useNavigate();
    return (
        <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'피드'}  onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'베스트'} onClick={()=>{navigate('/home/best')}}/>
                <MyButton text={'지금 그림'} onClick={()=>{navigate('/home/now')}}/>
                <MyButton text={'업로드'} type={'nothing'} onClick={()=>{navigate('/home/upload')}}/>
            </div>
            <div className="home_wrapper">
            <br/>
                <UploadedFriends/>
            <br/>
            <MyUpload/>
            </div>
            </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );
}

export default Upload;